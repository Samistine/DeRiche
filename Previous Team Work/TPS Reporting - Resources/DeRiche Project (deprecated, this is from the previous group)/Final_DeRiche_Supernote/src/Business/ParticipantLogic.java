/*
 * CIST2931-Advanced Systems Project
 * Team 1 AKA Code Monkeys:
 *      Team Lead AKA who to blame for things: Triston Gregoire
 *      Team: Bonnie Falk, Bunmi Bamiro, Kristen Dawes, Natacha Mosala, Taylor Smith
*/
package Business;

import Data_Access.ParticipantDBConnect;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Triston_Gregoire
 */
public class ParticipantLogic {
    private int participantID = -1;
    private String participantFirstName;
    private String participantLastName;
    private String insurance;
    private String medicaidNumber;

    /**
     * Data Access Object
     */
    public ParticipantDBConnect pdb = new ParticipantDBConnect();
    
    /**
     * GoalLogic object used to access various goals that A participant may have
     */
    public GoalLogic gl = new GoalLogic();

    
    /**
     * @return the participantID
     */
    public int getParticipantID() {
        return participantID;
    }

    /**
     * @param participantID the participantID to set
     */
    public void setParticipantID(int participantID) {
        this.participantID = participantID;
    }

    /**
     * @return the participantFirstName
     */
    public String getParticipantFirstName() {
        return participantFirstName;
    }

    /**
     * @param participantFirstName the participantFirstName to set
     */
    public void setParticipantFirstName(String participantFirstName) {
        this.participantFirstName = participantFirstName;
    }

    /**
     * @return the participantLastName
     */
    public String getParticipantLastName() {
        return participantLastName;
    }

    /**
     * @param participantLastName the participantLastName to set
     */
    public void setParticipantLastName(String participantLastName) {
        this.participantLastName = participantLastName;
    }

    /**
     * @return the insurance
     */
    public String getInsurance() {
        return insurance;
    }

    /**
     * @param insurance the insurance to set
     */
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    /**
     * @return the medicaidNumber
     */
    public String getMedicaidNumber() {
        return medicaidNumber;
    }

    /**
     * @param medicaidNumber the medicaidNumber to set
     */
    public void setMedicaidNumber(String medicaidNumber) {
        if("".equals(medicaidNumber)){
            this.medicaidNumber = null;
        }
        else{
            this.medicaidNumber = medicaidNumber;

        }
    }
    
    /**
     * Adds participant by passing the instance of the Participant class that called this method to ParticipantDBConnect's insertParticipant method. 
     * Should only be called from ParticipantLogic objects that have all of its variables populated.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addParticipant() throws ClassNotFoundException, SQLException{
        pdb.insertParticipant(this);
        
    }

    /**
     * Static method used to retrieve all Participants from the database in order to display in various GUI elements 
     * @return returns ArrayList of Participant Logic objects
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ArrayList<ParticipantLogic> populate() throws SQLException, ClassNotFoundException{
        ArrayList<ParticipantLogic> participants = ParticipantDBConnect.selectAllParticipants();
        return participants;
    }

    /**
     * Selects a participant by the passed integer
     * @param num integer to be used to find a specific row in the Participant Table of the database
     * @return Participant Logic representing the participant that has the ID corresponding to the method's parameter
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ParticipantLogic select(int num) throws SQLException, ClassNotFoundException{
        ParticipantLogic pl = new ParticipantLogic();
        pl.setParticipantID(num);
        pl = pdb.selectParticipant(pl);
        return pl;
    }
    
    public ParticipantLogic selectMed(String med) throws ClassNotFoundException, SQLException{
        ParticipantLogic part = new ParticipantLogic();
        part = pdb.selectPartMed(med);
        
        return part;
    }

    /**
     * Prepares ParticipantLogic object to be passed to Data access class for an update of a database row
     * @param medicaid Integer representing the medicaid number of a Participant
     * @param firstname String representing the first name of a participant
     * @param lastname String representing the last name of a participant
     * @param insurance String representing the insurance type of a participant. Can be either self-pay or medicaid
     * @param id Integer representing the ID number of a Participant
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void updateParticipant(String medicaid, String firstname, String lastname, String insurance, int id) throws SQLException, ClassNotFoundException{
        ParticipantLogic object = new ParticipantLogic();
        object = object.select(id);
        object.setInsurance(insurance);
        object.setMedicaidNumber(medicaid);
        object.setParticipantFirstName(firstname);
        object.setParticipantLastName(lastname);
        object.setParticipantID(id);
        pdb.updateParticipant(object);
    }
}
