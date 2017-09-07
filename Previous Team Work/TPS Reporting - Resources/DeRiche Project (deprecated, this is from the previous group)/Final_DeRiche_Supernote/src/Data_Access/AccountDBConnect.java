/*
 * CIST2931-Advanced Systems Project
 * Team 1 AKA Code Monkeys:
 *      Team Lead AKA who to blame for things: Triston Gregoire
 *      Team: Bonnie Falk, Bunmi Bamiro, Kristen Dawes, Natacha Mosala, Taylor Smith
*/
package Data_Access;

import Business.AccountLogic;
import Presentation.AdminForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles all transactions between the business classes and the database when it comes to Account changes
 * @author Triston_Gregoire
 */
public class AccountDBConnect implements Data {    

    /**
     * Updates password in the database for the row that matches the information in the parameter
     * @param object AccountLogic object which should be populated with at least the oldPassword and Username
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updatePassword(AccountLogic object) throws ClassNotFoundException, SQLException{
        String sql = "update user set Password = ? where UserName = ?";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setString(1, object.getNewPassword());
            prepared.setString(2, AdminForm.activeUser);
            prepared.execute();
        }
    }
    
    /**
     * Queries database for the row which matches matches the parameters.
     * @param username username
     * @param password password
     * @return String of the returned password from the query
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String validatePassword(String username, String password) throws SQLException, ClassNotFoundException{
        String sql = "Select * from User where UserName = ? AND Password = ?";
        String newPass = null;
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setString(1, username);
            prepared.setString(2, password);
            ResultSet result = prepared.executeQuery();
            result.next();
            newPass = result.getString("Password");
        }
        return newPass;
    }
}
