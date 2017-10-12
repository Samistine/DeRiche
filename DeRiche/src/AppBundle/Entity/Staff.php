<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Staff
 *
 * @ORM\Table(name="staff")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\StaffRepository")
 */
class Staff
{


    /**
     * Constructor
     */
    public function __construct()
    {
        $this->authoredNotes = new \Doctrine\Common\Collections\ArrayCollection();
        $this->reviewedNotes = new \Doctrine\Common\Collections\ArrayCollection();
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
     * Add authoredNote
     *
     * @param \AppBundle\Entity\Note $authoredNote
     *
     * @return Staff
     */
    public function addAuthoredNote(\AppBundle\Entity\Note $authoredNote)
    {
        $this->authoredNotes[] = $authoredNote;

        return $this;
    }

    /**
     * Remove authoredNote
     *
     * @param \AppBundle\Entity\Note $authoredNote
     */
    public function removeAuthoredNote(\AppBundle\Entity\Note $authoredNote)
    {
        $this->authoredNotes->removeElement($authoredNote);
    }

    /**
     * Get authoredNotes
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getAuthoredNotes()
    {
        return $this->authoredNotes;
    }

    /**
     * Add reviewedNote
     *
     * @param \AppBundle\Entity\Note $reviewedNote
     *
     * @return Staff
     */
    public function addReviewedNote(\AppBundle\Entity\Note $reviewedNote)
    {
        $this->reviewedNotes[] = $reviewedNote;

        return $this;
    }

    /**
     * Remove reviewedNote
     *
     * @param \AppBundle\Entity\Note $reviewedNote
     */
    public function removeReviewedNote(\AppBundle\Entity\Note $reviewedNote)
    {
        $this->reviewedNotes->removeElement($reviewedNote);
    }

    /**
     * Get reviewedNotes
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getReviewedNotes()
    {
        return $this->reviewedNotes;
    }
}
