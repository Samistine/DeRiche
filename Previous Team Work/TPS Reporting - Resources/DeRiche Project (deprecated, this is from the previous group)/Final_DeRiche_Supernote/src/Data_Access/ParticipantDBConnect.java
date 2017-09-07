/*
 * CIST2931-Advanced Systems Project
 * Started: Spring 2016
 * Team 1 AKA Code Monkeys:
 *      Team Lead AKA who to blame for things: Triston Gregoire
 *      Team: Bonnie Falk, Bunmi Bamiro, Kristen Dawes, Natacha Mosala, Taylor Smith
*/
package Data_Access;

import Business.ParticipantLogic;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Triston_Gregoire
 */
public class ParticipantDBConnect implements Data{
   
    
    /**
     * Inserts participant information into the database
     * @param pl ParticipantLogic object that has all of it's variables filled to be added to the database
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void insertParticipant(ParticipantLogic pl) throws ClassNotFoundException, SQLException{
        String sql = "Insert into Participant ( FirstName, LastName, Insurance, MedicaidNumber) values (?,?,?,?)";
        Class.forName(driver);
        try (Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)) {
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setString(1, pl.getParticipantFirstName());
            prepared.setString(2, pl.getParticipantLastName());
            prepared.setString(3, pl.getInsurance());
            prepared.setString(4,pl.getMedicaidNumber());
            prepared.execute();
        }
    }

    /**
     * Selects All user information from the database then inserts all fields into a ParticipantLogic object. 
     * Iterates over the entire result set and creates a new object for each row returned from the database
     * 
     * @return ArrayList containing ParticipantLogic objects
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ArrayList<ParticipantLogic> selectAllParticipants() throws SQLException, ClassNotFoundException{
        String sql = "Select * from Participant";
        Class.forName(driver);
        try(Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)){
            PreparedStatement prepared = connect.prepareStatement(sql);
            ResultSet rs = prepared.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            ArrayList<ParticipantLogic> participantList = new ArrayList<>();
            while(rs.next()){
                ParticipantLogic participant = new ParticipantLogic();
                participant.setParticipantID(rs.getInt(1));
                participant.setParticipantFirstName(rs.getString(2));
                participant.setParticipantLastName(rs.getString(3));
                participant.setInsurance(rs.getString(3));
                participant.setMedicaidNumber(rs.getString(4));
                participantList.add(participant);
            }
            return participantList;
        }
    }

    /**
     * updates row in the database that corresponds to the Participant passed as a parameter
     * @param object ParticipantLogic object that has been populated so its information can be added to the database
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void updateParticipant(ParticipantLogic object) throws SQLException, ClassNotFoundException{
        String sql = "update participant set Firstname = ?, Lastname = ?, Insurance = ?, MedicaidNumber = ? where ParticipantID = ?";
        Class.forName(driver);
        try(Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)){
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setString(1, object.getParticipantFirstName());
            prepared.setString(2, object.getParticipantLastName());
            prepared.setString(3, object.getInsurance());
            prepared.setString(4, object.getMedicaidNumber());
            prepared.setInt(5,object.getParticipantID());
            prepared.execute();
        }
            
    }

    /**
     * Selects a row from the database and populates a ParticipantLogic object that it then returns
     * @param object Participant Logic object that already has its participantID variable populated
     * @return returns a fully populated ParticipantLogic object
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ParticipantLogic selectParticipant(ParticipantLogic object) throws SQLException, ClassNotFoundException{
        ParticipantLogic pl = object;
        //System.out.println(pl.getParticipantID());
        String sql = "select * from Participant where ParticipantID = ?";
        Class.forName(driver);
        try(Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)){
            PreparedStatement prepared = connect.prepareStatement(sql);
            prepared.setInt(1, pl.getParticipantID());
            ResultSet result = prepared.executeQuery();
            while(result.next()){
            pl.setParticipantFirstName(result.getString(2));
            pl.setParticipantLastName(result.getString(3));
            pl.setInsurance(result.getString(4));
            pl.setMedicaidNumber(result.getString(5));
            }
        }
        return pl;
    }

    public ParticipantLogic selectPartMed(String med) throws ClassNotFoundException, SQLException {
        ParticipantLogic part = new ParticipantLogic();
        //System.out.println(pl.getParticipantID());
        String sql = "select * from Participant where MedicaidNumber = '" + med + "';";
        Class.forName(driver);
        try(Connection connect = DriverManager.getConnection(database, databaseUser, databasePass)){
            Statement state = connect.createStatement();
            ResultSet result = state.executeQuery(sql);
            while(result.next()){
                part.setParticipantID(result.getInt("ParticipantID"));
                part.setParticipantFirstName(result.getString("Firstname"));
                part.setParticipantLastName(result.getString("Lastname"));
                part.setInsurance(result.getString("Insurance"));
                part.setMedicaidNumber(result.getString("MedicaidNumber"));
            }
            
        }
        return part;
    }

}
