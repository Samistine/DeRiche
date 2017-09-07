/*
 * ParticipantDBConnectTest
 * Code Monkeys Triston, Bonnie, Bunmi, Natacha, Kristen and Taylor
 *code 1st done on March 22
 * CIST 2931 DeRiche 
 */
package Data_Access;

import Business.ParticipantLogic;
import java.util.ArrayList;
import java.util.Arrays;
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
public class ParticipantDBConnectTest {
    
    /**
     *
     */
    public ParticipantDBConnectTest() {
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
     * Test of insertParticipant method, of class ParticipantDBConnect.
     * the code in this method is used to insert a new participant to the data base
     * we commented the code out after we ran the test so we don't run a duplicate while
     * testing other methods!
     */
//    @Test
//    public void testInsertParticipant() throws Exception {
//        System.out.println("insertParticipant");
//      //enter appropriate information for each of the properties below
//        String participantFirstName = "Debbie ";
//        String participantLastName = "Harry";
//        String participantInsurance ="Self Pay";
//        String participantMedicaidNumber = "";
//        
//        //ParticipantLogic pl = null;
//        ParticipantLogic pl = new ParticipantLogic();
//        ParticipantDBConnect instance = new ParticipantDBConnect();
//        //need to set firstName,LastName, Insurance and MedicaidNumber
//        pl.setParticipantFirstName("Debbie");
//        pl.setParticipantLastName ("Harry");
//        pl.setInsurance ("Self Pay");
//        pl.setMedicaidNumber(participantMedicaidNumber);
//      
//        instance.insertParticipant(pl);   //this one works!
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");      //comment these out or test will fail
//    }

    /**
     * Test of selectAllParticipants method, of class ParticipantDBConnect.
     * @throws java.lang.Exception
     */
    @Test
    public void testSelectAllParticipants() throws Exception {
        System.out.println("selectAllParticipants");
        String participantName ="Nancy";  //FirstName in the DB   
        String acutalResult = "Nancy";      //firstName again in the DB
        String actualExpResult = "Smith";   //lastName for this person in DB
        //ParticipantDBConnect instance = new ParticipantDBConnect();
        String [] expResultArray = new String [] {"Nancy", "Smith"};    //Used FirstName and LastName of a participant
        ArrayList<ParticipantLogic> expResult = null;
        ArrayList<ParticipantLogic> result = ParticipantDBConnect.selectAllParticipants();
        Object[] resultArray =result.toArray();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of updateParticipant method, of class ParticipantDBConnect.
     * to update information in participant table of the database
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateParticipant() throws Exception {
        System.out.println("updateParticipant");
        ParticipantDBConnect obj = new ParticipantDBConnect();
        ParticipantLogic pl = new ParticipantLogic();
        String participantFirstName = "";
        String participantLastName ="";
        String participantInsurance = "";
        String participantMedicaidNumber = null;
        pl.setParticipantFirstName ("Nancy");
        pl.setParticipantLastName("Field");
        pl.setInsurance("Self-Pay");
        pl.setMedicaidNumber(null);
        pl.setParticipantID(5);
        ParticipantLogic object = null;
        ParticipantDBConnect instance = new ParticipantDBConnect();
        instance.updateParticipant(pl);
      
        //instance.updateParticipant(object);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of selectParticipant method, of class ParticipantDBConnect.
     * @throws java.lang.Exception
     */
    @Test
    public void testSelectParticipant() throws Exception {
        System.out.println("selectParticipant");
        //data fields
        String FirstName ="Nancy";
        String LastName ="Smith";
        String Insruance ="";
        String MedicaidNumber="";
        //String participantID="";  //don't need this one
        ParticipantLogic pl = new ParticipantLogic();
       pl.setParticipantID(1);
        
        //ParticipantLogic object = null;
       //set the column attributes
         pl.setParticipantFirstName("Nancy");
         pl.setParticipantLastName("Smith");
         pl.setInsurance("Medicaid");
         pl.setMedicaidNumber("8889994445578");
        ParticipantDBConnect instance = new ParticipantDBConnect();
        ParticipantLogic p = new ParticipantLogic ();
       p.getParticipantFirstName();
       System.out.println(p.getParticipantFirstName());
        
        String expResult = "Smith";
        //instance.selectParticipant(pl);
       // ParticipantLogic expResult = null;
       //ParticipantLogic result =(ParticipantLogic) instance.selectParticipant(pl);
        
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
