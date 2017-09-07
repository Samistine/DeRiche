/*
 * NoteDBConnectTest
 * Note: this test is not complete yet as of april 11th 2016
 * Actually we have not started this test yet!
 * Code Monkeys  Triston, Bonnie, Bunmi, Natacha, Kristen and Taylor
 * CIST 2931 DeRiche Group
 */
package Data_Access;

import Business.NoteLogic;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Triston_Gregoire
 */
public class NoteDBConnectTest {
    
    /**
     *
     */
    public NoteDBConnectTest() {
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
     * Test of insertNote method, of class NoteDBConnect.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertNote() throws Exception {
        System.out.println("insertNote");
        NoteLogic object = new NoteLogic();
                object.setComment("Comment");
                object.setParticipantID(5);
                object.setUserID(73);
                object.setText("Text");
        NoteDBConnect instance = new NoteDBConnect();
        instance.submitNote(object);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of updateNote method, of class NoteDBConnect.
     * @throws java.lang.Exception
     */
                //Rupa CHANGED IT ON 9/2/2016 
//    @Test
//    public void testUpdateNote() throws Exception {
//        System.out.println("updateNote");
//        NoteLogic object = null;
//        NoteDBConnect instance = new NoteDBConnect();
//        instance.updateNote(object);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

    /**
     * Test of selectNote method, of class NoteDBConnect.
     * @throws java.lang.Exception
     */
    @Test
    public void testSelectNote() throws Exception {
        System.out.println("selectNote");
        NoteLogic note = null;
        NoteDBConnect instance = new NoteDBConnect();
        NoteLogic expResult = null;
        NoteLogic result = instance.selectNote(note);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of selectByParticipant method, of class NoteDBConnect.
     * @throws java.lang.Exception
     */
    @Test
    public void testSelectByParticipant() throws Exception {
        System.out.println("selectByParticipant");
        int partID = 0;
        NoteDBConnect instance = new NoteDBConnect();
        List<NoteLogic> expResult = null;
        List<NoteLogic> result = instance.selectByParticipant(partID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of selectByUser method, of class NoteDBConnect.
     * @throws java.lang.Exception
     */
    @Test
    public void testSelectByUser() throws Exception {
        System.out.println("selectByUser");
        int userID = 0;
        NoteDBConnect instance = new NoteDBConnect();
        List<NoteLogic> expResult = null;
        List<NoteLogic> result = instance.selectByUser(userID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of selectForReview method, of class NoteDBConnect.
     * @throws java.lang.Exception
     */
    @Test
    public void testSelectForReview() throws Exception {
        System.out.println("selectForReview");
        NoteDBConnect instance = new NoteDBConnect();
        List<NoteLogic> expResult = null;
        List<NoteLogic> result = instance.selectForReview();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of deleteNote method, of class NoteDBConnect.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteNote() throws Exception {
        System.out.println("deleteNote");
        int id = 0;
        NoteDBConnect instance = new NoteDBConnect();
        instance.deleteNote(id);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
