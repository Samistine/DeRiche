<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Patient;
use AppBundle\Entity\Note;
use AppBundle\Entity\Types\FormType;
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
 * This is the actual note creation system. This is the spine of the system.
 * @Route("/note", name="Note Creating Subsystem")
 */
class SubmitterController extends Controller
{
    /**
     * Main page which is essentially just a starting point.
     * It shows kicked back/draft notes and the search button.
     * @Route("/", name="Note Creation Main Page")
     */
    public function indexAction()
    {
        // Get the draft and kicked back notes.
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
        // Send it to the template as a $backnotes variable.
        return $this->render('notes/home.html.twig', array('draftnotes' => $draftnotes,
            'backnotes' => $backnotes));
    }

    /**
     * Search for a patient for use and then forward a customer to the note creation system.
     * @Route("/findpatient/", name="Writer Find Patient")
     */
    public function findPatient(Request $request)
    {
        // We get the name that the Author wants to look up.
        $patientName = $request->get('name');
        // We then separate the name into two so we can search by first and last name.
        $names = explode(' ', $patientName, 2);
        $givenName = $names[0];
        $familyName = isset($names[1]) ? $names[1] : null;

        // The actual look up function.
        $patients = $this->getDoctrine()
            ->getRepository(Patient::class)
            ->findBy([
                'firstName' => $givenName,
                'lastName' => $familyName
            ]);

        // We then make sure that only one patient has that name.
        $count = count($patients);
        if ($count === 0) {
            // If a patient was not found with that name.
            throw $this->createNotFoundException('No individual found for ' . $patientName);
        }
        if ($count === 1) {
            // If a patient was found then we push them to the note creation page.
            return $this->redirect('../create/' . $patients[0]->getUuid());
        }
    }

    /**
     * The actual note creation page, this uses the patient ID rather than note ID.
     * That is the only odd part of this function as it should be using the draft ID and generating one
     * in the findpatient method.
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
            ->getForm();

        // Handle the form that we showed the user above.
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $note = $form->getData();
            $note->setState(20); // Send to reviewer.

            // Submit to the database. and persist.
            $em = $this->getDoctrine()->getManager();
            $em->persist($note);
            $em->flush();

            return $this->redirectToRoute('home page');
        }

        // We get the objectives to send to the template
        $objArray = array();
        foreach ($patient->getObjectives()->toArray() as $item) {
            $objArray[$item->getFreqKind()][] = $item;
        }
        // We send a different render if this is an update because we need to include
        // the $note->getContent()
        if ($update) {
            return $this->render('notes/create.html.twig', array(
                'patient' => $patient,
                'form' => $form->createView(),
                'note' => $note,
                'content' => $note->getContent(),
                'objectives' => $objArray,
                'form_types' => FormType::getReadableValues()
            ));
        } else {
            return $this->render('notes/create.html.twig', array(
                'patient' => $patient,
                'form' => $form->createView(),
                'note' => $note,
                'objectives' => $objArray,
                'form_types' => FormType::getReadableValues()
            ));
        }
    }

    /**
     * This is a method called by Javascript and constantly updates the note
     * so as to note lose content.
     * @Route("/updatedraft/{id}", name="Writer Update Draft")
     */
    public function updateDraft(Request $request, Note $note)
    {
        // This only handles the inbound request via JS, the state remains 10 until it's submitted.
        $content = $request->get('content');
        $note->setContent($content);

        // Submit to database and persist.
        $em = $this->getDoctrine()->getManager();
        $em->persist($note);
        $em->flush();

        // Empty response since this should only be called by JS.
        return new Response();
    }
}