<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Objective
 *
 * @ORM\Table(name="objective")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\ObjectiveRepository")
 */
class Objective
{
    /**
     * @var int
     *
     * @ORM\Column(name="uuid", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $uuid;

    /**
     * The patient of this Objective.
     *
     * Many Objectives have one Patient.
     *
     * @ORM\ManyToOne(targetEntity="Patient", inversedBy="objectives")
     * @ORM\JoinColumn(name="patient_uuid", referencedColumnName="uuid", nullable=false)
     */
    private $patient;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", length=255)
     */
    private $name;

    /**
     * @var string
     *
     * @ORM\Column(name="goal_text", type="text")
     */
    private $goalText;

    /**
     * @var string
     *
     * @ORM\Column(name="objective_text", type="text")
     */
    private $objectiveText;

    /**
     * @var string
     *
     * @ORM\Column(name="guidance_notes", type="text")
     */
    private $guidanceNotes;

    /**
     * @var int
     *
     * @ORM\Column(name="freq_amount", type="integer")
     */
    private $freqAmount;

    /**
     * @var string
     *
     * @ORM\Column(name="freq_kind", type="string", length=255)
     */
    private $freqKind;

    /**
     * Get uuid
     *
     * @return integer
     */
    public function getUuid()
    {
        return $this->uuid;
    }

    /**
     * Set name
     *
     * @param string $name
     *
     * @return Objective
     */
    public function setName($name)
    {
        $this->name = $name;

        return $this;
    }

    /**
     * Get name
     *
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * Set goalText
     *
     * @param string $goalText
     *
     * @return Objective
     */
    public function setGoalText($goalText)
    {
        $this->goalText = $goalText;

        return $this;
    }

    /**
     * Get goalText
     *
     * @return string
     */
    public function getGoalText()
    {
        return $this->goalText;
    }

    /**
     * Set objectiveText
     *
     * @param string $objectiveText
     *
     * @return Objective
     */
    public function setObjectiveText($objectiveText)
    {
        $this->objectiveText = $objectiveText;

        return $this;
    }

    /**
     * Get objectiveText
     *
     * @return string
     */
    public function getObjectiveText()
    {
        return $this->objectiveText;
    }

    /**
     * Set freqAmount
     *
     * @param integer $freqAmount
     *
     * @return Objective
     */
    public function setFreqAmount($freqAmount)
    {
        $this->freqAmount = $freqAmount;

        return $this;
    }

    /**
     * Get freqAmount
     *
     * @return integer
     */
    public function getFreqAmount()
    {
        return $this->freqAmount;
    }

    /**
     * Set freqKind
     *
     * @param string $freqKind
     *
     * "/Day", "/Week", "/Month" (Exactly as stated)
     * @return Objective
     */
    public function setFreqKind($freqKind)
    {
        $this->freqKind = $freqKind;

        return $this;
    }

    /**
     * Get freqKind
     *
     * @return string
     */
    public function getFreqKind()
    {
        return $this->freqKind;
    }

    /**
     * Set patient
     *
     * @param \AppBundle\Entity\Patient $patient
     *
     * @return Objective
     */
    public function setPatient(\AppBundle\Entity\Patient $patient)
    {
        $this->patient = $patient;

        return $this;
    }

    /**
     * Get patient
     *
     * @return \AppBundle\Entity\Patient
     */
    public function getPatient()
    {
        return $this->patient;
    }

    /**
     * Set guidanceNotes
     *
     * @param string $guidanceNotes
     *
     * @return Objective
     */
    public function setGuidanceNotes($guidanceNotes)
    {
        $this->guidanceNotes = $guidanceNotes;

        return $this;
    }

    /**
     * Get guidanceNotes
     *
     * @return string
     */
    public function getGuidanceNotes()
    {
        return $this->guidanceNotes;
    }
}