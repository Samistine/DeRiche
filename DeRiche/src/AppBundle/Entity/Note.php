<?php

namespace AppBundle\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;

/**
 * Note
 *
 * @ORM\Table(name="note")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\NoteRepository")
 * @ORM\HasLifecycleCallbacks()
 */
class Note implements \JsonSerializable
{
    const DRAFT = 10;
    const AWAITING_APPROVAL = 20;
    const KICKED_BACK = 30;
    const ACCEPTED = 40;

    /**
     * @var int
     *
     * @ORM\Column(name="uuid", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $uuid;

    /**
     * @var \DateTime
     * @ORM\Column(name="created_at", type="datetime")
     */
    private $createdAt;

    /**
     * The last time this entity was modified at.
     *
     * @var \DateTime
     * @ORM\Column(name="modified_at", type="datetime")
     */
    private $modifiedAt;

    /**
     * The last time a staff member submitted this for review.
     *
     * @var \DateTime
     * @ORM\Column(name="submitted_at", type="datetime", nullable=true)
     */
    private $submittedAt;

    /**
     * The time this note was accepted by a reviewer at.
     *
     * @var \DateTime
     * @ORM\Column(name="accepted_at", type="datetime", nullable=true)
     */
    private $acceptedAt;

    /**
     * Many Notes have one Patient.
     * @ORM\ManyToOne(targetEntity="Patient", inversedBy="notes")
     * @ORM\JoinColumn(name="patient_uuid", referencedColumnName="uuid")
     */
    private $patient;

    /**
     * The writer of the note.
     *
     * Many Notes have one Staff.
     *
     * @ORM\ManyToOne(targetEntity="Staff", inversedBy="authoredNotes")
     * @ORM\JoinColumn(name="staff_uuid", referencedColumnName="uuid", nullable=false)
     */
    private $staff;

    /**
     * The reviewer of the note.
     * This is null until the note is either accepted or kicked back for corrections.
     *
     * Many Notes have one or no Reviewer.
     *
     * @ORM\ManyToOne(targetEntity="Staff", inversedBy="reviewedNotes")
     * @ORM\JoinColumn(name="reviewer_uuid", referencedColumnName="uuid", nullable=true)
     */
    private $reviewer;

    /**
     * Writer's content for this note.
     *
     * @var string
     * @ORM\Column(name="content", type="text")
     */
    private $content;

    /**
     * One Note has many Comments.
     * @ORM\OneToMany(targetEntity="Comment", mappedBy="note", cascade={"persist",})
     */
    private $comments;

    /**
     * The state the note is in.
     *
     * 0 = Draft
     * 10 = Submitted for Review (Awaiting Review)
     * 20 = Kicked back for errors (Awaiting resubmission)
     * 30 = Accepted into Database (Accepted)
     *
     * @var integer
     * @ORM\Column(name="state", type="integer")
     */
    private $state;

    /**
     * @ORM\PrePersist
     */
    public function setCreateAt()
    {
        $this->createdAt = new \DateTime();
    }

    /**
     * @ORM\PrePersist()
     * @ORM\PreUpdate()
     */
    public function updateModifiedDatetime()
    {
        $this->setModifiedAt(new \DateTime());
    }

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
            'createdAt' => $this->getCreatedAt(),
            'modifiedAt' => $this->getModifiedAt(),
            'submittedAt' => $this->getSubmittedAt(),
            'acceptedAt' => $this->getAcceptedAt(),
            'patient' => $this->getPatient()->getUuid(),
            'staff' => $this->getStaff()->getUuid(),
            'reviewer' => is_object($this->getReviewer()) ? $this->getReviewer()->getUuid() : null,
            'content' => $this->getContent(),
            'comments' => $this->getComments(),
            'state' => $this->getState()
            //'' => $this->get
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
        $this->comments = new \Doctrine\Common\Collections\ArrayCollection();
    }

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
     * Set createdAt
     *
     * @param \DateTime $createdAt
     *
     * @return Note
     */
    public function setCreatedAt($createdAt)
    {
        $this->createdAt = $createdAt;

        return $this;
    }

    /**
     * Get createdAt
     *
     * @return \DateTime
     */
    public function getCreatedAt()
    {
        return $this->createdAt;
    }

    /**
     * Set modifiedAt
     *
     * @param \DateTime $modifiedAt
     *
     * @return Note
     */
    public function setModifiedAt($modifiedAt)
    {
        $this->modifiedAt = $modifiedAt;

        return $this;
    }

    /**
     * Get modifiedAt
     *
     * @return \DateTime
     */
    public function getModifiedAt()
    {
        return $this->modifiedAt;
    }

    /**
     * Set submittedAt
     *
     * @param \DateTime $submittedAt
     *
     * @return Note
     */
    public function setSubmittedAt($submittedAt)
    {
        $this->submittedAt = $submittedAt;

        return $this;
    }

    /**
     * Get submittedAt
     *
     * @return \DateTime
     */
    public function getSubmittedAt()
    {
        return $this->submittedAt;
    }

    /**
     * Set acceptedAt
     *
     * @param \DateTime $acceptedAt
     *
     * @return Note
     */
    public function setAcceptedAt($acceptedAt)
    {
        $this->acceptedAt = $acceptedAt;

        return $this;
    }

    /**
     * Get acceptedAt
     *
     * @return \DateTime
     */
    public function getAcceptedAt()
    {
        return $this->acceptedAt;
    }

    /**
     * Set content
     *
     * @param string $content
     *
     * @return Note
     */
    public function setContent($content)
    {
        $this->content = $content;

        return $this;
    }

    /**
     * Get content
     *
     * @return string
     */
    public function getContent()
    {
        return $this->content;
    }

    /**
     * Set state
     *
     * @param integer $state
     *
     * @return Note
     */
    public function setState($state)
    {
        $this->state = $state;

        return $this;
    }

    /**
     * Get state
     *
     * @return integer
     */
    public function getState()
    {
        return $this->state;
    }

    /**
     * Set patient
     *
     * @param \AppBundle\Entity\Patient $patient
     *
     * @return Note
     */
    public function setPatient(\AppBundle\Entity\Patient $patient = null)
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
     * Set staff
     *
     * @param \AppBundle\Entity\Staff $staff
     *
     * @return Note
     */
    public function setStaff(\AppBundle\Entity\Staff $staff = null)
    {
        $this->staff = $staff;

        return $this;
    }

    /**
     * Get staff
     *
     * @return \AppBundle\Entity\Staff
     */
    public function getStaff()
    {
        return $this->staff;
    }

    /**
     * Set reviewer
     *
     * @param \AppBundle\Entity\Staff $reviewer
     *
     * @return Note
     */
    public function setReviewer(\AppBundle\Entity\Staff $reviewer = null)
    {
        $this->reviewer = $reviewer;

        return $this;
    }

    /**
     * Get reviewer
     *
     * @return \AppBundle\Entity\Staff
     */
    public function getReviewer()
    {
        return $this->reviewer;
    }

    /**
     * Add comment
     *
     * @param \AppBundle\Entity\Comment $comment
     *
     * @return Note
     */
    public function addComment(\AppBundle\Entity\Comment $comment)
    {
        $this->comments[] = $comment;

        return $this;
    }

    /**
     * Remove comment
     *
     * @param \AppBundle\Entity\Comment $comment
     */
    public function removeComment(\AppBundle\Entity\Comment $comment)
    {
        $this->comments->removeElement($comment);
    }

    /**
     * Get comments
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getComments()
    {
        return $this->comments;
    }
}
