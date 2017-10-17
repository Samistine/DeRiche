<?php
/**
 * Created by PhpStorm.
 * User: sahmed6
 * Date: 10/17/2017
 * Time: 6:38 PM
 */

namespace Tests\AppBundle\Entity;

use AppBundle\Entity\Patient;
use AppBundle\Entity\User;
use AppBundle\Entity\Note;
use AppBundle\Entity\Comment;
use AppBundle\Salary\SalaryCalculator;
use Doctrine\Common\Persistence\ObjectManager;
use Doctrine\Common\Persistence\ObjectRepository;
use PHPUnit\Framework\TestCase;

class NoteTest extends TestCase {
    public function createPatient() {
        $patient = new Patient();
        $patient->setFirstName('John');
        $patient->setLastName('Adams');
        $patient->setMedicalId(random_int(1, 9000000));
        return $patient;
    }

    public function createStaff() {
        $staff = new User();
        $staff->setFirstName('John');
        $staff->setLastName('Oliver');
        $staff->setIsActive(true);
        $staff->setRoles(['ROLE_ADMIN']);
        return $staff;
    }

    public function createNote($patient, $staff) {
        $note = new Note();
        $note->setContent("This is a note for Today");
        $note->setModifiedAt(new \DateTime());
        $note->setSubmittedAt(new \DateTime());
        $note->setState(Note::AWAITING_APPROVAL);
        $note->setPatient($patient);
        $note->setStaff($staff);
        $patient->addNote($note); // Attach the note to patient
        $staff->addAuthoredNote($note); // Attach the note to staff.

        // While this is it's own entity, we'll keep it in this function since it's only used for notes.
        $comment = new Comment();
        $comment->setContent("Test Comment - Assert Later");
        $comment->setNote($note);
        $note->addComment($comment); // Attach the comment to the note
        return $note;
    }

    public function testNote() {
        // Create the patient and assign it to a variable.
        $patient = $this->createPatient();
        // Create the staff member and assign it to a variable.
        $staff = $this->createStaff();
        // Create the note and assign it to a variable.
        $note = $this->createNote($patient, $staff);

        // Now that we've created all the variables we need, let's actually test.
        
        // Make sure the note has the same staff member.
        $this->assertEquals($staff, $note->getStaff());
        // Make sure the note has the same patient.
        $this->assertEquals($patient, $note->getPatient());
        // Make sure the patient has the note.
        $this->assertEquals($note, $patient->getNotes()[0]);
        // Make sure the staff member has the note.
        $this->assertEquals($note, $staff->getAuthoredNotes()[0]);
        // Make sure the note has the comment.
        $this->assertEquals("Test Comment - Assert Later", $note->getComments()[0]->getContent());
    }
}