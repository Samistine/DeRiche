/*
 * CIST2931-Advanced Systems Project
 * Started: Spring 2016
 * Team 1 AKA Code Monkeys:
 *      Team Lead AKA who to blame for things: Triston Gregoire
 *      Team: Bonnie Falk, Bunmi Bamiro, Kristen Dawes, Natacha Mosala, Taylor Smith
*/
package Business;

import Data_Access.GoalDBConnect;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Validates Goal objects to be passed to and from the GoalDBConnect class
 * @author Triston_Gregoire
 */



//OK so the way the dropdowns are populated in the gui are often in the form of idnumber:name with the colon being the delimiter (for example: 7:Triston Gregoire)
//The objects in the dropdown lists are returned in that format as well.
//So I'm splitting the string by its delimiter or ':' 
//This is done via the String.split() method shown below
//7:TristonGregoire becomes array[0] = 7, array[1] = Triston Gregoire
//The next line will be an example
//String[] array = stringToBeSplit.split(":")[0]));
//You will see this done quite a bit throughout the source code. 
//Feel free to change the way I did this but realize that would probably involve changing the way things are formatted in the GUI's dropdowns.

/**
 * Validates GoalLogic objects to be passed to and from the GoalDBConnect class
 * @author Triston_Gregoire
 */

public class GoalLogic {
    private int goalID = -1;
    private int frequency = -1;
    private int isWeekly = -1;
    private int participantID = -1;
    private String description = null;
    private String objective = null;
    private String guidanceNote = null;
    GoalDBConnect gdb = new GoalDBConnect();

    /**
     * @return the goalID
     */
    public int getGoalID() {
        return goalID;
    }

    /**
     * @param goalID the goalID to set
     */
    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * @return the isWeekly
     */
    public int getIsWeekly() {
        return isWeekly;
    }

    /**
     * @param isWeekly the isWeekly to set
     */
    public void setIsWeekly(int isWeekly) {
        this.isWeekly = isWeekly;
    }

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the objective
     */
    public String getObjective() {
        return objective;
    }

    /**
     * @param objective the objective to set
     */
    public void setObjective(String objective) {
        this.objective = objective;
    }

    /**
     * @return the guidanceNote
     */
    public String getGuidanceNote() {
        return guidanceNote;
    }

    /**
     * @param guidanceNote the guidanceNote to set
     */
    public void setGuidanceNote(String guidanceNote) {
        this.guidanceNote = guidanceNote;
    }
    
    /**
     * Accepts input from GUI and select corresponding goal for the selected participant. 
     * @param str
     * @return returns ArrayList filled with goal objects
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<GoalLogic> selectGoalsOrSomeShit(Object str) throws ClassNotFoundException, SQLException{
        ArrayList<GoalLogic> goalList = new ArrayList();
        if(str != null && !"Select A Participant".equals(str)){     // If str does not equal null and if str does not equal 'Select A Participant'
            String placeholder = (String) str;                      // type cast to String
            int id = Integer.parseInt(placeholder.split(":")[0]);   //places the number before the colon into the variable 
            if(gdb.selectGoals(id) != null){                        //If the returned arraylist is not null. removing this check can give nullpointer exceptions
                goalList = gdb.selectGoals(id);
            }
            else{
                this.setDescription("empty");
                goalList.add(this);
            }
        }
        return goalList;
    }
    
    /**
     * Adds a new goal to the database for the selected Participant
     * @param frequency the number of times a goal is performed within a given interval of time
     * @param interval time period over which a goal is performed. should either be 'week' or 'month'
     * @param goal activity to be completed
     * @param objective reason the goal is being performed
     * @param guidance note for the caretaker to keep in mind
     * @param id selected participant's ID
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void add(Object frequency, Object interval, String goal, String objective,
            String guidance, Object id) throws ClassNotFoundException, SQLException{
        String participantInfo = (String)id;
        this.setFrequency(Integer.parseInt((String)frequency));    
        switch((String)interval){
            case "Week":
                this.setIsWeekly(1);
                break;
            case "Month":
                this.setIsWeekly(0);
                break;
        }
        this.setDescription(goal);
        this.setObjective(objective);
        this.setGuidanceNote(guidance);
        this.setParticipantID(Integer.parseInt(participantInfo.split(":")[0]));
        gdb.insertGoal(this);
    }
    
    /**
     * Receives input from UI and prepares it to be entered into the database.
     * @param frequency the number of times a goal is performed within a given interval of time
     * @param interval time period over which a goal is performed. should either be 'week' or 'month'
     * @param goal activity to be completed
     * @param objective reason the goal is being performed
     * @param guidance note for the caretaker to keep in mind
     * @param goalID selected participant's ID
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void update(Object frequency, Object interval, String goal, String objective,
            String guidance, int goalID) throws SQLException, ClassNotFoundException{
        this.setFrequency(Integer.parseInt((String)frequency));
        switch((String)interval){
            case "Week":
                this.setIsWeekly(1);
                break;
            case "Month":
                this.setIsWeekly(0);
                break;
        }        
        this.setDescription(goal);
        this.setObjective(objective);
        this.setGuidanceNote(guidance);
        this.setGoalID(goalID);
        gdb.updateGoal(this);
    }
    
    /**
     * Deletes goal from the database
     * @param activeGoal
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void delete(GoalLogic activeGoal) throws SQLException, ClassNotFoundException{
        gdb.deleteGoal(activeGoal);
        
    }
    /**
     * Used in the AdminForm.java to remove a goal from the database by the goalID
     * @param goalID
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void removeGoal(int goalID)throws SQLException, ClassNotFoundException{
        System.out.println(goalID);
        GoalDBConnect gdb = new GoalDBConnect();
        gdb.removeGoal(goalID);
    }
}
