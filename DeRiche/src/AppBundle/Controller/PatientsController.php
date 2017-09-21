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
        $patient = $this->getDoctrine()
            ->getRepository(Patient::class)
            ->findAll();

        return $this->render('patients.html.twig', array(
            'patients' => $patient
        ));
    }
}
