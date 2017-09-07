/*
 * CIST2931-Advanced Systems Project
 * Team 1 AKA Code Monkeys:
 *      Team Lead AKA who to blame for things: Triston Gregoire
 *      Team: Bonnie Falk, Bunmi Bamiro, Kristen Dawes, Natacha Mosala, Taylor Smith
*/
package Data_Access;

/**
 * Interface that defines some essential variables for the data access classes. Centralizes said variables for easy changes
 * @author Triston_Gregoire
 * 
 * 
 * 
 * 
 * It was a pain in the ass going back and changing all these variables 
 * so I decided to make an interface so future teams could make the change here 
 *  and have it cascade to all other files which implement this interface 
 * You're welcome
 */
public interface Data {
    /**************************************************************************
    *   CHANGING THESE CAN BREAK THE PROGRAM                                  *
    *   IF YOU CHANGE THEM MAKE SURE YOU UNDERSTAND HOW/WHY THEY'RE USED FIRST*
    *   OR YOU COULD JUST CHANGE THEM WILLY NILLY                             *
    *   I COULDN'T CARE LESS AT THIS POINT                                    *
    **************************************************************************/
    
    //we used the default 'root' for our username and no password. Change these to match your team's MySQL login
    //public static final String databaseUser = "root";           //For development
    //public static final String databasePass = "";               //For development 
    
    public static final String databaseUser = "root";           
    public static final String databasePass = "n3m3s1s2006+";
   // public static final String databaseUser = "chattech" ;  //For deployment to deriche's server
    //public static final String databasePass = "codemonkeys";    //For deployment to deriche's server
    
    
    public static final String driver = "com.mysql.jdbc.Driver";                //Used in every data access class

    /**
     *
     */
    public static final String database = "jdbc:mysql://localhost:3306/DeRiche?";    //Used in every data access class
    
    /**  Used to handle default Timestamp storage in MySQL                                           
    *   MySQL doesn't handle empty Time objects very well so it needs to be set to a default beforehand
    *   In this case an empty time like '0000-00-00' will be set to null in the database
    *   As of April 22nd this variable is only used in NoteDBConnect
    */
    
    public static final String  databaseTime = "jdbc:mysql://localhost:3306/DeRiche?user=root&zeroDateTimeBehavior=convertToNull";
    
    //Timezone. As of April 22nd this variable is only used in NoteDBConnect

    /**
     * Time zone specified for all Timestamps taken from database. 
     */
        public static final String zone = "America/New_York";
}
