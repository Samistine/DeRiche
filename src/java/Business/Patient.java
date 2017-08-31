/******************************************************************************************
* @author Trevor Bal & Carl Moon II
*@since3-1-17
*@author Yasmina Rabhi addeed documentation 3-20-2017
* @description patient business object for DeRichie web app.
* @version 1.1
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
* Patient main class for DeRichie web application.
*****************************************************************************************/
public class Patient extends Access {
 //patients identification number
    private String patientId;
    //patients first name
    private String firstName;
    //patients last name
    private String lastName;
    //patients insurance company
    private String insurance;
    //patients medicaid number
    private String medicaidNumber;
    
   /**
     * @return the patientId
     *@Author Trevor Bal & Carl Moon
     *@Since 3-1-17
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     * @ Author Trevor Bal & Carl Moon
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /** 
     *Description   
     * @return the firstName
     *@ Author Trevor Bal & Carl Moon
     *@Since 3-1-17
     */
    public String getFirstName() {
        return firstName;
    }

    /**
    *@Description  setFirstName sets the first name of the patient
     * @param firstName the firstName to set
     
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
    *@Description getLasteName return the name of the patient 
     * @return the lastName
     @ Author Trevor Bal & Carl Moon
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** 
     * @return the insurance
      *@ Author Trevor Bal & Carl Moon
      *@Since 3-1-17
     */
    public String getInsurance() {
        return insurance;
    }

    /**
     * @param insurance the insurance to set
      *@ Author Trevor Bal & Carl Moon
      *@Since 3-1-17
     */
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    /**
     *Description getMedicaidNumber returns the patient medicaidNumber
     * @return the medicaidNumber
      *@ Author Trevor Bal & Carl Moon
     *@Since 3-1-17
     */
    public String getMedicaidNumber() {
        return medicaidNumber;
    }

    /**
     * @param medicaidNumber the medicaidNumber to set
     */
    public void setMedicaidNumber(String medicaidNumber) {
        this.medicaidNumber = medicaidNumber;
    }

    
/******************************************************************************************
* 
* Patient display for testing.

*Description dispaly will dispaly all the returns from the previous get method : it will display Patient Id ,FirstName,LastName,Insurance,And Medicaid .
*@ return PatientId, FirstName,LastName,Insuarance Co,Medicaid Number 
 *@ Author Trevor Bal & Carl Moon
 *@Since 3-1-17

 *****************************************************************************************/
    public void display()
    {
        System.out.println("Begin patient.java display ");
        System.out.println("Patient Id: "+getPatientId());
        System.out.println("First Name: "+getFirstName());
        System.out.println("Last Name: "+getLastName());
        System.out.println("Insurance Co.: "+getInsurance());
        System.out.println("Medicaid Number: "+getMedicaidNumber());
        System.out.println("End patient.java display ");
    }//end display
    
/******************************************************************************************
* SelectDB() Method
* @Description This selectDB method select a patient by their patient ID number
* @param pID is Patient Id
* @Author Carl Moon - Changed the local var to not match the global var. Corrected int's to strings.
* @since 3-2-17
* @deprecated
*****************************************************************************************/
public void selectDB(String pId) throws SQLException
{
    patientId = pId;
 
    
    Connection con1 = null;
    PreparedStatement ps = null;
 //** sql will select from the patient database the specifice PatientID */
    
    String sql = "Select * from patient where PatientID = ?";
 
    
        try
        {
            con1 = db.dbc();
            ps = con1.prepareStatement(sql);
            ps.setString(1, getPatientId());
            //execute sql statement
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            { 
                //setUserId(rs.getString(1));
                setFirstName(rs.getString(2));
                setLastName(rs.getString(3));
                setInsurance(rs.getString(4));
                setMedicaidNumber(rs.getString(5));
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
*@Description  Insert method will put patient info into the DeRichie web application.
*@description Inserts into the Participant table.
*@description Changed the local var's so that they were not the same as the global
*@description var's. I also corrected the number of place holders to match the number of
*@description var's being inserted into the database. Corrected int's to strings.
*@Author Carl Moon. 
*@param pID Patient Id.
*@param fn First Name.
*@param ln Last Name. 
*@ param ins Insurance.
*@param mednum Medecaid Number.
*@Since3-2-17 .
*@deprecated
*******************************************************************************/

public void insertDB(String pId, String fn, String ln, String ins, String mednum) throws SQLException
{
   patientId = pId;
   firstName = fn;
   lastName = ln;
   insurance = ins;
   medicaidNumber = mednum;    

    Connection con1 = null;
    PreparedStatement ps = null;
    
    String sql = "insert into patient"
                                      +"(PatientID, Firstname, Lastname, Insurance, MedicaidNumber) values"
            
                                      +"(?,?,?,?,?)";
        
    try
    {
        con1 = db.dbc();
        ps = con1.prepareStatement(sql);
        
        ps.setString(1, getPatientId());
        ps.setString(2, getFirstName());
        ps.setString(3, getLastName());
        ps.setString(4, getInsurance());
        ps.setString(5, getMedicaidNumber());

        
        
         int n = ps.executeUpdate();
          
        if(n==1)
        { 
            System.out.println("Insert patient.java Successful!!!");
        }
        else
        {
            System.out.println("Insert patient.java Failed!!!");
           
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
**@description updateDBAdmin() Method
* @ descrioption This will allow the administrator to update, the user table in the in the DeRichie DB. Corrected  int's to strings.
* @ throws SQLException 
* @author Carl Moon
* @deprecated
* @ since 3-1-2017

*****************************************************************************************/
public void updateDBAdmin() throws SQLException
{
    Connection con1 = null;
    PreparedStatement ps = null;
    
    String sql = "update patient set Firstname = ?, Lastname = ?, Insurance = ?, MedicaidNumber = ? Where PatientID = ?";
    
    try
    {
        con1 = db.dbc();
        ps = con1.prepareStatement(sql);
        
        
        ps.setString(1, getFirstName());
        ps.setString(2, getLastName());
        ps.setString(3, getInsurance());
        ps.setString(4, getMedicaidNumber());
        ps.setString(5, getPatientId());
        
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
}//end updateDB
/******************************************************************************************
* @ describtion: updateDB() Method
* @ description : This updateDB method will allow a non admin
* @ description :user to update the user table with limited access in the in the DeRichie DB.
* @ description :Corrected int's to strings.
* @ since 3-1-2017
* @author Carl Moon 
* @deprecated
* @ catch SQLException e
*****************************************************************************************/
public void updateDB() throws SQLException
{
    Connection con1 = null;
    PreparedStatement ps = null;
    
    String sql = "update patient set Firstname = ?, Lastname = ? Where PatientID = ?";
    
    try
    {
        con1 = db.dbc();
        ps = con1.prepareStatement(sql);
        
        
        ps.setString(1, getFirstName());
        ps.setString(2, getLastName());
        ps.setString(3, getPatientId());
        
        
        System.out.println(sql);
          
          
          
        int n = ps.executeUpdate();
          
        if(n==1)
        { 
            System.out.println("Non - Admin Update Successful!!!");
        }
        else
        {
            System.out.println("Non - Admin Update Failed!!!");
           
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
* @ description deleteDB() Method
* @ description This deleteDB method will go in to participant table.
* @ authorCarl Moon
* @sinceII 3-1-17 Corrected int's to strings.
* @deprecated
* @throw sql exception SQLException.
*****************************************************************************************/
public void deleteDB() throws SQLException
{
      
    PreparedStatement ps = null;
    Connection con1 = null;
    
    
 
    String sql = "delete from patient where PatientID = ?";
    
    
        try
        {
          con1 = db.dbc();
          ps = con1.prepareStatement(sql);
          
          ps.setString(1, getPatientId());

          
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
/*******************************************************************************
 * @ author Carl Moon 
 *@since 3-2-17 
 * @ description I corrected the tests for all database actions. The  RUD's of CRUD. I confirmed that the all database test work.Corrected , int's to strings.
 *@ throws SQLException
 *@catch SQL EXception
 
******************************************************************************/
public static void main(String[] args)
{
   /*
    Patient p1 = new Patient();
    DBConnection.select(p1, 1, true);
    p1.display();
    */
   /*
//Testing selectDB(String p1)
     try
    {
        Patient p1 = new Patient();
        p1.selectDB("1");
        p1.display();
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
        Patient p1 = new Patient();
        p1.insertDB("111", "Carl", "Moon", "Medicaid", "123");
        p1.display();
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
        Patient p1 = new Patient();
        p1.selectDB("111");
        p1.setLastName("Nash");
        p1.updateDBAdmin();
        p1.display();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    } */
   /*
    //Testing updateDB
    try
    {
        Patient p1 = new Patient();
        p1.selectDB("73");
        p1.setLastName("Doe Rae Me");
        p1.updateDB();
        p1.display();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
    */    /*
    
    //Testing deleteDB
    try
    {
        Patient p1 = new Patient();
        p1.selectDB("111");
        p1.display();
        p1.deleteDB();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
   */
}//end main method   

}// endpublic class User 