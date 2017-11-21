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
     * @Route("{note}/create/{form_type}", name="Create Form")
     */
    public function renderForm(Note $note, $form_type)
    {
        if (!FormType::isValueExist($form_type)) {
            throw new BadRequestHttpException("$form_type is not a valid form.");
        }

        return $this->render(FormType::getTwigTemplate($form_type),
            array('note' => $note, 'type' => $form_type)
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

        // TODO: FINISH THIS PORTION OF THE CONTROLLER. ALSO we need to edit the template for notes.
        // That way we can show the added forms and give them the ability to remove it if the note is a draft.
        // etc, etc. Also - need to fix note checkboxes.
        // Create a new form using the data we have and persist it.
        $form = new Form();
        // Set some default values
        $form->setNote($note);
        $form->setType($patient);

        // Submit the draft so we can update it later
        $em = $this->getDoctrine()->getManager();
        $em->persist($form);
        $em->flush();
        die(var_dump($formData));
    }
}