/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import dataAccess.DB;
import dataAccess.Access;
import dataAccess.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carl
 */
public class Goal extends Access {

    private String goalID;
    private String description;
    private String objective;
    private String guidanceNote;
    private String frequency;
    private String isWeekly;
    private String patientID;

    /**
     * @return the goalID
     */
    public String getGoalID() {
        return goalID;
    }

    /**
     * @param goalID the goalID to set
     */
    public void setGoalID(String goalID) {
        this.goalID = goalID;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param desc the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the objective
     */
    public String getObjective() {
        return objective;
    }

    /**
     * @param objec the objective to set
     */
    public void setObjective(String objective) {
        this.objective = objective;
    }

    /**
     * @return the guidanceNote
     */
    public String getGuidanceNote() {
        return guidanceNote;
    }

    /**
     * @param note the note to set
     */
    public void setGuidanceNote(String guidanceNote) {
        this.guidanceNote = guidanceNote;
    }

    /**
     * @return the frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * @param freq the frequency to set
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     * @return the isWeekly
     */
    public String getIsWeekly() {
        return isWeekly;
    }

    /**
     * @param isWeekly the isWeekly to set
     */
    public void setIsWeekly(String isWeekly) {
        this.isWeekly = isWeekly;
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
     * ****************************************************************************************
     *
     * User display for testing.
 ****************************************************************************************
     */
    public void display() {
        System.out.println("Begin Goal.java display ");
        System.out.println("Goal Id: " + getGoalID());
        System.out.println("Description: " + getDescription());
        System.out.println("Objective: " + getObjective());
        System.out.println("Note: " + getGuidanceNote());
        System.out.println("Frequency: " + getFrequency());
        System.out.println("IsWeekly: " + getIsWeekly());
        System.out.println("PatientID: " + getPatientID());
        System.out.println("End Goal.java display ");
    }//end display

    /**
     * ****************************************************************************************
     * SelectDB() Method This selectDB method will go in to the Appointments
     * table in the Dentist DB.
     *
     * @deprecated
****************************************************************************************
     */
    public void selectDB(String gid) throws SQLException {
        goalID = gid;

        Connection con1 = null;
        PreparedStatement ps = null;

        String sql = "Select * from goal where GoalID = ?";

        try {
            con1 = DB.dbc();
            ps = con1.prepareStatement(sql);
            ps.setString(1, getGoalID());
            //execute sql statement
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                setDescription(rs.getString(2));
                setObjective(rs.getString(3));
                setGuidanceNote(rs.getString(4));
                setFrequency(rs.getString(5));
                setIsWeekly(rs.getString(6));
                setPatientID(rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }

            if (con1 != null) {
                con1.close();
            }
        }

    }// end public void selectDB

    /**
     * *****************************************************************************
     * Insert method will put user info into the DeRichie web app. Inserts into
     * the note table.
     *
     * @deprecated
******************************************************************************
     */
    public void insertDB(String gid, String d, String ob, String nt, String fr, String wkly, String pid) throws SQLException {
        goalID = gid;
        description = d;
        objective = ob;
        guidanceNote = nt;
        frequency = fr;
        isWeekly = wkly;
        patientID = pid;

        Connection con1 = null;
        PreparedStatement ps = null;

        String sql = "insert into goal"
                + "(goalid, Description, objective, note, frequency, isweekly, patientID) values"
                + "(?,?,?,?,?,?,?)";

        try {
            con1 = DB.dbc();
            ps = con1.prepareStatement(sql);

            ps.setString(1, getGoalID());
            ps.setString(2, getDescription());
            ps.setString(3, getObjective());
            ps.setString(4, getGuidanceNote());
            ps.setString(5, getFrequency());
            ps.setString(6, getIsWeekly());
            ps.setString(7, getPatientID());

            int n = ps.executeUpdate();

            if (n == 1) {
                System.out.println("Insert Goal.java Successful!!!");
            } else {
                System.out.println("Insert Goal.java Failed!!!");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }

            if (con1 != null) {
                con1.close();
            }
        }
    }// end public void insertDB

    /**
     * ****************************************************************************************
     ** updateDBAdmin() Method This will allow the admin to update the note
     * table in the in the DeRichie DB.
     *
     * @deprecated
****************************************************************************************
     */
    public void updateDB() throws SQLException {
        Connection con1 = null;
        PreparedStatement ps = null;

        String sql = "update goal set description = ?, objective = ?, note = ?, frequency = ?, isweekly = ?, patientID = ? Where GoalID = ?";

        try {
            con1 = DB.dbc();
            ps = con1.prepareStatement(sql);

            ps.setString(1, getDescription());
            ps.setString(2, getObjective());
            ps.setString(3, getGuidanceNote());
            ps.setString(4, getFrequency());
            ps.setString(5, getIsWeekly());
            ps.setString(6, getPatientID());
            ps.setString(7, getGoalID());

            System.out.println(sql);

            int n = ps.executeUpdate();

            if (n == 1) {
                System.out.println("Goal Update Successful!!!");
            } else {
                System.out.println("Goal Update Failed!!!");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }

            if (con1 != null) {
                con1.close();
            }
        }
    }//end updateDBAdmin

    /**
     * ****************************************************************************************
     * deleteDB() Method This deleteDB method will go in to the table in the
     * Dentist DB.
     *
     * @deprecated
****************************************************************************************
     */
    public void deleteDB() throws SQLException {

        PreparedStatement ps = null;
        Connection con1 = null;

        String sql = "delete from goal where GoalID = ?";

        try {
            con1 = DB.dbc();
            ps = con1.prepareStatement(sql);

            ps.setString(1, getGoalID());

            System.out.println(sql);

            int n = ps.executeUpdate();

            if (n == 1) {
                System.out.println("Delete Successful!!!");
            } else {
                System.out.println("Delete Failed!!!");

            }

        }//end try//end try
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con1 != null) {
                    con1.close();
                }
            } catch (SQLException sqle) {
                System.out.println(sqle);
            }
        }//end finally
    }//end deleteDB

    public static void main(String[] args) {
        /* 
//Testing selectDB(String un)
     try
    {
        Goal g = new Goal();
        g.selectDB("4");
        g.display();
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
        Goal g = new Goal();
        g.insertDB("14","this is a test", "test this class", "if inserts it is correct", "2", "1", "73");
        g.display();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
         */
 /*
    //Testing updateDB
    try
    {
        Goal g = new Goal();
        g.selectDB("14");
        g.setNote("Test1");
        g.updateDB();
        g.display();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    } 
         */

        //Testing deleteDB
        try {
            Goal g = new Goal();
            g.selectDB("14");
            g.display();
            g.deleteDB();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }//end main method             
}//end public class Goal 
