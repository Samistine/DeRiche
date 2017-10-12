<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Note;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/reviewer", name="Reviewer Subsystem")
 */
class ReviewerController extends Controller
{
    /**
     * @Route("/", name="Reviewer Home")
     */
    public function indexAction()
    {
        $notes = $this->getDoctrine()
            ->getRepository(Note::class)
            ->findBy(['state' => Note::AWAITING_APPROVAL]);

        return $this->render('reviewer/notes.html.twig', array(
            'notes' => $notes
        ));
    }
}
