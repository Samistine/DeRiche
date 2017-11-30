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
 * @Route("/admin/")
 */
class AdminController extends Controller
{

    /**
     * @Route(name="Admin Panel")
     */
    public function indexAction(Request $request, UserPasswordEncoderInterface $passwordEncoder)
    {
        $user = new User();
        $form = $this->createForm(UserType::class, $user);

        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            // 3) Encode the password (you could also do this via Doctrine listener)
            $password = $passwordEncoder->encodePassword($user, $user->getPlainPassword());
            $user->setPassword($password);

            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
        }

        $users = $this->getDoctrine()->getRepository(User::class)->findAll();

        return $this->render('admin/admin.html.twig', array(
            'users' => $users,
            'form' => $form->createView()
        ));
    }

    /**
     * @Route("archive/{username}", name="Archive User")
     */
    public function archiveUser(User $user, EntityManagerInterface $em, Request $request)
    {
        // Prevent the user from archiving themselves.
        if ($user == $this->getUser())
            throw new BadRequestHttpException('You can not archive yourself!');

        $user->setIsActive(false);
        $em->persist($user);
        $em->flush();

        //Get the page the browser was on before coming here and send em' out.
        $referer = $request->headers->get('referer');
        return $this->redirect($referer);
    }

    /**
     * @Route("delete/{username}", name="Delete User")
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

        //Get the page the browser was on before coming here and send em' out.
        $referer = $request->headers->get('referer');
        return $this->redirect($referer);
    }

    /**
 * @Route("secondaryrole/{username}", name="Update Secondary Role for User")
 */
    public function secondaryRole(User $user, EntityManagerInterface $em, Request $request)
    {
        // Get the role and add it as third value (After ROLE_USER)
        $role = $request->get('role');

        $roles = $user->getRoles();
        $roles[2] = $role;
        // Ignore the above if it's none and just remove it from the array.
        if ($role == "None") {
            unset($roles[2]);
        }
        $user->setRoles($roles);
        $em->persist($user);
        $em->flush();

        //Get the page the browser was on before coming here
        $referer = $request->headers->get('referer');

        //Send them back
        return $this->redirect($referer);
    }

    /**
     * @Route("password/{username}", name="Update Password for User")
     */
    public function setPassword(User $user, EntityManagerInterface $em, Request $request, UserPasswordEncoderInterface $passwordEncoder)
    {
        // Get the password and encode it.
        $password = $request->get('password');
        $password = $passwordEncoder->encodePassword($user, $password);
        // Now we set the password and send it to the DB.
        $user->setPassword($password);
        $em->persist($user);
        $em->flush();

        //Get the page the browser was on before coming here and send em' out.
        $referer = $request->headers->get('referer');
        return $this->redirect($referer);
    }
}
