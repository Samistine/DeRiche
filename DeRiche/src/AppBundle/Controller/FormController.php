<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Note;
use AppBundle\Entity\Types\FormType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpKernel\Exception\BadRequestHttpException;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;


/**
 * @Route("/form", name="Form Creating Subsystem")
 */
class FormController extends Controller
{
//    /**
//     * @Route("/{uuid}", name="View Forms")
//     */
//    public function indexAction(Note $note)
//    {
//        return $this->render('', array('name' => $note->getContent()));
//    }
//
//
    /**
<<<<<<< HEAD
     * @Route("/{note}/create/{form_type}", name="Create Form")
     */
    public function renderForm(Note $note, $form_type)
=======
     * @Route("/{uuid}", name="Create Form")
     */
    public function formCreate(Request $request, Note $note)
>>>>>>> 90ad998a11c251643563ebe106476a813f6d4731
    {
        if (!FormType::isValueExist($form_type)) {
            throw new BadRequestHttpException("$form_type is not a valid form.");
        }

        return $this->render(FormType::getTwigTemplate($form_type));
    }

    /**
     * @Route("/{note}/submit", name="Save form to note")
     */
    public function submitForm(Note $note, Request $request)
    {
        // $note->
    }
}
