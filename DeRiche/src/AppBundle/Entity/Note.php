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
class Note
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

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
     * @ORM\JoinColumn(name="patient_id", referencedColumnName="uuid")
     */
    private $patient;

    /**
     * The writer of the note.
     *
     * Many Notes have one Staff.
     *
     * @ORM\ManyToOne(targetEntity="Staff", inversedBy="notes")
     * @ORM\JoinColumn(name="staff_id", referencedColumnName="uuid")
     */
    private $staff;

    /**
     * The reviewer of the note.
     * This is null until the note is either accepted or kicked back for corrections.
     *
     * Many Notes have one or no Reviewer.
     *
     * @ORM\ManyToOne(targetEntity="Staff", inversedBy="notes")
     * @ORM\JoinColumn(name="reviewer_id", referencedColumnName="uuid", nullable=true)
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
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    public function __construct()
    {
        $this->comments = new ArrayCollection();
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
}
