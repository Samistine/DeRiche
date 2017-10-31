<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Patient;
use AppBundle\Entity\Note;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

/**
 * @Route("/note", name="Note Creating Subsystem")
 */
class SubmitterController extends Controller
{
    /**
     * @Route("/", name="Note Creation Main Page")
     */
    public function indexAction()
    {
        return $this->render('notes/home.html.twig', array('name' => ''));
    }

    /**
     * @Route("/findpatient/", name="Writer Find Patient")
     */
    public function findPatient(Request $request)
    {
        $patientName = $request->get('name');

        $names = explode(' ', $patientName, 2);

        $givenName = $names[0];
        $familyName = isset($names[1]) ? $names[1] : null;

        $patients = $this->getDoctrine()
            ->getRepository(Patient::class)
            ->findBy([
                'firstName' => $givenName,
                'lastName' => $familyName
            ]);

        $count = count($patients);

        if ($count === 0) {
            //TODO: Redirect to same page and display error.
            throw $this->createNotFoundException('No individual found for ' . $patientName);
        }

        if ($count === 1) {
            // TODO: Check if there's already a note submitted today, offer to edit or say wait for "review"
            return $this->redirect('../create/' . $patients[0]->getUuid());
        }

        //return $this->render('notes/home.html.twig', array('name' => $patients));
    }

    /**
     * @Route("/create/{id}", name="Writer Create Note")
     */
    public function create(Request $request, Patient $patient)
    {
        // Create a Note
        $note = new Note();

        // Set some default values
        $note->setStaff($this->getUser());
        $note->setPatient($patient);

        // This is because draft feature is not done. What we can do here is set this to 10.
        // We can then continually submit the form through an Update Note route via JS.
        // And then when he finally hits submit it will go through the if statement below.
        // We then set the state to 20 there at which point reviewers will see it.
        $note->setState(20);

        // Create the form we show the user.
        $form = $this->createFormBuilder($note)
            ->add('content', TextareaType::class, array(
                'attr' => array('rows' => '25'),))
            ->add('signature', HiddenType::class)
            ->getForm();

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
             $note = $form->getData();
             $note->setSubmittedAt(new \DateTime());

             $em = $this->getDoctrine()->getManager();
             $em->persist($note);
             $em->flush();
            return $this->redirectToRoute('home page');
        }

        return $this->render('notes/create.html.twig', array(
            'patient' => $patient,
            'form' => $form->createView(),
            'note' => $note,
            'objectives' => $patient->getObjectives()->toArray()
        ));
    }

}
