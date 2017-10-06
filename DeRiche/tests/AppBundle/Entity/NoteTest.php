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

        $note = new Note();
        $note->setContent("This is a note for Today");
        $note->setModifiedAt(time());
        $note->setState(Note::DRAFT);
        $note->setPatient($patient);
        $patient->addNote($note);//Attach the note to patient

        $comment = new Comment();
        $comment->setContent("This is a comment on today's note");
        $comment->setNote($note);
        $note->addComment($comment);//Attach the comment to the note

        $this->em->persist($patient);
        $this->em->persist($note);
        $this->em->flush();

        $note = $this->em->getRepository(Note::class)->find($note->getUuid());
        $this->em->refresh($note);

        print count($note->getComments());
        print $patient;
    }
}
