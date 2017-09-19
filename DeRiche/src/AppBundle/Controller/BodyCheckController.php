<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class BodyCheckController extends Controller
{
    /**
     * @Route("/bodycheck", name="body check page")
     */
    public function indexAction(Request $request)
    {
        return $this->render('bodycheck.html.twig');
    }
}