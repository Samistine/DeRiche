<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class IntIncidentController extends Controller
{
    /**
     * @Route("/internal_incident", name="internal incident page")
     */
    public function indexAction(Request $request)
    {
        return $this->render('internal_incident.html.twig');
    }
}