<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

class BodyCheckController extends Controller
{
    /**
     * @Route("/bodycheck", name="body check page")
     */
    public function indexAction(Request $request)
    {
        return $this->render('bodycheck.html.twig');
    }

    /**
     * @Route("/BodyCheckServlet", name="body check page submit")
     */
    public function test(Request $request)
    {
        return new Response(var_export($request->request), 200, array('content-type' => 'json'));
    }
}