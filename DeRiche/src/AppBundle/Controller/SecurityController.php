<?php
/**
 * Created by PhpStorm.
 * User: sahmed6
 * Date: 10/3/2017
 * Time: 6:47 PM
 */

namespace AppBundle\Controller;

use AppBundle\Entity\Staff;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use AppBundle\Entity\User;
use AppBundle\Form\UserType;

class SecurityController extends Controller
{
    /**
     * @Route("/login", name="Login Form")
     */
    public function loginAction(Request $request, AuthenticationUtils $authUtils)
    {
        // get the login error if there is one
        $error = $authUtils->getLastAuthenticationError();

        // last username entered by the user
        $lastUsername = $authUtils->getLastUsername();

        return $this->render('login.html.twig', array(
            'last_username' => $lastUsername,
            'error' => $error,
        ));
    }
}