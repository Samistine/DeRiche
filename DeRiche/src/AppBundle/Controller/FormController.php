<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Note;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
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
     * @Route("/{id}", name="Create Form")
     */
    public function formCreate(Note $note)
    {

    }
}
