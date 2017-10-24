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
use Doctrine\Common\Persistence\ObjectManager;
use Doctrine\Common\Persistence\ObjectRepository;
use PHPUnit\Framework\TestCase;

class PatientTest extends TestCase {
    public function createPatient() {
        $patient = new Patient();
        $patient->setFirstName('John');
        $patient->setLastName('Adams');
        $patient->setMedicalId(random_int(1, 9000000));
        return $patient;
    }

    public function createObjective($patient, $staff) {
        $note = new Objective();
        $note->setContent("This is a note for Today");
        $note->setModifiedAt(new \DateTime());
        $note->setSubmittedAt(new \DateTime());
        $note->setState(Note::AWAITING_APPROVAL);
        $note->setPatient($patient);
        $note->setStaff($staff);
        $patient->addNote($note); // Attach the note to patient
        $staff->addAuthoredNote($note); // Attach the note to staff.
    }

    public function testPatient() {
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