<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Note;
use AppBundle\Entity\Form;
use AppBundle\Entity\Types\FormType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpKernel\Exception\BadRequestHttpException;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/form/", name="Form Creating Subsystem")
 */
class FormController extends Controller
{
    /**
     * @Route("/", name="home form page")
     */
    public function indexAction(Request $request)
    {
        return $this->render('home.html.twig');
    }

    /**
     * @Route("{note}/create/{form_type}", name="forms_create")
     */
    public function renderForm(Note $note, $form_type)
    {
        if (!FormType::isValueExist($form_type)) {
            throw new BadRequestHttpException("$form_type is not a valid form.");
        }

        return $this->render(FormType::getTwigTemplate($form_type),
            array('note' => $note, 'type' => $form_type, 'data' => array())
        );
    }

    /**
     * @Route("{note}/submit/{form_type}", name="Save form to note")
     */
    public function submitForm(Note $note, Request $request, $form_type)
    {
        if (!FormType::isValueExist($form_type)) {
            throw new BadRequestHttpException("$form_type is not a valid form.");
        }

        // Iterate through a ParameterBag and store in formData
        $formData = array();
        foreach ($request->request->all() as $k => $o) {
            // Check if it starts with "form_" - if it does then it's part of the form data.
            if (0 === strpos($k, 'form_')) {
                // Add to the formData array so we can check it later.
                $formData[$k] = $o;
            }
        }

        // Create a new form using the data we have and persist it.
        $form = new Form();
        $form->setNote($note);
        $form->setType($form_type);
        $form->setData($formData);
        $em = $this->getDoctrine()->getManager();
        $em->persist($form);
        $em->flush();

        return $this->redirect('/note/create/' . $note->getPatient()->getUuid());
    }

    /**
     * @Route("view/{form}", name="View Form")
     */
    public function viewForm(Form $form)
    {
        return $this->render(FormType::getTwigTemplate($form->getType()),
            array('data' => $form->getData(), 'note' => $form->getNote(),
                'type' => $form->getType())
        );
    }

    /**
     * @Route("delete/{form}", name="Delete Form")
     */
    public function deleteForm(Form $form=null, Request $request)
    {
        $n = $form->getNote();
        // We only delete forms from drafts.
        if ($form && $n->getState() == $n::DRAFT) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($form);
            $em->flush();
        }

        // Send them back
        $referrer = $request->headers->get('referer');
        return $this->redirect($referrer);
    }
}