<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Objective;
use AppBundle\Entity\Patient;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

/**
 * @Route("/patients", name="View Patients")
 */
class PatientsController extends Controller
{
    /**
     * @Route("/")
     */
    public function indexAction()
    {
        $activePatients = array();
        $inactivePatients = array();

        $allPatients = $this->getDoctrine()
            ->getRepository(Patient::class)
            ->findAll();

        foreach ($allPatients as $patient) {
            if ($patient->getActive()) array_push($activePatients, $patient);
            else                       array_push($inactivePatients, $patient);
        }

        $patientsQuery = new \stdClass();
        $patientsQuery->active = $activePatients;
        $patientsQuery->inactive = $inactivePatients;

        return $this->render('patients.html.twig', array(
            'patientsQuery' => $patientsQuery
        ));
    }

    /**
     * @Route("/patient/{id}/", name="View Patient")
     */
    public function viewPatient($id)
    {
        $patient = $this->getDoctrine()
            ->getRepository(Patient::class)
            ->find($id);

        if (!$patient) {
            throw $this->createNotFoundException(
                'No patient found for id ' . $id
            );
        }

        return $this->render('patient.html.twig', array('patient' => $patient));
    }

    /**
     * @Route("/create/", name="Create Patient")
     */
    public function createPatient(Request $request)
    {
        $first_name = $request->get('first_name');
        $last_name = $request->get('last_name');
        $medical_id = $request->get('medical_id');

        $patient = new Patient();
        $patient
            ->setFirstName($first_name)
            ->setLastName($last_name)
            ->setMedicalId($medical_id);

        // Submit the patient
        $em = $this->getDoctrine()->getManager();
        $em->persist($patient);
        $em->flush();

        // Handle objectives - Syed A. ~ May be a little complicated.
        // TODO: Add something similar for editing patients.
        // Let's iterate through the ParameterBag for the request.
        $objectives = [];
        $objwords = ['objectiveName', 'goalText', 'objectiveText', 'guidanceNotes', 'freqAmount', 'freqKind'];
        foreach($request->request->all() as $k => $o) {
            $objword = substr($k, 0, -1);
            $objnum = intval(substr($k, -1));
            // Let's check if the first part of the key is what we want and the second part is an integer.
            if(in_array($objword, $objwords) && is_numeric(substr($k, -1))) {
                // Add to the objectives table that we'll iterate through and persist.
                $objectives[$objnum][$objword] = $o;
            }
        }

        // Iterate through the objective array we just created and persist them in the database.
        foreach($objectives as $obj) {
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

        //Get the page the browser was on before coming here
        $referer = $request->headers->get('referer');

        //Send them back
        return $this->redirect($referer);
    }

    /**
     * @Route("/createTestData", name="Create Test Patients")
     */
    // TODO: Move this to a test file, it shouldn't be here.
    public function createTestDate()
    {
        for ($i = 0; $i < 100; $i++) {
            $patient = new Patient();
            $patient
                ->setMedicalId(random_int(0, 999999999999999))
                ->setFirstName(uniqid())
                ->setLastName(uniqid());

            $em = $this->getDoctrine()->getManager();
            $em->persist($patient);
            $em->flush();
        }

        return new Response("Test patients have been created");
    }
}
