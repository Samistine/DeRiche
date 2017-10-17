<?php

namespace AppBundle\Controller;

use AppBundle\Entity\User;
use AppBundle\Form\UserType;
use Doctrine\ORM\EntityManager;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpKernel\Exception\BadRequestHttpException;
use Symfony\Component\HttpKernel\Exception\ConflictHttpException;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

/**
 * @Route("/admin")
 */
class AdminController extends Controller
{

    /**
     * @Route(name="Admin Panel")
     */
    public function indexAction(Request $request, UserPasswordEncoderInterface $passwordEncoder)
    {
        // 1) build the form
        $user = new User();
        $form = $this->createForm(UserType::class, $user);

        // 2) handle the submit (will only happen on POST)
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            // 3) Encode the password (you could also do this via Doctrine listener)
            $password = $passwordEncoder->encodePassword($user, $user->getPlainPassword());
            $user->setPassword($password);

            // 4) save the User!
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();

            // ... do any other work - like sending them an email, etc
            // maybe set a "flash" success message for the user

            //return $this->redirectToRoute('Admin Panel');
        }

        $users = $this->getDoctrine()->getRepository(User::class)->findAll();

        return $this->render('admin/admin.html.twig', array(
            'users' => $users,
            'form' => $form->createView()
        ));
    }

    /**
     * @Route("/archive/{username}", name="Archive User")
     */
    public function archiveUser(User $user, EntityManagerInterface $em, Request $request)
    {
        // Prevent the user from archiving themselves.
        if ($user == $this->getUser())
            throw new BadRequestHttpException('You can not archive yourself!');

        $user->setIsActive(false);
        $em->persist($user);
        $em->flush();

        //Get the page the browser was on before coming here
        $referer = $request->headers->get('referer');

        //Send them back
        return $this->redirect($referer);
    }

    /**
     * @Route("/delete/{username}", name="Delete User")
     */
    public function deleteUser(User $user, EntityManagerInterface $em, Request $request)
    {
        // Prevent the user from deleting themselves.
        if ($user == $this->getUser())
            throw new BadRequestHttpException('You can not delete yourself!');

        // Prevent the deletion of a dependency of notes
        if (!$user->getAuthoredNotes()->isEmpty())
            throw new ConflictHttpException('Can not delete a user that has authored notes!');

        // Prevent the deletion of a dependency of notes
        if (!$user->getReviewedNotes()->isEmpty())
            throw new ConflictHttpException('Can not delete a user that has reviewed notes!');


        $em->remove($user);
        $em->flush();

        //Get the page the browser was on before coming here
        $referer = $request->headers->get('referer');

        //Send them back
        return $this->redirect($referer);
    }
}
