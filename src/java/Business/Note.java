/******************************************************************************************
* @author Carl Moon II.
*@author Yasmina Rabhi added documentation
*@since 3-3-17.
*@description Note business object for DeRichie web app.
* @version 1.0
*****************************************************************************************/
package Business;

import dataAccess.db;
import dataAccess.Access;
import dataAccess.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/******************************************************************************************
* 
* @description Note main class for DeRichie web application.
*****************************************************************************************/
public class Note extends Access {
    
    private String noteID;
    
    private String notes;
    
    private String Time_Started;
    
    private String Last_Updated;
    
    private String Time_Submitted;
    
    private String Time_Accepted;
    
    private String Comment;
    
    private String Submitted;
    
    private String TimeReviewer_Accepted;
    
    private String NoteReviewer_Accepted;
    
    private String patientID;
    
    private String UserID;

    /**
     * @return the noteID
     */
    public String getNoteID() {
        return noteID;
    }

    /**
     * @param noteID the noteID to set
     */
    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the Time_Started
     */
    public String getTime_Started() {
        return Time_Started;
    }

    /**
     * @param Time_Started the Time_Started to set
     */
    public void setTime_Started(String Time_Started) {
        this.Time_Started = Time_Started;
    }

    /**
     * @return the Last_Updated
     */
    public String getLast_Updated() {
        return Last_Updated;
    }

    /**
     * @param Last_Updated the Last_Updated to set
     */
    public void setLast_Updated(String Last_Updated) {
        this.Last_Updated = Last_Updated;
    }

    /**
     * @return the Time_Submitted
     */
    public String getTime_Submitted() {
        return Time_Submitted;
    }

    /**
     * @param Time_Submitted the Time_Submitted to set
     */
    public void setTime_Submitted(String Time_Submitted) {
        this.Time_Submitted = Time_Submitted;
    }

    /**
     * @return the Time_Accepted
     */
    public String getTime_Accepted() {
        return Time_Accepted;
    }

    /**
     * @param Time_Accepted the Time_Accepted to set
     */
    public void setTime_Accepted(String Time_Accepted) {
        this.Time_Accepted = Time_Accepted;
    }

    /**
     * @return the Comment
     */
    public String getComment() {
        return Comment;
    }

    /**
     * @param Comment the Comment to set
     */
    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    /**
     * @return the Submitted
     */
    public String getSubmitted() {
        return Submitted;
    }

    /**
     * @param Submitted the Submitted to set
     */
    public void setSubmitted(String Submitted) {
        this.Submitted = Submitted;
    }

    /**
     * @return the TimeReviewer_Accepted
     */
    public String getTimeReviewer_Accepted() {
        return TimeReviewer_Accepted;
    }

    /**
     * @param TimeReviewer_Accepted the TimeReviewer_Accepted to set
     */
    public void setTimeReviewer_Accepted(String TimeReviewer_Accepted) {
        this.TimeReviewer_Accepted = TimeReviewer_Accepted;
    }

    /**
     * @return the NoteReviewer_Accepted
     */
    public String getNoteReviewer_Accepted() {
        return NoteReviewer_Accepted;
    }

    /**
     * @param NoteReviewer_Accepted the NoteReviewer_Accepted to set
     */
    public void setNoteReviewer_Accepted(String NoteReviewer_Accepted) {
        this.NoteReviewer_Accepted = NoteReviewer_Accepted;
    }

    /**
     * @return the patientID
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * @param patientID the patientID to set
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    /**
     * @return the UserID
     */
    public String getUserID() {
        return UserID;
    }

    /**
     * @param UserID the UserID to set
     */
    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
    
/******************************************************************************************
*   
* @ description User display for testing.
 *****************************************************************************************/
    public void display()
    {
        System.out.println("Begin Note.java display ");
        System.out.println("Note Id: "+getNoteID());
        System.out.println("Notes: "+getNotes());
        System.out.println("Time Started: "+getTime_Started());
        System.out.println("Last Updated: "+getLast_Updated());
        System.out.println("Time Submitted: "+getTime_Submitted());
        System.out.println("Time Accepted: "+getTime_Accepted());
        System.out.println("Comment: "+getComment());
        System.out.println("Submitted: "+getSubmitted());
        System.out.println("Time Reviewer Accepted: "+getTimeReviewer_Accepted());
        System.out.println("Note Reviewer Accepted: "+getNoteReviewer_Accepted());
        System.out.println("Patient ID: "+getPatientID());
        System.out.println("User ID: "+getUserID());
        System.out.println("End Note.java display ");
    }//end display
/******************************************************************************************
* SelectDB() Method
* @description  This selectDB method will go in to the Appointments table in the Dentist DB.
* @param nid note ID
* @deprecated
*****************************************************************************************/
public void selectDB(String nid) throws SQLException
{
    noteID = nid;
 
    
    Connection con1 = null;
    PreparedStatement ps = null;
    
    String sql = "Select * from note where NoteID = ?";
    
        try
        {
            con1 = db.dbc();
            ps = con1.prepareStatement(sql);
            ps.setString(1, getNoteID());
            //execute sql statement
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            { 
                //setUserId(rs.getString(1));
                setNotes(rs.getString(2));
                setTime_Started(rs.getString(3));
                setLast_Updated(rs.getString(4));
                setTime_Submitted(rs.getString(5));
                setTime_Accepted(rs.getString(6));
                setComment(rs.getString(7));
                setSubmitted(rs.getString(8));
                setTimeReviewer_Accepted(rs.getString(9));
                setNoteReviewer_Accepted(rs.getString(10));
                setPatientID(rs.getString(11));
                setUserID(rs.getString(12));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
            
        finally
        {
            if (ps != null)
            {
                ps.close();
            }
            
            if(con1 != null)
            {
            con1.close();
            }
        }
           
}// end public void selectDB
/*******************************************************************************
*@description Insert method will put user info into the DeRichie web app.
*@description Inserts into the note table.
*@param n1 notes
*@param ts time started
*@param lu last update
*@param tus time submitted
*@param ta time accepted
*@param c Comments
*@param s submitted
*@param tra time reviewer accepted
*@param nra note reviewer accepted
*@param  pid patient ID 
*@param uid User ID
*@deprecated
*******************************************************************************/

public void insertDB(String n1, String ts, String lu, String tus, String ta, String c, String s, String tra, String nra, String pid, String uid) throws SQLException
{
     
   notes = n1;
   Time_Started = ts;
   Last_Updated = lu;
   Time_Submitted = tus;
   Time_Accepted = ta;
   Comment = c;
   Submitted = s;
   TimeReviewer_Accepted = tra;     
   NoteReviewer_Accepted = nra;
   patientID =pid;
   UserID = uid;
           
    Connection con1 = null;
    PreparedStatement ps = null;
    
    String sql = "insert into note"
                                      +"(notes, Time_Started, Last_Updated, Time_Submitted, Time_Accepted, Comment, Submitted, TimeReviewer_Accepted, NoteReviewer_Accepted, patientID, UserID) values"
            
                                      +"(?,?,?,?,?,?,?,?,?,?,?)";
        
    try
    {
        con1 = db.dbc();
        ps = con1.prepareStatement(sql);
       
        ps.setString(1, getNotes());
        ps.setString(2, getTime_Started());
        ps.setString(3, getLast_Updated());
        ps.setString(4, getTime_Submitted());
        ps.setString(5, getTime_Accepted());
        ps.setString(6, getComment());
        ps.setString(7, getSubmitted());
        ps.setString(8, getTimeReviewer_Accepted());
        ps.setString(9, getNoteReviewer_Accepted());
        ps.setString(10, getPatientID());
        ps.setString(11, getUserID());
        //ps.setString(11, getUserID());
        
        
        
         int n = ps.executeUpdate();
          
        if(n==1)
        { 
            System.out.println("Insert User.java Successful!!!");
        }
        else
        {
            System.out.println("Insert User.java Failed!!!");
           
        }
    }
    catch(SQLException e)
    {
        e.printStackTrace();
    }
    finally
    {
        if(ps != null)
        {
            ps.close();
        }
        
        if(con1 != null)
        {
            con1.close();
        }
    }
}// end public void insertDB
/******************************************************************************************
** updateDBAdmin() Method
* @description This will allow the admin to update
* @description the note table in the in the DeRichie DB.
* @deprecated
*****************************************************************************************/
public void updateDBAdmin() throws SQLException
{
    Connection con1 = null;
    PreparedStatement ps = null;
    
    String sql = "update note set notes =?, Time_Started = ?, Last_Updated = ?, Time_Submitted = ?, Time_Accepted = ?, Comment = ?, Submitted = ?, TimeReviewer_Accepted = ?, NoteReviewer_Accepted = ?, patientID = ?, UserID = ? Where NoteID = ?";
    
    try
    {
        con1 = db.dbc();
        ps = con1.prepareStatement(sql);
        
        
        ps.setString(1, getNotes());
        ps.setString(2, getTime_Started());
        ps.setString(3, getLast_Updated());
        ps.setString(4, getTime_Submitted());
        ps.setString(5, getTime_Accepted());
        ps.setString(6, getComment());
        ps.setString(7, getSubmitted());
        ps.setString(8, getTimeReviewer_Accepted());
        ps.setString(9, getNoteReviewer_Accepted());
        ps.setString(10, getPatientID());
        ps.setString(11, getUserID());
        ps.setString(12, getNoteID());
        
        //System.out.println(sql);
          
          
          
        int n = ps.executeUpdate();
          
        if(n==1)
        { 
            System.out.println("Admin Update Successful!!!");
        }
        else
        {
            System.out.println("Admin Update Failed!!!");
           
        }
    }
    catch(SQLException e)
    {
        e.printStackTrace();
    }
    finally
    {
        if(ps != null)
        {
            ps.close();
        }
        
        if(con1 != null)
        {
            con1.close();
        }
    }
}//end updateDBAdmin
/******************************************************************************************
* @description  deleteDB() Method
* @description This deleteDB method will go in to the  table in the Dentist DB.
* @deprecated
*****************************************************************************************/
public void deleteDB() throws SQLException
{
      
    PreparedStatement ps = null;
    Connection con1 = null;
    
    
 
    String sql = "delete from note where NoteID = ?";
    
    
        try
        {
          con1 = db.dbc();
          ps = con1.prepareStatement(sql);
          
          ps.setString(1, getNoteID());

          
          System.out.println(sql);
          
          
          
           int n = ps.executeUpdate();
          
           if(n==1)
           { 
               System.out.println("Delete Successful!!!");
           }
           else
           {
               System.out.println("Delete Failed!!!");
           
           }
                  
        }//end try
        catch(SQLException e)
        {
           e.printStackTrace();
        }
        finally
        {
            try
            {    
                if(ps != null)
                {
                    ps.close();
                }
                if(con1 != null)
                {
                    con1.close();
                }
            }
            catch(SQLException sqle)
            {
                System.out.println(sqle);
            }
        }//end finally
   }//end deleteDB
public static void main(String[] args)
{
 /*    
//Testing selectDB(String un)
     try
    {
        Note n = new Note();
        n.selectDB("6");
        n.display();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
     */
    /*
     //Testing insertDB
    try
    {
        Note n = new Note();
        n.insertDB("this is a test", "2017-03-03 20:35:36", "2017-03-03 20:35:36", "2017-03-03 20:35:36", "2017-03-03 20:35:36", "comment test","1", "2017-04-03 7:35:36","1","3","73");
        n.display();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
    */
    /*
    //Testing updateDBAdmin
    try
    {
        Note n = new Note();
        n.selectDB("43");
        n.setSubmitted("0");
        n.updateDBAdmin();
        n.display();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    } 
    */
    
    //Testing deleteDB
    try
    {
        Note n = new Note();
        n.selectDB("43");
        n.display();
        n.deleteDB();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
    
}//end main method 
    
}// end public class Note