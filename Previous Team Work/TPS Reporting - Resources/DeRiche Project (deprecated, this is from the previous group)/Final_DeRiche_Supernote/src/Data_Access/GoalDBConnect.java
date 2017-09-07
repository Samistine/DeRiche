 /*
 * CIST2931-Advanced Systems Project
 * Team 1 AKA Code Monkeys:
 *      Team Lead AKA who to blame for things: Triston Gregoire
 *      Team: Bonnie Falk, Bunmi Bamiro, Kristen Dawes, Natacha Mosala, Taylor Smith
 * The Delete method in this class has yet to be implemented. 
*/
package Data_Access;

import Business.GoalLogic;
import static Data_Access.Data.databasePass;
import static Data_Access.Data.databaseTime;
import static Data_Access.Data.databaseUser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Handles all transactions between the business classes and the database when it comes to Goal changes
 * @author Triston_Gregoire
 */
public class GoalDBConnect implements Data{

    
    /**
     * Retrieves all goals from database that belong to the participant identified by the int parameter
     * @param participantID
     * @return Array list containing multiple Goal Logic objects
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<GoalLogic> selectGoals(int participantID) throws ClassNotFoundException, SQLException{
        ArrayList<GoalLogic> list = new ArrayList();
        String sql = "Select * from Goal where ParticipantID = ? order by GoalID";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setInt(1, participantID);
            ResultSet result = prepared.executeQuery();
            
            while(result.next()){
                GoalLogic gl = new GoalLogic();
                gl.setGoalID(result.getInt(1));
                gl.setDescription(result.getString(2));
                gl.setObjective(result.getString(3));
                gl.setGuidanceNote(result.getString(4));
                gl.setFrequency(result.getInt(5));
                gl.setIsWeekly(result.getInt(6));
                gl.setParticipantID(result.getInt(7));
                list.add(gl);
            }
        }
        return list;
    }

    /**
     * Inserts information from passed GoalLogic into the database
     * Empty Goal Logic objects will throw a NullPointer if it has not been initialized and a SQL error if it has not been populated correctly
     * @param object populated GoalLogic object with information to be inserted into the database
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void insertGoal(GoalLogic object) throws ClassNotFoundException, SQLException{
        String sql = "Insert into Goal (Description, Objective, GuidanceNote, Frequency, isWeekly, ParticipantID )values (?,?,?,?,?,?)";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            //prepared.setInt(1, object.getGoalID());
            prepared.setString(1, object.getDescription());
            prepared.setString(2, object.getObjective());
            prepared.setString(3, object.getGuidanceNote());
            prepared.setInt(4, object.getFrequency());
            prepared.setInt(5, object.getIsWeekly());
            prepared.setInt(6, object.getParticipantID());
            prepared.execute();
        }
    }

    /**
     * Deletes a Goal row from the database that corresponds the the GoalLogic passed to this method.
     * Currently is note used as I ran out of time/neglected to implement
     * @param object GoalLogic object. presumably already populated
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteGoal(GoalLogic object) throws SQLException, ClassNotFoundException{
        String sql = "delete from Goal where GoalID = ?";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setInt(1, object.getGoalID());
            prepared.execute();
        }        
    }

    /**
     * Updates Row in the database that corresponds the GoalLogic object passed to the method
     * @param object GoalLogic object. presumably already populated
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void updateGoal(GoalLogic object) throws SQLException, ClassNotFoundException{
        String sql = "update Goal set Description = ?, Objective = ?, GuidanceNote = ?, Frequency = ?, isWeekly = ? where GoalID = ?";
        Class.forName(driver);
        try(Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)){
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setString(1, object.getDescription());
            prepared.setString(2, object.getObjective());
            prepared.setString(3, object.getGuidanceNote());
            prepared.setInt(4, object.getFrequency());
            prepared.setInt(5, object.getIsWeekly());
            prepared.setInt(6, object.getGoalID());
            System.out.println(prepared);
            prepared.execute();            
        }            
    }
    
    /**
     * Method that is called from the AdminForm.java class to connect to the database
     * to remove a goal from the Goal table.
     * @param goalID
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void removeGoal (int goalID)throws ClassNotFoundException, SQLException{
        System.out.println("You are trying to remove a goal.");
        System.out.println(goalID);
        
        String sql = ("DELETE from Goal where GoalID = " + goalID);
        
        Class.forName(driver);
        try(Connection connect = DriverManager.getConnection(databaseTime,databaseUser,databasePass)){
            Statement statement = connect.createStatement();
            statement.executeUpdate(sql);
            
        }
    }
}
