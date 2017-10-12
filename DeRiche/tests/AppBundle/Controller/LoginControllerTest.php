<?php

namespace Tests\AppBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;
use Symfony\Component\BrowserKit\Cookie;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Security\Core\Authentication\Token\UsernamePasswordToken;

class LoginControllerTest extends WebTestCase
{
    private $client = null;

    public function setUp()
    {
        $this->client = static::createClient();
    }

    public function testSecuredHello()
    {
        $this->logIn();
        $crawler = $this->client->request('GET', '/admin');

        // This test verifies whether the logged in user can access the /admin without a 302
        // It also verifies that the "Admin Dashboard" text can be verified.
        $this->assertSame(Response::HTTP_OK, $this->client->getResponse()->getStatusCode());
        $this->assertSame('Admin Dashboard', $crawler->filter('h2')->text());
    }

    private function logIn()
    {
        $session = $this->client->getContainer()->get('session');
        $firewallContext = 'main';
        // Create a test "user" token that has the role of admin so that it can view the correct page.
        $token = new UsernamePasswordToken('admin', 'test', $firewallContext, array('ROLE_ADMIN'));
        $session->set('_security_'.$firewallContext, serialize($token));
        $session->save();

        $cookie = new Cookie($session->getName(), $session->getId());
        $this->client->getCookieJar()->set($cookie);
    }
}