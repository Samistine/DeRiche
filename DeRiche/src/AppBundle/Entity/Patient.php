<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Patient
 *
 * @ORM\Table(name="patient")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\PatientRepository")
 */
class Patient implements \JsonSerializable
{
    /**
     * @ORM\Id
     * @ORM\Column(name="uuid", type="guid")
     * @ORM\GeneratedValue(strategy="UUID")
     */
    private $uuid;

    /**
     * Protected field, don't show to unauthorized users.
     *
     * @var integer
     *
     * @ORM\Column(name="medicalId", type="integer", unique=true)
     */
    private $medicalId;

    /**
     * @var string
     *
     * @ORM\Column(name="firstName", type="string", length=255, nullable=false)
     */
    private $firstName;

    /**
     * @var string
     *
     * @ORM\Column(name="lastName", type="string", length=255, nullable=false)
     */
    private $lastName;

    /**
     * @var boolean
     *
     * @ORM\Column(name="active", type="boolean", nullable=false)
     */
    private $active = true;

    /**
     * One Patient has many Notes.
     * @ORM\OneToMany(targetEntity="Note", mappedBy="notes")
     */
    private $notes;

    /**
     * Specify data which should be serialized to JSON
     * @link http://php.net/manual/en/jsonserializable.jsonserialize.php
     * @return mixed data which can be serialized by <b>json_encode</b>,
     * which is a value of any type other than a resource.
     * @since 5.4.0
     */
    function jsonSerialize()
    {
        return [
            'uuid' => $this->getUuid(),
            'firstName' => $this->getFirstName(),
            'lastName' => $this->getLastName(),
            'medicalId' => $this->getMedicalId(),
            'notes' => $this->getNotes()->toArray()
        ];
    }

    public function __toString()
    {
        return json_encode($this, JSON_PRETTY_PRINT);
    }

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->notes = new \Doctrine\Common\Collections\ArrayCollection();
    }

    /**
     * Get uuid
     *
     * @return guid
     */
    public function getUuid()
    {
        return $this->uuid;
    }

    /**
     * Set medicalId
     *
     * @param integer $medicalId
     *
     * @return Patient
     */
    public function setMedicalId($medicalId)
    {
        $this->medicalId = $medicalId;

        return $this;
    }

    /**
     * Get medicalId
     *
     * @return integer
     */
    public function getMedicalId()
    {
        return $this->medicalId;
    }

    /**
     * Set firstName
     *
     * @param string $firstName
     *
     * @return Patient
     */
    public function setFirstName($firstName)
    {
        $this->firstName = $firstName;

        return $this;
    }

    /**
     * Get firstName
     *
     * @return string
     */
    public function getFirstName()
    {
        return $this->firstName;
    }

    /**
     * Set lastName
     *
     * @param string $lastName
     *
     * @return Patient
     */
    public function setLastName($lastName)
    {
        $this->lastName = $lastName;

        return $this;
    }

    /**
     * Get lastName
     *
     * @return string
     */
    public function getLastName()
    {
        return $this->lastName;
    }

    /**
     * Set active
     *
     * @param boolean $active
     *
     * @return Patient
     */
    public function setActive($active)
    {
        $this->active = $active;

        return $this;
    }

    /**
     * Get active
     *
     * @return boolean
     */
    public function getActive()
    {
        return $this->active;
    }

    /**
     * Add note
     *
     * @param \AppBundle\Entity\Note $note
     *
     * @return Patient
     */
    public function addNote(\AppBundle\Entity\Note $note)
    {
        $this->notes[] = $note;

        return $this;
    }

    /**
     * Remove note
     *
     * @param \AppBundle\Entity\Note $note
     */
    public function removeNote(\AppBundle\Entity\Note $note)
    {
        $this->notes->removeElement($note);
    }

    /**
     * Get notes
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getNotes()
    {
        return $this->notes;
    }
}
