/*
 * CIST2931-Advanced Systems Project
 * Started: Spring 2016
 * Team 1 AKA Code Monkeys:
 *      Team Lead AKA who to blame for things: Triston Gregoire
 *      Team: Bonnie Falk, Bunmi Bamiro, Kristen Dawes, Natacha Mosala, Taylor Smith
 *
 *
 * A few things to understand about the project:
 *      1) It was built with java 1.8.0_05 on Netbeans 8.0.2 and uses a MySQL connecter version 5.1.37
 *      and is compatible with a MySQL database of at least version 10.0.17-MariaDB.
 *      2) Program assumes the database name is 'deriche'
 *      3) Customer's server information as of April 22nd, 2016:
            IP address:192.168.1.150
            username = chattech
            password = codemonkeys
        4) The User Interface is essentially just one form. the AdminForm handles all major interactions between the user and the system
            In hindsight it was not the best idea to put all components into one class like we did. 
            If you can break it up without destroying the project then I'd recommend doing so just for flexibility sake.
        5) Data access variables like the driver string and database url is in the Data class. Every data access class implements from the Data class
        6) In the business classes you'll often see objects casted to a String and split with a colon acting as the delimiter(Spelling?)
            This is because of the way I populated the dropdowns in the GUI
            The dropdowns are formatted in the form of 'idnumber:name with the colon being the delimiter (for example   7:Triston Gregoire)
            The objects in the dropdown lists are returned in that form as well. 
            The reason they are formatted this way is simply because it then makes it easier to obtain the ID for a selected item in a list
            When I need the ID number of a selected item in a list I split the returned object by the colon
            This is done via the String.split method shown here for example: String[] array = stringToBeSplit.split(":")[0]));
            In practice 7:TristonGregoire becomes array[0] = 7, array[1] = Triston Gregoire
            There is likely a better way to do all of this but the given time constraints didn't allow for much refactoring.
            I suppose that's where you(The next team) come in.
        7) Business classes with similar names are related.
            Classes ending with DBConnect are data access classes and classes ending with Logic are business logic classes
            Classes who both have the same keywords like Goal, for example, both work in tandem to manipulate the Goal table in the database. Same goes for Note, participant, etc
        8) There is virtually NO validation. Validation is easy but time consuming so we focused simply on getting it working.

*

 *
 *
 *
 */
package Business;
import Presentation.*;
import java.sql.SQLException;


/**
 *
 * @author Triston_Gregoire
 */
public class DeRiche_Supernote {

    /**
     * This method creates the Login Form on startup
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        LoginForm start = new LoginForm();
        start.setVisible(true);
    }
    
}
