<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Patient;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/patient/")
 */
class PatientController extends Controller
{
    public function indexAction($name)
    {
        return $this->render('', array('name' => $name));
    }

    /**
     * @Route("create", name="Create Test Patient")
     */
    public function create()
    {
        $patient = new Patient();
        $patient
            ->setId(1)
            ->setFirstName("John")
            ->setLastName("Deet")
            ->setInsurance("No Insurance");

        $em = $this->getDoctrine()->getManager();
        $em->persist($patient);
        $em->flush();

        return new Response("Test patient has been created");
    }

    /**
     * @Route("{id}/view", name="View Patient")
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
}
