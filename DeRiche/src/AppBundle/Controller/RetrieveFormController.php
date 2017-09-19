<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class RetrieveFormController extends Controller
{
    /**
     * @Route("/formretrieval", name="form retrieval page")
     */
    public function indexAction(Request $request)
    {
        return $this->render('retrieveform.html.twig');
    }
}