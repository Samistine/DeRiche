/*
 * GoalDBConnectTest
 * Code Monkeys Triston, Bonnie, Bunmi, Natacha, Kristen and Taylor
 *  code for the test was done on March 30th 2016
 * CIST 2931 DeRiche 
 */
package Data_Access;

import Business.GoalLogic;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bonnie
 */
public class GoalDBConnectTest {
    
    /**
     *
     */
    public GoalDBConnectTest() {
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of selectGoals method, of class GoalDBConnect.
     * 
     */
//    @Test
//    public void testSelectGoals() throws Exception {
//        System.out.println("selectGoals");
//     properties for the GoalLogic()
//        int GoalId =4;
//        String Description ="blank";
//        String Objective = "getting dressed";
//        String GuidanceNote ="note";
//        String Frequency = "1";
//        String isWeekly = "0";
//        int participantID = 1;
//        GoalDBConnect instance = new GoalDBConnect();
//        GoalLogic gl = new GoalLogic();
//        gl.setGoalID(GoalId);     //need to set the properties
//        gl.setDescription(Description);
//        gl.setObjective(Objective);
//        gl.setGuidanceNote(GuidanceNote);
//        gl.setFrequency(GoalId);
//        gl.setIsWeekly(GoalId);
//        gl.setParticipantID(participantID);
//        instance.selectGoals(1);
//        ArrayList<Goal> exp = new ArrayList();  //create an arraylist
//        exp.add(gl);    //what you expect to get back
//        ArrayList<Goal> expResult = null;
//        ArrayList<Goal> result = instance.selectGoals(1);   //1 stands for Participant ID
//        assertEquals(exp.get(0).getObjective(), result.get(0).getObjective());
//        
//        // TODO review the generated test code and remove the default call to fail.
//       // fail("The test case is a prototype.");
//    }

    /**
     * Test of insertGoal method, of class GoalDBConnect.
     * 
     */
//    @Test
//    public void testInsertGoal() throws Exception {
//        System.out.println("insertGoal");
//        String Description = "shopping";
//        String Objective = "run errands";
//        String GuidanceNote ="done";
//        int Frequency = 2;
//        int IsWeekly = 1;
//        int ParticipantID = 2;
//        
//        GoalLogic object = null;
//        GoalDBConnect instance = new GoalDBConnect();
//       
//       // GoalDBConnect instance = new GoalDBConnect();
//        GoalLogic gl = new GoalLogic ();
//        gl.setDescription(Description);
//        gl.setObjective(Objective);
//        gl.setGuidanceNote(GuidanceNote);
//        gl.setFrequency(Frequency);
//        gl.setIsWeekly(IsWeekly);
//        gl.setParticipantID(ParticipantID);
//         instance.insertGoal(gl);
//        
//        // TODO review the generated test code and remove the default call to fail.
//       // fail("The test case is a prototype.");
//    }

    /**
     * Test of deleteGoal method, of class GoalDBConnect.
     */
//    @Test
//    public void testDeleteGoal() throws Exception {
//        System.out.println("deleteGoal");
//        GoalLogic object = null;
//        int goalID = 3;
//        GoalLogic gl = new GoalLogic();
//        gl.setGoalID(goalID);
//        
//        GoalDBConnect instance = new GoalDBConnect();
//        instance.deleteGoal(gl);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

    /**
     * Test of updateGoal method, of class GoalDBConnect.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateGoal() throws Exception {
        System.out.println("updateGoal");
        String Description = "Shopping";
        String Objective = "buy food";
        String GuidanceNote = "needs to be complete";
        int Frequency = 1;
        int IsWeekly = 0;
        int ParticipantID = 2;
        int GoalID = 5;
        GoalLogic gl = new GoalLogic();
        gl.setDescription("Shopping");
        gl.setObjective("buy food");
        gl.setGuidanceNote("needs to be complete");
        gl.setFrequency(Frequency);
        gl.setIsWeekly(IsWeekly);
        gl.setParticipantID(ParticipantID);
        gl.setGoalID(GoalID);
        GoalDBConnect object = null;
        GoalDBConnect instance = new GoalDBConnect();
        instance.updateGoal(gl);
        
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

//    /**
//     * Test of main method, of class GoalDBConnect.
//     * the main() is used just for testing
//     */
//    @Test
//    public void testMain() {
//        System.out.println("main");
//        String[] args = null;
//        GoalDBConnect.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
    
}
