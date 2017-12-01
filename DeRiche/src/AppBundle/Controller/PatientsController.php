<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Objective;
use AppBundle\Entity\Patient;
use Doctrine\ORM\EntityManagerInterface;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpKernel\Exception\BadRequestHttpException;

/**
 * This is an important class and is used for all patient-related activity.
 * This has actions like, creation, deletion, listing, adjusting, etc.
 * @Route("/patients", name="View Patients")
 */
class PatientsController extends Controller
{
    /**
     * This is the main action for the route and is just listing the patients.
     * @Route("/", name="Patient List")
     */
    public function indexAction()
    {
        // We get two separate lists.
        // One for active patients, one for disabled/archived patients.
        $activePatients = array();
        $inactivePatients = array();

        // We pull all patients and separate them.
        $allPatients = $this->getDoctrine()
            ->getRepository(Patient::class)
            ->findAll();

        // The actual separation part.
        foreach ($allPatients as $patient) {
            if ($patient->getActive()) array_push($activePatients, $patient);
            else                       array_push($inactivePatients, $patient);
        }

        // We generate a class to send to Twig which will then separate it as needed.
        $patientsQuery = new \stdClass();
        $patientsQuery->active = $activePatients;
        $patientsQuery->inactive = $inactivePatients;

        return $this->render('patients.html.twig', array(
            'patientsQuery' => $patientsQuery
        ));
    }

    /**
     * This is the viewing of the patient page. We send everything we can over to twig.
     * Twig does the heavy lifting for this class.
     * @Route("/patient/{id}/", name="View Patient")
     */
    public function viewPatient($id)
    {
        // We find the patient ID and use it to serve the patient page.
        $patient = $this->getDoctrine()
            ->getRepository(Patient::class)
            ->find($id);
        // If there's no patient ID then we throw it away.
        if (!$patient) {
            throw $this->createNotFoundException(
                'No patient found for id ' . $id
            );
        }
        // Render if found.
        return $this->render('patient.html.twig', array('patient' => $patient));
    }

    /**
     * This is for adding an objective to a patient and is quite involved due to the ability
     * to add multiple at once.
     * @Route("/patient/{id}/objective/", name="Add Patient Objective")
     */
    public function addObjective(Request $request, Patient $patient)
    {
        // First we iterate through a ParameterPag for the request and find the objective words we need
        $objectives = [];
        // This is the list of objective words.
        $objwords = ['objectiveName', 'goalText', 'objectiveText', 'guidanceNotes', 'freqAmount', 'freqKind'];
        // Actual iteration process.
        foreach ($request->request->all() as $k => $o) {
            // We make sure that the KEY value starts with an objective word.
            $objword = substr($k, 0, -1);
            // We get the number of the objective for this patient that we want to edit.
            // This is reliant on the understanding that we append the objective variables in HTML
            // with the number of the objective.
            $objnum = intval(substr($k, -1));
            // Let's check if the first part of the key is what we want and the second part is an integer.
            if (in_array($objword, $objwords) && is_numeric(substr($k, -1))) {
                // Add to the objectives table that we'll iterate through and persist.
                // Notice how we use the objective number pulled above and start the array with that.
                // That is the actual key that we need to edit the right objective.
                $objectives[$objnum][$objword] = $o;
            }
        }

        // Iterate through the objective array we just created and persist them all in the database.
        foreach ($objectives as $obj) {
            // Create
            $objective = new Objective();
            $objective
                ->setPatient($patient)
                ->setName($obj['objectiveName'])
                ->setGoalText($obj['goalText'])
                ->setObjectiveText($obj['objectiveText'])
                ->setGuidanceNotes($obj['guidanceNotes'])
                ->setFreqAmount($obj['freqAmount'])
                ->setFreqKind($obj['freqKind']);
            // This is where we add each objective and persist it.
            $patient->addObjective($objective);
            $em = $this->getDoctrine()->getManager();
            $em->persist($objective);
            $em->flush();
        }
        // Return them back to the patient page.
        return $this->redirect('../');
    }

    /**
     * Deleting a patient objective. Self explanatory.
     * @Route("/patient/{patient}/objective/{objective}/delete", name="Delete Patient Objective")
     */
    public function deleteObjective(EntityManagerInterface $em, Patient $patient, Objective $objective)
    {
        // If the objective is attached to the patient then we delete it.
        if ($patient !== $objective->getPatient()) {
            throw new BadRequestHttpException("Note does not belong to patient specified.");
        }
        // Persist the delete.
        $em->remove($objective);
        $em->flush();
        // Redirect them back to the patient page.
        return $this->redirect('../../');
    }

    /**
     * This is for updating a specific objective. Much less complex than adding.
     * @Route("/patient/{patient}/objective/{objective}/patch", name="Update Patient Objective")
     */
    public function updateObjective(Request $request, Patient $patient, Objective $objective)
    {
        if ($patient !== $objective->getPatient()) {
            throw new BadRequestHttpException("Note does not belong to patient specified.");
        }

        // Update the objective with the variables we pull from the request.
        $objective
            ->setName($request->get('objectiveName'))
            ->setGoalText($request->get('goalText'))
            ->setObjectiveText($request->get('objectiveText'))
            ->setGuidanceNotes($request->get('guidanceNotes'))
            ->setFreqAmount($request->get('freqAmount'))
            ->setFreqKind($request->get('freqKind'));
        // Persist the changes.
        $em = $this->getDoctrine()->getManager();
        $em->persist($objective);
        $em->flush();
        // Redirect them back to the patient page.
        return $this->redirect('../../');
    }


    /**
     * This is the actual creation of a patient.
     * @Route("/create/", name="Create Patient")
     */
    public function createPatient(Request $request)
    {
        // Get all the variables we need.
        $first_name = $request->get('first_name');
        $last_name = $request->get('last_name');
        $medical_id = $request->get('medical_id');
        // Use ternary operator to check whether we got anything for these fields
        // as it's not required on the HTML end.
        $seizure_status = empty($request->get('seizure')) ? false : true;
        $bowel_status = empty($request->get('bowel')) ? false : true;

        // Generate the actual patient and fill in the variables.
        $patient = new Patient();
        $patient
            ->setFirstName($first_name)
            ->setLastName($last_name)
            ->setMedicalId($medical_id)
            ->setSeizure($seizure_status)
            ->setBowel($bowel_status);

        // Submit the patient to the database and persist.
        $em = $this->getDoctrine()->getManager();
        $em->persist($patient);
        $em->flush();

        // Handle objectives - Syed A. ~ May be a little complicated.
        // Let's iterate through the ParameterBag for the request. - More in depth explained above.
        $objectives = [];
        $objwords = ['objectiveName', 'goalText', 'objectiveText', 'guidanceNotes', 'freqAmount', 'freqKind'];
        foreach ($request->request->all() as $k => $o) {
            $objword = substr($k, 0, -1);
            $objnum = intval(substr($k, -1));
            // Let's check if the first part of the key is what we want and the second part is an integer.
            if (in_array($objword, $objwords) && is_numeric(substr($k, -1))) {
                // Add to the objectives table that we'll iterate through and persist.
                $objectives[$objnum][$objword] = $o;
            }
        }

        // Iterate through the objective array we just created and persist them in the database.
        foreach ($objectives as $obj) {
            // Create
            $objective = new Objective();
            $objective
                ->setPatient($patient)
                ->setName($obj['objectiveName'])
                ->setGoalText($obj['goalText'])
                ->setObjectiveText($obj['objectiveText'])
                ->setGuidanceNotes($obj['guidanceNotes'])
                ->setFreqAmount($obj['freqAmount'])
                ->setFreqKind($obj['freqKind']);
            // Persist
            $patient->addObjective($objective);
            $em->persist($objective);
            $em->flush();
        }

        //Send them to the newly created patient's page
        return $this->redirectToRoute('View Patient', array('id' => $patient->getUuid()));
    }

    /**
     * @Route("/patient/{id}/update", name="Update Patient")
     */
    public function updatePatient(Request $request, Patient $patient)
    {
        // Get all the variables we need.
        $first_name = $request->get('first_name');
        $last_name = $request->get('last_name');
        $medical_id = $request->get('medical_id');
        // Use ternary operator to check whether we got anything for these fields
        // as it's not required on the HTML end.
        $seizure_status = empty($request->get('seizure')) ? false : true;
        $bowel_status = empty($request->get('bowel')) ? false : true;

        // Only update if we got all three fields.
        if ($first_name && $last_name && $medical_id) {
            $patient
                ->setFirstName($first_name)
                ->setLastName($last_name)
                ->setMedicalId($medical_id)
                ->setSeizure($seizure_status)
                ->setBowel($bowel_status);
            // Submit the patient and persist.
            $em = $this->getDoctrine()->getManager();
            $em->persist($patient);
            $em->flush();
        }
        // Redirect them back to the patient page.
        return $this->render('patient.html.twig', array('patient' => $patient));
    }

    /**
     * Archive a patient, essentially disabling them.
     * @Route("/{id}/archive", name="Archive Patient")
     */
    public function archivePatient(Request $request, Patient $patient)
    {
        // Disable the user and update the database.
        $patient->setActive(false);
        $em = $this->getDoctrine()->getManager();
        $em->persist($patient);
        $em->flush();
        return $this->redirectToRoute('Patient List');
    }
}