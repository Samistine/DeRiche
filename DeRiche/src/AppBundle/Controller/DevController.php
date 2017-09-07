<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class DevController extends Controller
{
    /**
     * @Route("/dev", name="dev page")
     */
    public function indexAction(Request $request)
    {
        // replace this example code with whatever you need
        return $this->render('patient.html.twig');
    }
}