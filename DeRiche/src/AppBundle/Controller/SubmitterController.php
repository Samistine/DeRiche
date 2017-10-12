<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Patient;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/notes", name="Submitter Subsystem")
 */
class SubmitterController extends Controller
{
    /**
     * @Route("/", name="Reviewer Main Page")
     */
    public function indexAction()
    {
        return $this->render('notes/home.html.twig', array('name' => ''));
    }

    /**
     * @Route("/findpatient/", name="Reviewer Find Patient")
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
            return $this->redirect('../patient/' . $patients[0]->getUuid());
        }

        //return $this->render('notes/home.html.twig', array('name' => $patients));
    }

    /**
     * @Route("/patient/{id}", name="Reviewer View Patient")
     */
    public function patient(Request $request, Patient $patient)
    {
        return $this->render('notes/patient.html.twig', array(
            'patient' => $patient
        ));
    }

}
