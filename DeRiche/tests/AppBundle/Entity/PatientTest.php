<?php
/**
 * Created by PhpStorm.
 * User: sahmed6
 * Date: 10/17/2017
 * Time: 6:38 PM
 */

namespace Tests\AppBundle\Entity;

use AppBundle\Entity\Individual;
use AppBundle\Entity\Objective;
use Doctrine\Common\Persistence\ObjectManager;
use Doctrine\Common\Persistence\ObjectRepository;
use PHPUnit\Framework\TestCase;

class IndividualTest extends TestCase {
    public function createIndividual() {
        $patient = new Individual();
        $patient->setFirstName('John');
        $patient->setLastName('Adams');
        $patient->setMedicalId(random_int(1, 9000000));
        return $patient;
    }

    public function createObjective($patient) {
        $objective = new Objective();
        $objective
            ->setIndividual($patient)
            ->setName('O1')
            ->setGoalText('GoalText')
            ->setObjectiveText('ObjectiveText')
            ->setGuidanceNotes('GuidanceNotes')
            ->setFreqAmount(2)
            ->setFreqKind('/Week');
        return $objective;
    }

    public function testIndividual() {
        // Create the patient and assign it to a variable.
        $patient = $this->createIndividual();
        // Create the objectives and attach it to the patient.
        $objective = $this->createObjective($patient);
        $patient->addObjective($objective);

        // Now that we've created all the variables we need, let's actually test.

        // Make sure the patient exists.
        $this->assertEquals($patient->getFirstName(), 'John');
        $this->assertEquals($patient->getLastName(), 'Adams');

        // Make sure the patient has the objective.
        $this->assertEquals($objective, $patient->getObjectives()[0]);
        // Make sure the objective is assigned to the patient.
        $this->assertEquals($objective->getIndividual(), $patient);
        // Make sure the objective has the right frequency.
        $this->assertEquals("/Week", $objective->getFreqKind());
    }
}