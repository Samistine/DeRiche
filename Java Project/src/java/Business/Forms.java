/**
 * ****************************************************************************************
 * @author Trevor Bal
 * @author yasmina Rabhi added documentation.
 * @since3-6-17
 * @description Forms business object for DeRichie web app.
 * @version 1.0
 * ***************************************************************************************
 */
package Business;

//import dataAccess.DBConnection;
import dataAccess.DB;
import dataAccess.DBConnection;
import dataAccess.Access;
import dataAccess.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpSession;

/**
 * ****************************************************************************************
 *
 * @description Forms main class for DeRichie web application.
 * ***************************************************************************************
 */
public class Forms extends Access {

    //form ID
    private String formID;
    //user ID
    private String userID;

    // patient ID
    private String patientID;

    // form type
    private String formType;
    //form info
    private String formInfo;
    //date
    private String date;
    //revision
    private String revision1;

    /**
     * @return the formID
     */
    public String getFormID() {
        return formID;
    }

    /**
     * @param formID the formID to set
     */
    public void setFormID(String formID) {
        this.formID = formID;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
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
     * @return the formType
     */
    public String getFormType() {
        return formType;
    }

    /**
     * @param formType the formType to set
     */
    public void setFormType(String formType) {
        this.formType = formType;
    }

    /**
     * @return the formInfo
     */
    public String getFormInfo() {
        return formInfo;
    }

    /**
     * @param formInfo the formInfo to set
     */
    public void setFormInfo(String formInfo) {
        this.formInfo = formInfo;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the revision1
     */
    public String getRevision1() {
        return revision1;
    }

    /**
     * @param revision1 the revision1 to set
     */
    public void setRevision1(String revision1) {
        this.revision1 = revision1;
    }

    /**
     * ****************************************************************************************
     *
     * @description Forms display for testing.
     * ***************************************************************************************
     */
    public void display() {
        System.out.println("Begin Forms.java display ");
        System.out.println("Form Id: " + getFormID());
        System.out.println("User ID:" + getUserID());
        System.out.println("Patient ID: " + getPatientID());
        System.out.println("Form Type: " + getFormType());
        System.out.println("Form Info: " + getFormInfo());
        System.out.println("Date: " + getDate());
        System.out.println("Revision: " + getRevision1());
        System.out.println("End Forms.java display ");
    }//end display

    /**
     * ****************************************************************************************
     * SelectDB() Method
     *
     * @param fid form ID
     *
     ****************************************************************************************
     */
    public void selectDB(String fid) throws SQLException {
        formID = fid;

        String sql = "Select * from forms where FormID = ?";

        try (
                Connection con1 = DB.dbc();
                PreparedStatement ps = con1.prepareStatement(sql)) {

            ps.setString(1, getFormID());
            //execute sql statement
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                setUserID(rs.getString(2));
                setPatientID(rs.getString(3));
                setFormType(rs.getString(4));
                setFormInfo(rs.getString(5));
                setDate(rs.getString(6));
                setRevision1(rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }// end public void selectDB

    /**
     * *****************************************************************************
     * @description Insert method will put forms info into the DeRichie web app.
     * @description Inserts into the forms table.
     * @param uid user ID
     * @param pid patient ID
     * @param ft form type
     * @param fi form info
     * @param dt date
     * @param r1 revision
     *
     ******************************************************************************
     */
    public void insertDB(String uid, String pid, String ft, String fi, String dt, String r1) {
        userID = uid;
        patientID = pid;
        formType = ft;
        formInfo = fi;
        date = dt;
        revision1 = r1;

        String sql = "insert into forms"
                + "(userID, patientID, formType, formInfo, date, revision1) values"
                + "(?,?,?,?,?,?)";

        try (
                Connection conn = DB.dbc();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            //File file = new File(fi);
            //FileInputStream input = new FileInputStream(fi);
            ps.setString(1, getUserID());
            ps.setString(2, getPatientID());
            ps.setString(3, getFormType());
            ps.setString(4, formInfo);
            ps.setString(5, getDate());
            ps.setString(6, getRevision1());

            int n = ps.executeUpdate();

            if (n == 1) {
                System.out.println("Insert Forms.java Successful!!!");
            } else {
                System.out.println("Insert Forms.java Failed!!!");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }// end public void insertDB

    /**
     * ****************************************************************************************
     ** updateDBAdmin() Method
     *
     * @description This will allow the admin to update
     * @description the forms table in the in the DeRichie DB.
     * @deprecated
     * ***************************************************************************************
     */
    public void updateDBAdmin() throws SQLException {
        Connection con1 = null;
        PreparedStatement ps = null;

        String sql = "update forms set userID =?, patientID =?, formType = ?, formInfo = ?, date = ?, revision1 = ? Where FormID = ?";

        try {
            con1 = DB.dbc();
            ps = con1.prepareStatement(sql);

            ps.setString(1, getUserID());
            ps.setString(2, getPatientID());
            ps.setString(3, getFormType());
            ps.setString(4, getFormInfo());
            ps.setString(5, getDate());
            ps.setString(6, getRevision1());
            //System.out.println(sql);

            int n = ps.executeUpdate();

            if (n == 1) {
                System.out.println("Admin Update Successful!!!");
            } else {
                System.out.println("Admin Update Failed!!!");

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
     * deleteDB() Method
     *
     * @description This deleteDB method will go in to the forms table in the
     * Deriche DB.
     * @deprecated
     * ***************************************************************************************
     */
    public void deleteDB() throws SQLException {

        PreparedStatement ps = null;
        Connection con1 = null;

        String sql = "delete from forms where FormID = ?";

        try {
            con1 = DB.dbc();
            ps = con1.prepareStatement(sql);

            ps.setString(1, getFormID());

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
        String sql = DBConnection.generateSQL(new Forms(), 6, DBConnection.SELECT);
        sql = sql.replaceFirst("=", "<>");
        System.out.println("sql= " + sql);
        /*
     GregorianCalendar cal = new GregorianCalendar();
        int tempdate;
        
        tempdate = cal.get(Calendar.MONTH);
        tempdate +=1;
        
        
      
    Forms form = new Forms();
        //DBConnection.insert(form, new Object[] {"FID000000014", "1", "1", "BODY_CHECK", "test", cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DATE), "1",}, true);
        String date = cal.get(Calendar.YEAR) + "-" + tempdate + "-" + cal.get(Calendar.DATE);

        FormInfo info = new FormInfo(FormInfo.BODY_CHECK);
        
        info.setValues(new String[] {"canvasData", "sigData", "Pizza", "Hambo", "Fall", "Kitchen", "9:00am", "They Slipped", "N/A", "N/A"});
        
        System.out.println(info.encode());
        DBConnection.select(form, 123, 0, false, true);
       
        try
        {
            DBConnection.insert(form, new Object[] {"270","1", "3", "BODY_CHECK", info.encode(), date, "3"});
        //form.insertDB("1", "3", "BODY_CHECK", info.encode(), date, "3");
        form.display();
        //form.selectDB("123");
        form.display();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        System.out.println(FormInfo.decode(form.getFormInfo()));
         */
 /*
    User form = new User();
 Object[] array = DBConnection.select(form, "77", 1, true);
 
 for(int x=0; x < array.length; x++)
 {
     System.out.println(array[x]);
 }
 
         */
 /*
    Forms form = new Forms();
        DBConnection.insert(form, new Object[] {"116", 2, 1, "BODY_CHECK","filetest", 2017+ "-" +04 + "-" + 10, "1"},false);
         */
 /*
//Testing selectDB(String un)
     try
    {
        Forms f = new Forms();
        f.selectDB("6");
        f.display();
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
        Forms f = new Forms();
        f.insertDB("test uID", "test pID", "test type", "test info", "test date", "test revision");
        f.display();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
    
    /*
    //Testing updateDBAdmin
    try
    {
        Forms f = new Forms();
        f.selectDB("43");
        f.setRevision1("0");
        f.updateDBAdmin();
        f.display();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    } 
         */

        //Testing deleteDB
        /*
    try
    {
        Forms f = new Forms();
        f.selectDB("43");
        f.display();
        f.deleteDB();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
         */
    }//end main method 

}// end public class Forms
