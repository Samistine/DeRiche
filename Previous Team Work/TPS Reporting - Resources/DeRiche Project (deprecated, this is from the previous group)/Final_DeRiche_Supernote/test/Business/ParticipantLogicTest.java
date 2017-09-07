/*
 * ParticipantLogicTest
 * Code Monkeys Triston, Bonnie, Bunmi, Natacha, Kristen and Taylor
 * CIST 2931 DeRiche
 * March 23 2016 redid this one on March 28th due to DB changing
 */
package Business;

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
public class ParticipantLogicTest {
    
    /**
     *
     */
    public ParticipantLogicTest() {
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
     * Test of getParticipantID method, of class ParticipantLogic.
     */
//    @Test
//    public void testGetParticipantID() {
//        System.out.println("getParticipantID");
//        ParticipantLogic instance = new ParticipantLogic();
//        int expResult = 0;
//        int result = instance.getParticipantID();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setParticipantID method, of class ParticipantLogic.
//     */
//    @Test
//    public void testSetParticipantID() {
//        System.out.println("setParticipantID");
//        int participantID = 0;
//        ParticipantLogic instance = new ParticipantLogic();
//        instance.setParticipantID(participantID);
//        // TODO review the generated test code and remove the default call to fail.
//       // fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getParticipantFirstName method, of class ParticipantLogic.
//     */
//    @Test
//    public void testGetParticipantFirstName() {
//        System.out.println("getParticipantFirstName");
//        ParticipantLogic instance = new ParticipantLogic();
//        String expResult = "";
//        String result = instance.getParticipantFirstName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setParticipantFirstName method, of class ParticipantLogic.
//     */
//    @Test
//    public void testSetParticipantFirstName() {
//        System.out.println("setParticipantFirstName");
//        String participantFirstName = "";
//        ParticipantLogic instance = new ParticipantLogic();
//        instance.setParticipantFirstName(participantFirstName);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getParticipantLastName method, of class ParticipantLogic.
//     */
//    @Test
//    public void testGetParticipantLastName() {
//        System.out.println("getParticipantLastName");
//        ParticipantLogic instance = new ParticipantLogic();
//        String expResult = "";
//        String result = instance.getParticipantLastName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setParticipantLastName method, of class ParticipantLogic.
//     */
//    @Test
//    public void testSetParticipantLastName() {
//        System.out.println("setParticipantLastName");
//        String participantLastName = "";
//        ParticipantLogic instance = new ParticipantLogic();
//        instance.setParticipantLastName(participantLastName);
//        // TODO review the generated test code and remove the default call to fail.
//       // fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getInsurance method, of class ParticipantLogic.
//     */
//    @Test
//    public void testGetInsurance() {
//        System.out.println("getInsurance");
//        ParticipantLogic instance = new ParticipantLogic();
//        String expResult = "";
//        String result = instance.getInsurance();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setInsurance method, of class ParticipantLogic.
//     */
//    @Test
//    public void testSetInsurance() {
//        System.out.println("setInsurance");
//        String insurance = "";
//        ParticipantLogic instance = new ParticipantLogic();
//        instance.setInsurance(insurance);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMedicaidNumber method, of class ParticipantLogic.
//     */
//    @Test
//    public void testGetMedicaidNumber() {
//        System.out.println("getMedicaidNumber");
//        ParticipantLogic instance = new ParticipantLogic();
//        String expResult = "";
//        String result = instance.getMedicaidNumber();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//       // fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMedicaidNumber method, of class ParticipantLogic.
//     */
//    @Test
//    public void testSetMedicaidNumber() {
//        System.out.println("setMedicaidNumber");
//        String medicaidNumber = "";
//        ParticipantLogic instance = new ParticipantLogic();
//        instance.setMedicaidNumber(medicaidNumber);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

    /**
     * Test of addParticipant method, of class ParticipantLogic.
     */
//    @Test
//    public void testAddParticipant() throws Exception {
//        System.out.println("addParticipant");
//        ParticipantLogic instance = new ParticipantLogic();
//        instance.setParticipantFirstName("Bonnie");
//        instance.setParticipantLastName("Falk");
//        instance.setInsurance("Self-pay");
//        instance.setMedicaidNumber(null);
//        //instance.setParticipantID(7);
//        instance.addParticipant();
//        
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

    /**
     * Test of populate method, of class ParticipantLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testPopulate() throws Exception {
        System.out.println("populate");
        
        int expResult = 1;
        int actualResult;
        ArrayList<ParticipantLogic> result = ParticipantLogic.populate();
        actualResult = result.get(0).getParticipantID();
        System.out.println(result.get(0).getParticipantID());
        assertEquals(expResult, actualResult);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of select method, of class ParticipantLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testSelect() throws Exception {
        System.out.println("select");
        int num = 1 ;
        ParticipantLogic instance = new ParticipantLogic();
        String expResult = "1234567890123" ;
        ParticipantLogic result = instance.select(num);
        String actualResult = result.getMedicaidNumber();
        assertEquals(expResult, actualResult);
        System.out.println(actualResult);
        System.out.println(expResult);

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of updateParticipant method, of class ParticipantLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateParticipant() throws Exception {
        System.out.println("updateParticipant");
        String medicaid = "";
        String firstname = "";
        String lastname = "";
        String insurance = "";
        int ID = 0;
       
        ParticipantLogic instance = new ParticipantLogic();
        
        instance.setMedicaidNumber("1234567890123");
        instance.setParticipantFirstName("Triston");
        instance.setParticipantLastName("Gregoire");
        instance.setInsurance("Medicaid");
        instance.setParticipantID(7);
        instance.updateParticipant("1234567890123", "Triston", "Gregoire", "Medicaid",1);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
