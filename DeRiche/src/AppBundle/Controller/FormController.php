<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Note;
use AppBundle\Entity\Types\FormType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpKernel\Exception\BadRequestHttpException;
use Symfony\Component\Routing\Annotation\Route;

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
     * @Route("/{note}/create/{form_type}", name="Create Form")
     */
    public function renderForm(Note $note, $form_type)
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
