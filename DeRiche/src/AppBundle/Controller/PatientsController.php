<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Patient;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

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
        $patients = $this->getDoctrine()
            ->getRepository(Patient::class)
            ->findAll();

        return $this->render('patients.html.twig', array(
            'patients' => $patients
        ));
    }

    /**
     * @Route("/{id}/", name="View Patient")
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
     * @Route("createTestData", name="Create Test Patient")
     */
    public function createTestDate()
    {
        for ($i = 0; $i < 100; $i++) {
            $patient = new Patient();
            $patient
                ->setId(random_int(0, 999999999999999))
                ->setFirstName(uniqid())
                ->setLastName(uniqid())
                ->setInsurance("No Insurance");

            $em = $this->getDoctrine()->getManager();
            $em->persist($patient);
            $em->flush();
        }

        return new Response("Test patients have been created");
    }
}
