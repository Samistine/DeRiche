<?php

namespace AppBundle\Controller;

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

        // Handle objectives - Syed A.
        // TODO: Add something similar for editing patients.


        $em = $this->getDoctrine()->getManager();
        $em->persist($patient);
        $em->flush();

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
