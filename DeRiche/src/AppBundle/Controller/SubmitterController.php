<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Patient;
use AppBundle\Entity\Note;
use DateTime;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\HttpFoundation\Response;

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
        // Show the draft and kicked back notes.
        $draftnotes = $backnotes = [];
        if($this->getUser()) {
            foreach ($this->getUser()->getAuthoredNotes() as $n) {
                if ($n->getState() == $n::DRAFT) {
                    $draftnotes[] = $n;
                } elseif ($n->getState() == $n::KICKED_BACK) {
                    $backnotes[] = $n;
                }
            }
        }
        return $this->render('notes/home.html.twig', array('draftnotes' => $draftnotes,
            'backnotes' => $backnotes));
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
            return $this->redirect('../create/' . $patients[0]->getUuid());
        }
    }

    /**
     * @Route("/create/{id}", name="Writer Create Note")
     */
    public function create(Request $request, Patient $patient)
    {
        // Check if there's a note for this patient done today and whether it's a draft.
        $update = false;
        foreach ($patient->getNotes() as $n) {
            $ts = $n->getCreatedAt()->getTimeStamp();
            if (date('Y-m-d', strtotime("today")) == date('Y-m-d', $ts)) {
                // Verify that it's a draft before using it as the base.
                if ($n->getState() == $n::DRAFT || $n->getState() == $n::KICKED_BACK) {
                    $note = $n;
                    $update = true;
                }
            }
        }

        // Create a new Note if this is not a draft.
        if (!isset($note)) {
            $note = new Note();
            // Set some default values
            $note->setStaff($this->getUser());
            $note->setPatient($patient);

            // Submit the draft so we can update it later
            $em = $this->getDoctrine()->getManager();
            $em->persist($note);
            $em->flush();
        }

        // Create the form we show the user.
        $form = $this->createFormBuilder($note)
            ->add('content', TextareaType::class, array(
                'attr' => array('rows' => '25'),))
            ->add('signature', HiddenType::class)
            ->add('bowel', CheckboxType::class, array(
                'label'    => 'Any cool bowel movements?',
                'required' => false))
            ->add('seizure', CheckboxType::class, array(
                'label'    => 'Seizure Information',
                'required' => false))
            ->getForm();

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $note = $form->getData();
            $note->setState(20); // Send to reviewer.

            //Save
            $em = $this->getDoctrine()->getManager();
            $em->persist($note);
            $em->flush();

            return $this->redirectToRoute('home page');
        }


        if ($update) {
            return $this->render('notes/create.html.twig', array(
                'patient' => $patient,
                'form' => $form->createView(),
                'note' => $note,
                'content' => $note->getContent(),
                'objectives' => $patient->getObjectives()->toArray()
            ));
        } else {
            return $this->render('notes/create.html.twig', array(
                'patient' => $patient,
                'form' => $form->createView(),
                'note' => $note,
                'objectives' => $patient->getObjectives()->toArray()
            ));
        }
    }

    /**
     * @Route("/updatedraft/{id}", name="Writer Update Draft")
     */
    public function updateDraft(Request $request, Note $note)
    {
        // This only handles the inbound request via JS, the state remains 10 until it's submitted.
        $content = $request->get('content');
        $note->setContent($content);

        $em = $this->getDoctrine()->getManager();
        $em->persist($note);
        $em->flush();

        return new Response(); // Empty response.
    }
}