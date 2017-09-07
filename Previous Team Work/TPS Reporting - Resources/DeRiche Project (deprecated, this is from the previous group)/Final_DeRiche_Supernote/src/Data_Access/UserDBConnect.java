/*
 * CIST2931-Advanced Systems Project
 * Started: Spring 2016
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
package Data_Access;

import Business.UserLogic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Triston_Gregoire
 */
public class UserDBConnect implements Data {
    
    private String username = null;
    private String password = null;
    private String firstName = null;
    private String lastName = null;
    private String title = null;
    private int clearance = -1;
    


    
    /**
     * Compares parameters to the database to determine if it has been passed a valid login
     * @param object
     * @return UserLogic object
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public UserLogic login(UserLogic object) throws SQLException, ClassNotFoundException{
        boolean isLoggedIn = false;  
            UserLogic obj = new UserLogic();
            String sql = "Select UserName, Password from User where UserName = ?";
            Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setString(1, object.getUsername());
            ResultSet result = prepared.executeQuery();
            ResultSetMetaData meta = result.getMetaData();
            while(result.next()){
                obj.setUsername(result.getString(1));
                obj.setPassword(result.getString(2));
                String credentials = result.getString(1) + "-" + result.getString(2);
            }
        }
        return obj;
    }

    /**
     * Pulls all users from the User table of the database and populates an ArrayList with all users
     * Method should be used to return ArrayLists for the purpose of filling GUI elements with user information
     * 
     * @return Returns Array list containing list of all users' first and last name
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ArrayList<String> populateUser() throws SQLException, ClassNotFoundException{
        ArrayList userList = new ArrayList();
        String sql = "Select UserName from User where Clearance != 4";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            ResultSet result = prepared.executeQuery();
            int i = 0;
            
            //Runs until the end of the result set is reached
            //Places the first and last name of each user into userList
            while(result.next()){
                String user;
                user = result.getString(1);
                userList.add(i, user);
                System.err.println(user);
                i++;
            }
        }
        return userList;
    }
    
    /**
     * Selects user from database and returns a concatenated String.
     * @param object
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public UserLogic userSelect(UserLogic object) throws ClassNotFoundException, SQLException{
        String sql = "Select * from User where UserName = ?";
        UserLogic ul = object;
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setString(1, ul.getUsername());
            ResultSet result = prepared.executeQuery();
            String rs = "";
            if(!result.isBeforeFirst()){
                rs = null;
            }
            else{
                result.next();
                ul.setUserID(result.getInt(1));
                ul.setUsername(result.getString(2));
                ul.setPassword(result.getString(3));
                ul.setFirstName(result.getString(4));
                ul.setLastName(result.getString(5));
                ul.setUserTitle(result.getString(6));
                ul.setClearance(result.getInt(7));
            }
        }
        return ul;
    }

    /**
     * override of userSelect that simply accepts an int to search the database for
     * @param userID
     * @return returns UserLogic populated with information from the database
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public UserLogic userSelect(int userID) throws ClassNotFoundException, SQLException{
        String sql = "Select * from User where UserID = ?";
        UserLogic ul = new UserLogic();
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setInt(1, userID);
            ResultSet result = prepared.executeQuery();
            while(result.next()){
                ul.setUserID(userID);
                ul.setUsername(result.getString(2));
                ul.setLastName(result.getString(3));
                ul.setFirstName(result.getString(4));
                ul.setLastName(result.getString(5));
                ul.setUserTitle(result.getString(6));
                ul.setClearance(result.getInt(7));
            }
        }
        return ul;
    }
    
    /**
     * Deletes entry from database
     * @param userID
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void deleteUser(String userID) throws ClassNotFoundException, SQLException{
        String sql = "Delete from user where UserName = ?";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setString(1,userID);
            prepared.execute();
        }        
    }
    

    
    /**
     * Inserts information into the User table of the database
     * 
     * @param object
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public void insertUser(UserLogic object) throws SQLException, ClassNotFoundException{
        //ArrayList <String> list = populateUser();
        String sql = "Insert into User (UserName, PASSWORD, FirstName, LastName, Title, Clearance)"
                + "values (?,?,?,?,?,?)";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setString(1, object.getUsername());
            prepared.setString(2, object.getPassword());
            prepared.setString(3, object.getFirstName());
            prepared.setString(4, object.getLastName());
            prepared.setString(5, object.getUserTitle());
            prepared.setInt(6, object.getClearance());
            prepared.execute();
        }
    }  

    /**
     *
     * Updates entry in the database
     * 
     * @param object
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void update(UserLogic object) throws SQLException, ClassNotFoundException{
        String sql = "update user set UserName = ?, Password = ?, FirstName = ?,"
                + " LastName = ?, Title = ?, Clearance = ? where UserName = ?";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setString(1, object.getUsername());
            prepared.setString(2, object.getPassword());
            prepared.setString(3, object.getFirstName());
            prepared.setString(4, object.getLastName());
            prepared.setString(5, object.getUserTitle());
            prepared.setInt(6, object.getClearance());
            prepared.setString(7, object.getOldUserName());
            prepared.execute();
        }
    }
    
    /**
     * 
     * @param user
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public UserLogic selectByID(UserLogic user) throws ClassNotFoundException, SQLException{
        String sql = "Select from User where UserID = ?";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setInt(1, user.getUserID());
            ResultSet result = prepared.executeQuery();
            user.setUsername(result.getString(2));
            user.setPassword(result.getString(3));
            user.setFirstName(result.getString(4));
            user.setLastName(result.getString(5));
            user.setUserTitle(result.getString(6));
            user.setClearance(result.getInt(7));
    }
        return user;
    }       
    /**
     * This boolean checks to see if the user exists within the database.
     * @param username
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static boolean doesExist(String username) throws ClassNotFoundException, SQLException{
        String sql = "Select * from User where Username = ?";
        Class.forName(driver);
        boolean exists = true;
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setString(1, username);
            ResultSet result = prepared.executeQuery();
            if(!result.next()){
                exists = false;
            }
        }
            return exists;
    }
}
