<?php
/**
 * Created by PhpStorm.
 * User: samuel
 * Date: 10/3/17
 * Time: 6:35 PM
 */

namespace Tests\AppBundle\Entity;

use AppBundle\Entity\Comment;
use AppBundle\Entity\Note;
use AppBundle\Entity\Patient;
use AppBundle\Entity\Staff;
use AppBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Test\KernelTestCase;

class NoteTest extends KernelTestCase
//TODO: Don't use KernelTestCase see here: https://symfony.com/doc/current/testing/doctrine.html
{
    /**
     * @var \Doctrine\ORM\EntityManager
     */
    private $em;

    /**
     * {@inheritDoc}
     */
    protected function setUp()
    {
        self::bootKernel();

        $this->em = static::$kernel->getContainer()
            ->get('doctrine')
            ->getManager();
    }

    /**
     * {@inheritDoc}
     */
    protected function tearDown()
    {
        parent::tearDown();

        $this->em->close();
        $this->em = null; // avoid memory leaks
    }


    public function testAddPatientWithNote()
    {
        $patient = new Patient();
        $patient->setFirstName('John');
        $patient->setLastName('Adams');
        $patient->setMedicalId(random_int(1, 9000000));

        $staff = $note = $this->em->getRepository(User::class)->findOneBy(['username' => 'admin']);
        if ($staff === null) {
            $staff = new User();
            $staff->setFirstName('John');
            $staff->setLastName('Oliver');
            $staff->setEmail('john@oliver.com');
            $staff->setIsActive(true);
            $staff->setRoles(['ROLE_ADMIN']);

            $this->em->get
            // 3) Encode the password (you could also do this via Doctrine listener)
            $password = $passwordEncoder->encodePassword($user, $user->getPlainPassword());
            $user->setPassword($password);

            // 4) save the User!
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
        }

        Passw

        // $patient->setFirstName('John');
        // $patient->setLastName('Adams');
        // $patient->setMedicalId(random_int(1, 9000000));

        $note = new Note();
        $note->setContent("This is a note for Today");
        $note->setModifiedAt(new \DateTime());
        $note->setSubmittedAt(new \DateTime());
        $note->setState(Note::AWAITING_APPROVAL);
        $note->setPatient($patient);
        $note->setStaff($staff);
        $patient->addNote($note);//Attach the note to patient

        $comment = new Comment();
        $comment->setContent("This is a comment on today's note");
        $comment->setNote($note);
        $note->addComment($comment);//Attach the comment to the note

        $this->em->persist($staff);
        $this->em->persist($patient);
        $this->em->persist($note);
        $this->em->flush();

        $note = $this->em->getRepository(Note::class)->find($note->getUuid());
        $this->em->refresh($note);

        print count($note->getComments());
        print $patient;
    }
}
