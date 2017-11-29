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
        $note->setSubmittedAt(new \DateTime());
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
        $note->setSubmittedAt(new \DateTime());
        $em->persist($note);
        $em->flush();
        return $this->render('reviewer/view.html.twig', array(
            'note' => $note
        ));
    }

    /**
     * @Route("/review/comment/{id}", name="Reviewer comments on a note.")
     */
    public function comment(Request $request, Note $note)
    {
        // Get the comment from the page.
        $em = $this->getDoctrine()->getManager();
        $note->setComment($request->get('comment'));
        // Figure out whether it's a comment submission or just a comment.
        if ($request->get('correctsubmit') !== null) {
            $note->setState($note::KICKED_BACK);
        }
        $em->persist($note);
        $em->flush();
        return $this->redirect('../' . $note->getUuid());
    }

    /**
     * @Route("/export/{id}", name="Reviewer exports a note.")
     */
    public function export(Request $request, Note $note)
    {
        // Let's generate the PDF.
        $mpdf = new \Mpdf\Mpdf();
        $mpdf->SetHeader("Patient Note: " . $note->getPatient()->getFirstName() . " " . $note->getPatient()->getLastName());
        $mpdf->SetTitle('Patient Note: ' . $note->getPatient()->getFirstName() . " " . $note->getPatient()->getLastName());
        $mpdf->WriteHTML("<h3>Patient Name:</h3><p>" . $note->getPatient()->getFirstName() . " " . $note->getPatient()->getLastName() . "</p>");
        $mpdf->WriteHTML("<h3>Note Creation Date:</h3><p>" . $note->getCreatedAt()->format('m/d/Y') . "</p>");
        $mpdf->WriteHTML("<h3>Staff Member:</h3><p>" . $note->getStaff()->getFirstName() . " " . $note->getStaff()->getLastName() . "</p>");
        $mpdf->WriteHTML("<h3>Content:</h3><p>" . $note->getContent() . "</p>");
        $mpdf->WriteHTML('<h3>Signature:</h3><p><img src="data:image/jpg;base64, ' . $note->getSignature() . '"/></p>');
        foreach($note->getForms() as $form) {
            $mpdf->AddPage();
            $mpdf->WriteHTML("<h1>Form: " . $form->getType() . "</h1>"); // TODO: Get actual text instead of enum.
            // TODO: Make it display actual form stuff somehow. Maybe hard code.
            foreach($form->getData() as $k=>$v) {
                if($k == "form_signature_canvas") {
                    $mpdf->WriteHTML('<h3>'.$k.'</h3><p><img src="data:image/jpg;base64, ' . $v . '"/></p>');
                    continue;
                }
                $mpdf->WriteHTML("<h3>".$k."</h3><p>".$v."</p>");
            }
        }
        $mpdf->Output('Note_'.$note->getPatient()->getLastName().'-'.$note->getCreatedAt()->format("m-d").'.pdf', 'I');
    }
}
