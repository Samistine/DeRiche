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
 * Main route for auditing tools, primarily, auditing by patient, daterange and doing a full backup.
 * @Route("/audit/")
 */
class AuditController extends Controller
{

    /**
     * Main index for the audit panel which just loads the template.
     * @Route(name="Audit Panel")
     */
    public function indexAction(Request $request, UserPasswordEncoderInterface $passwordEncoder)
    {
        return $this->render('admin/audit.html.twig');
    }

    /**
     * Route for when the admin wants to audit by patient.
     * @Route("patient", name="Audit By Patient")
     */
    public function auditPatient(Request $request)
    {
        // We get the name that the Admin wants to look up.
        $patientName = $request->get('name');
        // We then separate the name into two so we can search by first and last name.
        $names = explode(' ', $patientName, 2);
        $givenName = $names[0];
        $familyName = isset($names[1]) ? $names[1] : null;
        // The actual look up function.
        $patients = $this->getDoctrine()
            ->getRepository(Patient::class)
            ->findBy([
                'firstName' => $givenName,
                'lastName' => $familyName
            ]);
        // We then make sure that only one patient has that name.
        $count = count($patients);
        if ($count === 0) {
            // If a patient was not found with that name.
            throw $this->createNotFoundException('No individual found for ' . $patientName);
        }
        if ($count === 1) {
            // If a patient was found then we render the template with the notes.
            return $this->render('admin/audit.html.twig', array('notes' => $patients[0]->getNotes(),
                'patient' => $patients[0]->getFirstName() . ' ' . $patients[0]->getLastName()));
        }
    }

    /**
     * This is for when an admin wants all notes in a certain time period.
     * We use the Repository file for Notes and added a getBetweenDates function which makes this simpler.
     * @Route("dates", name="Audit By DateRange")
     */
    public function auditDates(Request $request)
    {
        // We get the variables and convert them into Datetime objects.
        $startDate = \DateTime::createFromFormat('m/d/Y', $request->get('start'));
        $endDate = \DateTime::createFromFormat('m/d/Y', $request->get('end'));
        // Find the notes between that date.
        $notes = $this->getDoctrine()
            ->getRepository(Note::class)
            ->getBetweenDates($startDate, $endDate);
        // Return the template
        return $this->render('admin/audit.html.twig', array('notes' => $notes));
    }

    /**
     * This is when
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

            $response = new Response();
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
