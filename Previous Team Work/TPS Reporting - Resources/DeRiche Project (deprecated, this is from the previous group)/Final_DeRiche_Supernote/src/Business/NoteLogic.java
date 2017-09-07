/*
 * CIST2931-Advanced Systems Project
 * Started: Spring 2016
 * Team 1 AKA Code Monkeys:
 *      Team Lead AKA who to blame for things: Triston Gregoire
 *      Team: Bonnie Falk, Bunmi Bamiro, Kristen Dawes, Natacha Mosala, Taylor Smith
*/
package Business;

import Data_Access.NoteDBConnect;
import Data_Access.UserDBConnect;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class handles the NoteLogic changes such as creating a note, updating a 
 * note,
 * @author Triston_Gregoire
 */
public class NoteLogic {
    private String text = null;             //main text from note
    private int noteID = -1;
    private String comment = null;          //writer comment for note
    private int participantID = -1;
    private int userID = -1;
    private int isSubmitted = -1;
    private int isAccepted = -1;
    private int timeReviewerAccepted = -1;
    private int noteReviewerAccepted = -1;
    private Timestamp timeStarted;
    private Timestamp timeSubmitted;
    private Timestamp lastUpdated;
    private Timestamp timeAccepted;
    private ParticipantLogic pl = new ParticipantLogic();
    private UserLogic ul = new UserLogic();

    /**
     * @return the pl
     */
    public ParticipantLogic getPl() {
        return pl;
    }

    /**
     * @param pl the pl to set
     */
    public void setPl(ParticipantLogic pl) {
        this.pl = pl;
    }

    /**
     * @return the ul
     */
    public UserLogic getUl() {
        return ul;
    }

    /**
     * @param ul the ul to set
     */
    public void setUl(UserLogic ul) {
        this.ul = ul;
    }
    

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the noteID
     */
    public int getNoteID() {
        return noteID;
    }

    /**
     * @param noteID the noteID to set
     */
    public void setNoteID(int noteID) {
        System.out.println("I'm in NoteLogic, the noteID is: " + noteID);
        this.noteID = noteID;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the participantID
     */
    public int getParticipantID() {
        return participantID;
    }

    /**
     * @param participantID the participantID to set
     */
    public void setParticipantID(int participantID) {
        this.participantID = participantID;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    

    /**
     * @return the isSubmitted
     */
    public int getIsSubmitted() {
        return isSubmitted;
    }

    /**
     * @param isSubmitted the isSubmitted to set
     */
    public void setIsSubmitted(int isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    /**
     * @return the timeReviewerAccepted
     */
    public int getTimeReviewerAccepted() {
        return timeReviewerAccepted;
    }

    /**
     * @param timeReviewerAccepted the timeReviewerAccepted to set
     */
    public void setTimeReviewerAccepted(int timeReviewerAccepted) {
        this.timeReviewerAccepted = timeReviewerAccepted;
    }

    /**
     * @return the noteReviewerAccepted
     */
    public int getNoteReviewerAccepted() {
        return noteReviewerAccepted;
    }

    /**
     * @param noteReviewerAccepted the noteReviewerAccepted to set
     */
    public void setNoteReviewerAccepted(int noteReviewerAccepted) {
        this.noteReviewerAccepted = noteReviewerAccepted;
    }

    /**
     * @return the timeStarted
     */
    public Timestamp getTimeStarted() {
        return timeStarted;
    }

    /**
     * @param timeStarted the timeStarted to set
     */
    public void setTimeStarted(Timestamp timeStarted) {
        this.timeStarted = timeStarted;
    }

    /**
     * @return the timeSubmitted
     */
    public Timestamp getTimeSubmitted() {
        return timeSubmitted;
    }

    /**
     * @param timeSubmitted the timeSubmitted to set
     */
    public void setTimeSubmitted(Timestamp timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
    }

    /**
     * @return the lastUpdated
     */
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return the timeAccepted
     */
    public Timestamp getTimeAccepted() {
        return timeAccepted;
    }

    /**
     * @param timeAccepted the timeAccepted to set
     */
    public void setTimeAccepted(Timestamp timeAccepted) {
        this.timeAccepted = timeAccepted;
    }
     /**
     *
     */
    public NoteLogic(){
        ParticipantLogic pl = new ParticipantLogic();
        
    }   

    /**
     * Adds a note to the database that has been submitted by a note writer
     * @param text main text from a note
     * @param comment comment added by note writer
     * @param participantID ID of the participant who is the subject of the written note 
     * @param activeUser current user logged into the program
     * @param noteState state added to note once submitted. 1 means the note has been submitted and is ready for review. 0 means the note is a draft
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void submit(Object text, Object comment, Object participantID, String activeUser, int noteState) throws SQLException, ClassNotFoundException{
        String placeholder = (String) participantID;
        int id = Integer.parseInt(placeholder.split(":")[0]);
        UserLogic ul = new UserLogic();
        UserDBConnect db = new UserDBConnect();
        
        ul.setUsername(activeUser);
        NoteLogic note = new NoteLogic();
        NoteDBConnect nbc = new NoteDBConnect();
        note.setText((String) text);
        note.setComment((String) comment);
        note.setParticipantID(id);
        note.setUserID(db.userSelect(ul).getUserID()); //Calls the getUserID method on the User object returned by UserDBConnect's userSelect method
        note.setIsSubmitted(noteState);
        nbc.submitNote(note);
        
        setNoteID(nbc.getNoteID());
    }
    
    /**
     * Updates a row in the Note table of the database to indicate that the specified note has been reviewed and accepted.
     * @param selectedNote Selected Note from a JList in the GUI
     * @param content Content of the Note to be updated in the database
     * @param isAccepted a 1 or 0 value indicating if the note has been accepted. 0 means a rejected note. 1 means accepted
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void acceptNote(Object selectedNote, Object content, int isAccepted) throws ClassNotFoundException, SQLException{
        NoteLogic note = new NoteLogic();
        NoteDBConnect ndb = new NoteDBConnect();
        String noteContent = (String) content;
        String placeholder = (String) selectedNote;
        String trimmed = placeholder.trim().split("-")[0];
        int ID = Integer.parseInt(trimmed.trim());
        
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        Timestamp currentStamp = new Timestamp(currentDate.getTime());
        
        note.setTimeAccepted(currentStamp);
        note.setNoteID(ID);
        note.setText(noteContent);
        note.setIsAccepted(isAccepted);
        ndb.updateReview(note);
    }
    

    /**
     * retrieves list of notes that are pending review to be displayed in the GUI
     * @return List of NoteLogic objects
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<NoteLogic> populateNotesPendingReview() throws SQLException, ClassNotFoundException{
        NoteDBConnect ndb = new NoteDBConnect();
        List<NoteLogic> list = ndb.selectForReview();
        return list;
    }

    /**
     * retrieves a single note specified by the noteID parameter 
     * @param noteID Unique ID for the desired note from the database
     * @return returns NoteLogic object
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static NoteLogic select(int noteID) throws ClassNotFoundException, SQLException{
        NoteLogic note = new NoteLogic();
        NoteDBConnect ndb = new NoteDBConnect();
        note.setNoteID(noteID);
        return ndb.selectNote(note);
    }

    /**
     * @return the isAccepted
     */
    public int getIsAccepted() {
        return isAccepted;
    }

    /**
     * @param isAccepted the isAccepted to set
     */
    public void setIsAccepted(int isAccepted) {
        this.isAccepted = isAccepted;
    }
    
}
