/*
 * CIST2931-Advanced Systems Project
 * Started: Spring 2016
 * Team 1 AKA Code Monkeys:
 *      Team Lead AKA who to blame for things: Triston Gregoire
 *      Team: Bonnie Falk, Bunmi Bamiro, Kristen Dawes, Natacha Mosala, Taylor Smith
*/
package Data_Access;
import Business.NoteLogic;
import Business.ParticipantLogic;
import Business.UserLogic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 *
 * @author Triston_Gregoire
 */
public class NoteDBConnect implements Data {

    int noteID;
    Timestamp time_Submitted;
    
    /**
     * Inserts a note to the database Note table
     * @param object NoteLogic object populated with information to be inserted into the database. Object passed to this method should have the isSubmitted variable set to 1 to indicate a submission
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public void submitNote(NoteLogic object) throws SQLException, ClassNotFoundException{
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        Timestamp currentStamp = new Timestamp(currentDate.getTime()); 
        String sql = "insert into Note (Text, Time_Submitted, Comment, ParticipantID, UserID, Submitted) values (?,?,?,?,?,?)";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(databaseTime, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setString(1, object.getText());
            prepared.setTimestamp(2, currentStamp, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone))));
            prepared.setString(3, object.getComment());
            prepared.setInt(4, object.getParticipantID());
            prepared.setInt(5, object.getUserID());
            prepared.setInt(6, object.getIsSubmitted());
            prepared.execute();
            
            //Code from Steven for new note button
            time_Submitted = currentStamp;
            System.out.println(time_Submitted);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String string = dateFormat.format(new Date());
            System.out.println(string);
            pullNoteID(string);
        }
    }
    
    
    //NOT FINISHED OR USED BUT WILL EVENTUALLY BE NEEDED
//    /**
//     * 
//     * @param object
//     * @throws SQLException
//     * @throws ClassNotFoundException
//     */
//    public void updateNote(NoteLogic object) throws SQLException, ClassNotFoundException{
//        String sql = "update note set text = ?, Last_Updated = ?, State = ? where NoteID = ?";
//        Class.forName(driver);
//        try (Connection connect = DriverManager.getConnection(databaseTime, databaseUser, databasePass)) {
//            PreparedStatement prepared = connect.prepareStatement(sql);
//            
//        }
//    }

    /**
     * Updates information in the row that corresponds to the NoteID contained in the NoteLogic parameter
     * @param object NoteLogic object populated with information to be inserted into the database. 
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateReview(NoteLogic object) throws ClassNotFoundException, SQLException{
        String sql = "update note set text = ?, NoteReviewer_Accepted = ?, Time_Accepted = ? where NoteID = ?";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(databaseTime, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setString(1, object.getText());
            prepared.setInt(2, object.getIsAccepted());
            prepared.setTimestamp(3, object.getTimeAccepted(), Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone))));
            prepared.setInt(4, object.getNoteID());
            prepared.executeUpdate();
        }
    }
    
    /**
     * Retrieves note from the Note table that corresponds to the NoteID in the NoteLogic object passed as the parameter 
     * @param note 
     * @return NoteLogic object 
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public NoteLogic selectNote(NoteLogic note) throws ClassNotFoundException, SQLException{
        String sql = "Select * from Note where NoteID = ?";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(databaseTime, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setInt(1, note.getNoteID());
            ResultSet result = prepared.executeQuery();
            //Skip Note ID column
            while(result.next()){
                note.setText(result.getString(2));
                note.setTimeStarted(result.getTimestamp(3, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                note.setLastUpdated(result.getTimestamp(4, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                note.setTimeSubmitted(result.getTimestamp(5, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                note.setTimeAccepted(result.getTimestamp(6, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                note.setComment(result.getString(7));
                note.setIsSubmitted(result.getInt(8));
                note.setTimeReviewerAccepted(result.getInt(9));
                note.setNoteReviewerAccepted(result.getInt(10));
                note.setParticipantID(result.getInt(11));
                note.setUserID(result.getInt(12));
            }
        }
        return note;
    }
    
    /**
     * retrieves notes from the database by the participant ID passed to the method
     * @param partID participant ID
     * @return List of NoteLogic objects 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<NoteLogic> selectByParticipant(int partID) throws SQLException, ClassNotFoundException{
        String sql = "Select * from Note where ParticipantID = ?";
        List<NoteLogic> list = new ArrayList();
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(databaseTime, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setInt(1, partID);
            ResultSet result = prepared.executeQuery();
            while(result.next()){
                NoteLogic newNote = new NoteLogic();
                newNote.setNoteID(result.getInt(1));
                newNote.setText(result.getString(2));
                newNote.setTimeStarted(result.getTimestamp(3, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                newNote.setLastUpdated(result.getTimestamp(4, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                newNote.setTimeSubmitted(result.getTimestamp(5, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                newNote.setTimeAccepted(result.getTimestamp(6, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                newNote.setComment(result.getString(7));
                newNote.setIsSubmitted(result.getInt(8));
                newNote.setTimeReviewerAccepted(result.getInt(9));
                newNote.setNoteReviewerAccepted(result.getInt(10));
                newNote.setParticipantID(result.getInt(11));
                newNote.setUserID(result.getInt(12));
                list.add(newNote);
            }
        }
        return list;
    }
    
    /**
     * Selects notes by the User ID passed to the method returns notes in descending order based by the time submitted
     * @param userID 
     * @return List of NoteLoigc objects retrieved from the database. 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<NoteLogic> selectByUser(int userID) throws SQLException, ClassNotFoundException{
        String sql = "Select * from Note where UserID = ? order by Time_Submitted desc";
        List<NoteLogic> list = new ArrayList();
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(databaseTime, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setInt(1, userID);
            ResultSet result = prepared.executeQuery();
            while(result.next()){
                NoteLogic newNote = new NoteLogic();
                newNote.setNoteID(result.getInt(1));
                newNote.setText(result.getString(2));
                newNote.setTimeStarted(result.getTimestamp(3, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                newNote.setLastUpdated(result.getTimestamp(4, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                newNote.setTimeSubmitted(result.getTimestamp(5, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                newNote.setTimeAccepted(result.getTimestamp(6, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                newNote.setComment(result.getString(7));
                newNote.setIsSubmitted(result.getInt(8));
                newNote.setTimeReviewerAccepted(result.getInt(9));
                newNote.setNoteReviewerAccepted(result.getInt(10));
                newNote.setParticipantID(result.getInt(11));
                newNote.setUserID(result.getInt(12));
                list.add(newNote);
            }
        }
        return list;
    }
    
//    public List<NoteLogic> selectByState(int status) throws SQLException, ClassNotFoundException{
//        String sql = "Select * from Note where State = ?";
//        List<NoteLogic> list = new ArrayList();
//        Class.forName(driver);
//        try (Connection connect = DriverManager.getConnection(databaseTime)) {
//            PreparedStatement prepared = connect.prepareStatement(sql);
//            prepared.setInt(1, status);
//            ResultSet result = prepared.executeQuery();
//            while(result.next()){
//                NoteLogic newNote = new NoteLogic();
//                newNote.setNoteID(result.getInt(1));
//                newNote.setText(result.getString(2));
//                newNote.setTimeStarted(result.getTimestamp(3, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
//                newNote.setLastUpdated(result.getTimestamp(4, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
//                newNote.setTimeSubmitted(result.getTimestamp(5, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
//                newNote.setTimeAccepted(result.getTimestamp(6, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
//                newNote.setComment(result.getString(7));
//                newNote.setIsSubmitted(result.getInt(8));
//                newNote.setTimeReviewerAccepted(result.getInt(9));
//                newNote.setNoteReviewerAccepted(result.getInt(10));
//                newNote.setParticipantID(result.getInt(11));
//                newNote.setUserID(result.getInt(12));
//                list.add(newNote);
//            }
//        }
//        return list;
//    }
    
    /**
     * Selects all notes that have been submitted but have yet to be reviewed for acceptance
     * 
     * @return list of Notes to be accepted
     * @throws SQLException
     * @throws ClassNotFoundException
     */
        
    public List<NoteLogic> selectForReview() throws SQLException, ClassNotFoundException{
        String sql = "Select * from Note where Submitted = 1 AND NoteReviewer_Accepted is null";
        List<NoteLogic> list = new ArrayList();
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(databaseTime, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            ResultSet result = prepared.executeQuery();
            while(result.next()){
                NoteLogic note = new NoteLogic();
                UserLogic user = new UserLogic();
                ParticipantLogic participant = new ParticipantLogic();
                note.setNoteID(result.getInt(1));
                note.setText(result.getString(2));
                note.setTimeStarted(result.getTimestamp(3, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                note.setLastUpdated(result.getTimestamp(4, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                note.setTimeSubmitted(result.getTimestamp(5, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                note.setTimeAccepted(result.getTimestamp(6, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));            
                note.setIsSubmitted(result.getInt(8));
                note.setTimeReviewerAccepted(result.getInt(9));
                note.setNoteReviewerAccepted(result.getInt(10));
                note.setParticipantID(result.getInt(11));
                note.setUserID(result.getInt(12));
                note.setPl(participant.select(result.getInt(11)));
                note.setUl(user.select(result.getInt(12)));
                list.add(note);
            }
        }
        return list;
    }
    
    public static List<NoteLogic> getSavedDraft(String username) throws SQLException, ClassNotFoundException{
        UserLogic ul = new UserLogic();
        int ident = ul.select(username).getUserID();
        String sql = "Select * from Note where Submitted = 0 AND UserID = ?";
        List<NoteLogic> list = new ArrayList();
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(databaseTime, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setInt(1, ident);
            ResultSet result = prepared.executeQuery();
            while(result.next()){
                NoteLogic note = new NoteLogic();
                UserLogic user = new UserLogic();
                ParticipantLogic participant = new ParticipantLogic();
                note.setNoteID(result.getInt(1));
                note.setText(result.getString(2));
                note.setTimeStarted(result.getTimestamp(3, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                note.setLastUpdated(result.getTimestamp(4, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                note.setTimeSubmitted(result.getTimestamp(5, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));
                note.setTimeAccepted(result.getTimestamp(6, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(zone)))));            
                note.setIsSubmitted(result.getInt(8));
                note.setTimeReviewerAccepted(result.getInt(9));
                note.setNoteReviewerAccepted(result.getInt(10));
                note.setParticipantID(result.getInt(11));
                note.setUserID(result.getInt(12));
                note.setPl(participant.select(result.getInt(11)));
                note.setUl(user.select(result.getInt(12)));
                list.add(note);
            }
            System.out.println("Note \"opened\", apparently");
        }
        return list;
    }
    
    /**
     * Deletes Note from Note table in Database that corresponds with the accepted parameter
     * 
     * @param id
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void deleteNote(int id) throws ClassNotFoundException, SQLException{
        String sql = "delete from Note where NoteID = ?";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(databaseTime, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setInt(1, id);
            prepared.execute();
        }
    }
    public static void deleteDraft(int id) throws SQLException, ClassNotFoundException{
        String sql = "Delete from Note where NoteID = ? AND Submitted = 0";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(databaseTime, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setInt(1, id);
            prepared.execute();
        }
    }
    
    public void upDate(int noteId, String note) throws ClassNotFoundException, SQLException {
        System.out.println(noteId);
        System.out.println(note);
        System.out.println("note not saved yet");
        String sql = ("UPDATE Note Set text = '" + note + "' WHERE NoteID = "+ noteId);
        Class.forName(driver);
        try(Connection connect = DriverManager.getConnection(databaseTime,databaseUser,databasePass)){
            Statement statement = connect.createStatement();
            statement.executeUpdate(sql);
            
        }
    }
    
    
    /**
     * Overwrites when a save note is submitted
     * @param noteID
     * @param comment
     * @param i
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void submitUpDate(int noteID, String comment, int i) throws ClassNotFoundException, SQLException {
        System.out.println("you made it to NoteDBConnect");
        System.out.println(noteID);
        System.out.println(comment);
        System.out.println(i);
        
        String sql = ("UPDATE Note set Comment = '" + comment + "', Submitted = " + i + " where NoteID = "+ noteID);
        
        Class.forName(driver);
        try(Connection connect = DriverManager.getConnection(databaseTime,databaseUser,databasePass)){
            Statement statement = connect.createStatement();
            statement.executeUpdate(sql);
            
        }
        
    }
    
    /**
     * Pulls the noteID from the DB
     * Known error depending on the PC if failure to pull data use time_Started 
     * instead of time_Submitted.
     * @param time
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void pullNoteID(String time) throws ClassNotFoundException,SQLException{
        System.out.println("NoteDB get NoteID method");
        System.out.println(time);
        
        String sql = ("Select NoteID from Note where time_Submitted = '" + time + "'");
        
        Class.forName(driver);
        try(Connection connect = DriverManager.getConnection(databaseTime,databaseUser,databasePass)){
            Statement statement = connect.createStatement();
            ResultSet result;
            result = statement.executeQuery(sql);
            while(result.next()){
                setNoteID(result.getInt("NoteID"));
            }            
        }
    }
    /**
     * Sets noteID from DB
     * @param note 
     */
    public void setNoteID(int note){
        System.out.println(">"+note+"<");
        this.noteID = note;
    }
    /**
     * Returns noteID from DB
     * @return 
     */
    public int getNoteID(){
        return noteID;
    }
}
