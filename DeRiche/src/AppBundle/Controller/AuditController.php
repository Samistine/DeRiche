<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Note;
use AppBundle\Entity\Patient;
use AppBundle\Entity\User;
use AppBundle\Form\UserType;
use Doctrine\ORM\EntityManager;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpKernel\Exception\BadRequestHttpException;
use Symfony\Component\HttpKernel\Exception\ConflictHttpException;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

/**
 * @Route("/audit/")
 */
class AuditController extends Controller
{

    /**
     * @Route(name="Audit Panel")
     */
    public function indexAction(Request $request, UserPasswordEncoderInterface $passwordEncoder)
    {
        return $this->render('admin/audit.html.twig');
    }

    /**
     * @Route("patient", name="Audit By User")
     */
    public function auditPatient(Request $request)
    {
        $patientName = $request->get('name');
        $names = explode(' ', $patientName, 2);
        $givenName = $names[0];
        $familyName = isset($names[1]) ? $names[1] : null;
        $patients = $this->getDoctrine()
            ->getRepository(Patient::class)
            ->findBy([
                'firstName' => $givenName,
                'lastName' => $familyName
            ]);
        $count = count($patients);
        if ($count === 0) {
            throw $this->createNotFoundException('No individual found for ' . $patientName);
        }
        if ($count === 1) {
            return $this->render('admin/audit.html.twig', array('notes' => $patients[0]->getNotes(),
                'patient' => $patients[0]->getFirstName() . ' ' . $patients[0]->getLastName()));
        }
    }

    /**
     * @Route("dates", name="Audit By DateRange")
     */
    public function auditDates(Request $request)
    {
        $startDate = \DateTime::createFromFormat('m/d/Y', $request->get('start'));
        $endDate = \DateTime::createFromFormat('m/d/Y', $request->get('end'));

        $notes = $this->getDoctrine()
            ->getRepository(Note::class)
            ->getBetweenDates($startDate, $endDate);
        return $this->render('admin/audit.html.twig', array('notes' => $notes));
    }

    /**
     * @Route("db-backup", name="Audit - Full Backup")
     */
    public function dbBackup(EntityManagerInterface $em, Request $request)
    {
        $dbuser = $em->getConnection()->getUsername();
        $dbpasswd = $em->getConnection()->getPassword();
        $database = $em->getConnection()->getDatabase();
        if(substr($database, -7) == ".sqlite") {
            // Let's do a SQLite backup. For the dev environment (tested).
            $output = shell_exec("sqlite3 $database .dump");

            $response = new RespAonse();
            $response->headers->set('Content-Type', 'text/plain');
            $response->headers->set('Content-disposition', 'attachment;filename=full_backup.sql');
            $response->setContent($output);
            $response->setStatusCode(Response::HTTP_OK);
            $response->sendHeaders();
            $response->send();
        }
        // Actual MySQL Dump function (untested).
        $output = shell_exec("mysqldump -u $dbuser --password=$dbpasswd $database | gzip --best");

        $response = new Response();
        $response->headers->set('Content-Type', 'application/x-gzip');
        $response->headers->set('Content-disposition', 'attachment;filename=full_backup.sql.gz');
        $response->setContent($output);
        $response->setStatusCode(Response::HTTP_OK);
        $response->sendHeaders();
        $response->send();
    }
}
