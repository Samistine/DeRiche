<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Note;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
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
     * @Route("/{uuid}", name="Create Form")
     */
    public function formCreate(Request $request, Note $note)
    {

    }
}
