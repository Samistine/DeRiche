/******************************************************************************************
* @author Carl Moon, documentaion Yasmina Rabhi
* @since 3-28-17
* @description User business object for DeRichie web app.
*
*****************************************************************************************/
package Business;

import dataAccess.db;
import dataAccess.Access;
import dataAccess.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/******************************************************************************************
* 
* @ description User main class for DeRichie web app.


*****************************************************************************************/
public class User extends Access {

    	//User Identification Number
	private String userId;
    //User Name
	public String userName;
	//Password
    private String password;
    //Pin Number
    private String pin;
    //First Name
    private String firstName;
    //Last Name
    private String lastName;
    //User title
    private String title;
    //Clearance level
    private String clearance;

    /**
     * Constructs a new instance of a User object
     */
	public User() {
            
	}
	
    /**
     * Constructs a new instance of a User object with all of its attributes set
     */
    public User(String userId, String userName, String password, String pin, String firstName, String lastName,
			String title, String clearance) {
    	
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.pin = pin;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.clearance = clearance;
	}

    /**
     * @return the userId
     */
    public String getUserId() {
    	return userId;
    }
    
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
    	this.userId = userId;
    }
    
    /**
     * @return the userName
     */
    public String getUserName() {
    	return userName;
    }
    
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
    	this.userName = userName;
    }
    
    /**
     * @return the password
     */
    public  String getPassword() {
		return password;
	}

    /**
     * @param password the password to set
     */
	public void setPassword(String password) {
		this.password = password;
	}
	
    /**
     * @return the pin
     */
    public String getPin() {
    	return pin;
    }
    
    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
    	this.pin = pin;
    }

    /**
     * @return the firstName
     */
	public String getFirstName() {
		return firstName;
	}

    /**
     * @param firstName the firstName to set
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    /**
     * @return the lastName
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
     * @return the title
     */
	public String getTitle() {
		return title;
	}

    /**
     * @param title the title to set
     */
	public void setTitle(String title) {
		this.title = title;
	}

    /**
     * @return the clearance
     */
	public String getClearance() {
		return clearance;
	}

    /**
     * @param clearance the clearance to set
     */
	public void setClearance(String clearance) {
		this.clearance = clearance;
	}
    
/******************************************************************************************
 * 
*  User display for testing.
*@description it dispalys user id,user name,password,pin,first anme ,last name,title,and clearense 
 *****************************************************************************************/
    public void display()
    {
        System.out.println("Begin User.java display ");
        System.out.println("User Id: "+getUserId());
        System.out.println("User Name: "+getUserName());
        System.out.println("Password: "+getPassword());
        System.out.println("Pin: "+getPin());
        System.out.println("First Name: "+getFirstName());
        System.out.println("Last Name: "+getLastName());
        System.out.println("Title: "+getTitle());
        System.out.println("Clearence: "+getClearance());
        System.out.println("End User.java display ");
    }//end display
/******************************************************************************************
* SelectDB() Method
*@ description This selectDB method will go in to the Appointments table in the Dentist DB.
*@param un user id
*
*@throws SQLException
*****************************************************************************************/
public void selectDB(String un) throws SQLException
{
    //userId = un;
    userName = un;
    
    Connection con1 = null;
    PreparedStatement ps = null;
    
    String sql = "Select * from user where UserName = ?";
    
        try
        {
            con1 = db.dbc();
            ps = con1.prepareStatement(sql);
            ps.setString(1, getUserName());
            //ps.setString(2, getPassword());
            //execute sql statement
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            { 
                setUserId(rs.getString(1));
                setUserName(rs.getString(2));
                setPassword(rs.getString(3));
                setPin(rs.getString(4));
                setFirstName(rs.getString(5));
                setLastName(rs.getString(6));
                setTitle(rs.getString(7));
                setClearance(rs.getString(8));
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
*@description Inserts into the User table.
*@param ui user id
*@param un user name
*@param r password
*@param p pin
*@param f first name
*@param l last name
*@param t title 
*@param c clerance
*@throws SQLException e
*@deprecated
*******************************************************************************/

public void insertDB(String ui, String un, String r, String p, String f, String l, String T, String C) throws SQLException
{
   userId = ui;
   userName = un;
   password = r;
   pin = p;
   firstName = f;
   lastName = l;
   title = T;
   clearance = C;     

    Connection con1 = null;
    PreparedStatement ps = null;
    
    String sql = "insert into user"
                                      +"(UserID, UserName, Password, PIN, FirstName, LastName, Title, Clearance) values"
            
                                      +"(?,?,?,?,?,?,?,?)";
        
    try
    {
        con1 = db.dbc();
        ps = con1.prepareStatement(sql);
        
        ps.setString(1, getUserId());
        ps.setString(2, getUserName());
        ps.setString(3, getPassword());
        ps.setString(4, getPin());
        ps.setString(5, getFirstName());
        ps.setString(6, getLastName());
        ps.setString(7, getTitle());
        ps.setString(8, getClearance());
        
        
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
** @description updateDBAdmin() Method
* @description This will allow the admin to update
* @description the user table in the in the DeRichie DB.
* @deprecated
*****************************************************************************************/
public void updateDBAdmin() throws SQLException
{
    Connection con1 = null;
    PreparedStatement ps = null;
    
    String sql = "update user set UserName =?, Password = ?, PIN = ?, FirstName = ?, LastName = ?, Title = ?, Clearance = ? Where UserID = ?";
    
    try
    {
        con1 = db.dbc();
        ps = con1.prepareStatement(sql);
        
        
        ps.setString(1, getUserName());
        ps.setString(2, getPassword());
        ps.setString(3, getPin());
        ps.setString(4, getFirstName());
        ps.setString(5, getLastName());
        ps.setString(6, getTitle());
        ps.setString(7, getClearance());
        ps.setString(8, getUserId());
        
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
* updateDB() Method
* @description This updateDB method will allow a non admin  user to update the user table with limited access in the in the DeRichie DB.
* @deprecated
*****************************************************************************************/
public void updateDB() throws SQLException
{
    Connection con1 = null;
    PreparedStatement ps = null;
    
    String sql = "update user set Password = ?, PIN = ? Where UserID = ?";
    
    try
    {
        con1 = db.dbc();
        ps = con1.prepareStatement(sql);
        
        
        ps.setString(1, getPassword());
        ps.setString(2, getPin());
        ps.setString(3, getUserId());
        
        
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
* deleteDB() Method
* This deleteDB method will go in to the Patients table in the Dentist DB.
* @deprecated
*****************************************************************************************/
public void deleteDB() throws SQLException
{
      
    PreparedStatement ps = null;
    Connection con1 = null;
    
    
 
    String sql = "delete from user  where UserID = ?";
    
    
        try
        {
          con1 = db.dbc();
          ps = con1.prepareStatement(sql);
          
          ps.setString(1, getUserId());

          
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
  
//Testing selectDB(String un)
     try
    {
        User us = new User();
        us.selectDB("Admin");
        
        us.display();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
     
    /*
     //Testing insertDB
    try
    {
        User us = new User();
        us.insertDB("5", "cmoon10", "cmoon10", "3124", "Carl", "Moon", "Admin", "0");
        us.display();
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
        User us = new User();
        us.selectDB("5");
        us.setPass("pass");
        us.updateDBAdmin();
        us.display();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    } */
   /*
    //Testing updateDB
    try
    {
        User us = new User();
        us.selectDB("5");
        us.setPass("pass1");
        us.updateDB();
        us.display();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
    */
    /*
    //Testing deleteDB
    try
    {
        User us = new User();
        us.selectDB("9");
        us.display();
        us.deleteDB();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
   */
}//end main method   

}// endpublic class User 