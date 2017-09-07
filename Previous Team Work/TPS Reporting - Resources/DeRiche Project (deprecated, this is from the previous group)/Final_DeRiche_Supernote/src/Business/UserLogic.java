/*
 * CIST2931-Advanced Systems Project
 * Team 1 AKA Code Monkeys:
 *      Team Lead AKA who to blame for things: Triston Gregoire
 *      Team: Bonnie Falk, Bunmi Bamiro, Kristen Dawes, Natacha Mosala, Taylor Smith
*/
/*
 * CIST2931-Advanced Systems Project
 * Team 2 AKA Ctrl/Alt/Delicious:
 *      Team Lead: Tyrus Skipper
 *      Team: Steven Alcorn, Zachary Weaver, Antonio Mosquera, Rupa Shrestha
 */
package Business;

import Data_Access.UserDBConnect;
import java.sql.SQLException;

/**
 *
 * @author Triston_Gregoire
 */
public class UserLogic {
    private String username = null;
    private String oldUserName = null;
    private String password = null;
    private String oldPassowrd = null;
    private String firstName = null;
    private String lastName = null;
    private String title = null;
    private int clearance = -1;
    private int userID = -1;

    /**
     *  Data Access Object
     */
    public UserDBConnect uc = new UserDBConnect();

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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the oldUserName
     */
    public String getOldUserName() {
        return oldUserName;
    }

    /**
     * @param oldUserName the oldUserName to set
     */
    public void setOldUserName(String oldUserName) {
        this.oldUserName = oldUserName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the oldPassowrd
     */
    public String getOldPassowrd() {
        return oldPassowrd;
    }

    /**
     * @param oldPassowrd the oldPassowrd to set
     */
    public void setOldPassowrd(String oldPassowrd) {
        this.oldPassowrd = oldPassowrd;
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
     * @return the user title
     */
    public String getUserTitle() {
        return title;
    }
    
    /**
     * 
     * @param title the title to set
     */
    public void setUserTitle(String title) {
        this.title = title;
    }
    
    /**
     * @return the clearance
     */
    public int getClearance() {
        return clearance;
    }

    /**
     * @param clearance the clearance to set
     */
    public void setClearance(int clearance) {
        this.clearance = clearance;
    }
    
    
    /**
     * Accepts String parameters representing the user's credentials entered via the UI. 
     * Authenticates the inputted credentials and returns the appropriate boolean. True for input that matches a row in the database. false otherwise
     * 
     * 
     * @param username username inputted by the user. username must be unique (No duplicates in database).
     * @param password password inputted by the user. Case sensitive
     * @return returns true if both username and password parameters match a row in the database. Otherwise it returns false 
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean isValid(String username, String password) throws SQLException, ClassNotFoundException{
        boolean result = false;
        UserLogic inputtedCredentials = new UserLogic();            //stores inputted credentials
        UserLogic SystemCredentials = null;                       //stores credentials from database
        inputtedCredentials.setUsername(username);
        inputtedCredentials.setPassword(password);
        uc = new UserDBConnect();
        SystemCredentials = uc.login(inputtedCredentials);
        // reult = true if inputtedCredentials math System Credentials
        // In other words if the inputted username and password exactly match that of a row in the database then result = true
        result = inputtedCredentials.getUsername().equalsIgnoreCase(SystemCredentials.getUsername()) && inputtedCredentials.getPassword().equals(SystemCredentials.getPassword());
        return result;
    }
    
    /**
     * Accepts username from drop down list in the UI and pulls all the user information from the database.
     * 
     * @param username username selected from the corresponding drop down list in the UI. 
     * @return populated UserLogic object
     * @throws ClassNotFoundException
     * @throws ArrayIndexOutOfBoundsException
     * @throws SQLException
     */
    
    public UserLogic select(Object username) throws ClassNotFoundException, ArrayIndexOutOfBoundsException, SQLException{
        //Java's drop down lists return the selected Items as Objects so I type cast it to String.
        this.setUsername((String) username);
        uc = new UserDBConnect();
        return uc.userSelect(this); //return
        
    }
    
    /**
     * Passes integer parameter to data access class for a query and returns resulting UserLogic object 
     * @param ID integer representing the user ID of a user
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public UserLogic select(int ID) throws ClassNotFoundException, SQLException{
        UserLogic ul = uc.userSelect(ID);
        return ul;
    }
    
    /**
     * populates this instance with the parameters passed and then passes this instance to a Data Access method for insertion into the database 
     * 
     * @param username String representing the username of a user
     * @param password String representing the password of a user
     * @param firstName String representing the first name of a user
     * @param lastName String representing the last name of a user
     * @param title String representing the title of the user
     * @param clearance Object representing the user level of the user. method casts clearance to a String before manipulating it
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void insertUser(String username, String password, String firstName
            , String lastName, String title, Object clearance) throws SQLException, ClassNotFoundException{
        uc = new UserDBConnect();
        int modifiedClearance = clearanceCheck((String) clearance);
        this.setUsername(username);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUserTitle(title);
        this.setClearance(modifiedClearance);
        uc.insertUser(this);
    }
    
    /**
     * Passes this instance to the User data access class to update the corresponding row in the database for the User. This method should only be called by an instance that has all of its variables already populated
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void updateUser() throws SQLException, ClassNotFoundException{
        //Obviously having a method that only operates correctly when used by an object that has been manually poulated is a bad idea.
        //This method should eventually accept parameters before populating a UserLogic object.
        uc = new UserDBConnect();
        uc.update(this);        
    }
    
    
     /**
     * Method accepts the user clearance level pulled from GUI and converts it into an integer
     * Integer representation of clearance is used for storing in the database
     * 0 is an Admin level
     * 1 is a Reviewer and has the second most privileges 
     * 2 is a DCP and has the third most privileges
     * 3 is an Auditor which as of yet has not been implemented 
     * 4 is a User that has been terminated from the company and has no privileges
     * 
     * @param clearance  String clearance variable pulled from the GUI. 
     * @return returns an Integer representation of Clearance to be stored in database
     */
    public int clearanceCheck(String clearance){
        int newClearance = -1;
        switch(clearance){
            case "Admin":
                newClearance = 0;
                break;
            case "Reviewer":
                newClearance = 1;
                break;
            case "DCP":
                newClearance = 2;
                break;
            case "Auditor":
                newClearance = 3;
                break;
       //RUPA ADDED AUDITORPRINT
//            case "AuditorPrint":
//                newClearance = 4;
//                break;
            case "Terminated":
                newClearance = 4;
                break;
        }
        return newClearance;
    }
    
    /**
     * override of clearanceCheck method that turns an integer clearance to its String equivalent for display in the GUI
     * @param clearance
     * @return String representation of the user's clearance level to be used in the GUI
     */
    public String clearanceCheck(int clearance){
        String newClearance = null;
        switch(clearance){
            case 0:
                newClearance = "Admin";
                break;
            case 1:
                newClearance = "Reviewer";
                break;
            case 2:
                newClearance = "DCP";
                break;
            case 3:
                newClearance = "Auditor";
                break;
            case 4: 
                newClearance = "Terminated";
                break;
        }
        return newClearance;
    }
}


