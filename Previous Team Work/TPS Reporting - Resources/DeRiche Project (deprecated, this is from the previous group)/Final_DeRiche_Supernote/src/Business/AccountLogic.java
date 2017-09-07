/*
 * CIST2931-Advanced Systems Project
 * Team 1 AKA Code Monkeys:
 *      Team Lead AKA who to blame for things: Triston Gregoire
 *      Team: Bonnie Falk, Bunmi Bamiro, Kristen Dawes, Natacha Mosala, Taylor Smith
*/
package Business;

import Data_Access.AccountDBConnect;
import java.sql.SQLException;

/**
 * Handles AccountLogic changes such as password updates
 * @author Flory
 * 
 * I never got around to finishing this class. Doesn't do much as is but the framework is there.
 */
public class AccountLogic {
    private String oldPassword;
    private String newPassword;
    private String username;

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
     *
     * @return
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     *
     * @param oldPassword
     */
    public void setOldPassword(char[] oldPassword) {
        
        this.oldPassword = charArrayToString(oldPassword);
    }

    /**
     *
     * @return newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     *
     * @param newPassword
     */
    public void setNewPassword(char[] newPassword) {
        this.newPassword = charArrayToString(newPassword);
    }

    /**
     * Accepts a character array and does stuff with them
     * @param newPassword
     * @throws ClassNotFoundException
     * @throws SQLException
     * 
     */
    public void updateAccount(char[] newPassword) throws ClassNotFoundException, SQLException{
        //Password fields return character arrays causing the need for this method to accept a character Array
        AccountDBConnect ac = new AccountDBConnect();
        this.setNewPassword(newPassword);
        ac.updatePassword(this);
    }
    
    /**
     * Converts character array into a string which contains all the elements from the character array in the same order.
     * @param array of characters
     * @return String representation of the Character Array elements in the order they were originally stored in the character array
     * 
     */
    public String charArrayToString(char[] array){
        //This method is innefficient seeing as how you could just to str = new String(array) and get same result.
        //Method should be deprecated and deleted once you are sure doing so won't break the program
        String str = "";
        for(int i = 0; i < array.length; i++){
            str = str + array[i];
        }
        return str;
    }
    
    /**
     * Validates the user's login before allowing them to change passwords
     * @param username String representing the username of the user logged into the system
     * @param password String representing the password entered by the user
     * @return boolean value representing whether or not the current password inputted is correct
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean validate(String username, String password) throws SQLException, ClassNotFoundException{
        AccountDBConnect adb = new AccountDBConnect();
        boolean isValid = false;
        if(password.equals(adb.validatePassword(this.getUsername(), this.getOldPassword()))){//If password entered equals the password from database for the user.
            isValid = true;
        }
        return isValid;
    }

    /**
     * Validates the user's login before allowing them to change passwords
     * @param username String representing the username of the user logged into the system
     * @param passwordArray Character Array representing the password entered by the user
     * @return String representing the password of the user as it is in the database
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean validate(String username, char[] passwordArray) throws SQLException, ClassNotFoundException{
        AccountDBConnect adb = new AccountDBConnect();
        boolean isValid = false;
        String password = new String(passwordArray);
        if(password.equals(adb.validatePassword(username, password))){//If password entered equals the password from database for the user.
            isValid = true;
        }
        return isValid;
    }
    
    
}
