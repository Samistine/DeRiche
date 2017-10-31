<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Note;
use Symfony\Component\HttpFoundation\Request;
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
            ->findAll();

        return $this->render('reviewer/notes.html.twig', array(
            'notes' => $notes
        ));
    }

    /**
     * @Route("/review/{id}", name="Reviewer reviews a note.")
     */
    public function review(Request $request, Note $note)
    {
        return $this->render('reviewer/view.html.twig', array(
            'note' => $note
        ));
    }

    /**
     * @Route("/review/approve/{id}", name="Reviewer approves a note.")
     */
    public function approve(Request $request, Note $note)
    {
        // Approve and submit to database.
        $em = $this->getDoctrine()->getManager();
        $note->setState($note::ACCEPTED);
        $em->persist($note);
        $em->flush();
        return $this->render('reviewer/view.html.twig', array(
            'note' => $note
        ));
    }

    /**
     * @Route("/review/edit/{id}", name="Reviewer edits and approves a note.")
     */
    public function edit(Request $request, Note $note)
    {
        // Get edited content then submit to database.
        $em = $this->getDoctrine()->getManager();
        $note->setContent($request->get('content'));
        $note->setState($note::ACCEPTED);
        $em->persist($note);
        $em->flush();
        return $this->render('reviewer/view.html.twig', array(
            'note' => $note
        ));
    }
}
