/*
 * UserLogicTest 
 * Code Monkeys Triston, Bonnie, Bunmi, Natacha, Kristen and Taylor
 * test code redone on March 16
 * CIST 2931 DeRiche
 */
package Business;

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
public class UserLogicTest {
    
    /**
     *
     */
    public UserLogicTest() {
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
     * Test of getUsername method, of class UserLogic.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        UserLogic instance = new UserLogic();
        UserLogic ul = new UserLogic();
        String expResult = "";
        instance.setUsername(expResult);
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class UserLogic.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        UserLogic instance = new UserLogic();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getOldUserName method, of class UserLogic.
     */
    @Test
    public void testGetOldUserName() {
        System.out.println("getOldUserName");
        UserLogic instance = new UserLogic();
        String expResult = "Lucy";
        instance.setOldUserName("Lucy");
        String result = instance.getOldUserName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setOldUserName method, of class UserLogic.
     */
    @Test
    public void testSetOldUserName() {
        System.out.println("setOldUserName");
        String oldUserName = "";
        UserLogic instance = new UserLogic();
        instance.setOldUserName(oldUserName);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class UserLogic.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        UserLogic instance = new UserLogic();
        instance.setPassword("");
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class UserLogic.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        UserLogic instance = new UserLogic();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of getOldPassowrd method, of class UserLogic.
     */
    @Test
    public void testGetOldPassowrd() {
        System.out.println("getOldPassowrd");
        String password="";
        UserLogic instance = new UserLogic();
        instance.setOldPassowrd("");
        String expResult = "";
        String result = instance.getOldPassowrd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of setOldPassowrd method, of class UserLogic.
     */
    @Test
    public void testSetOldPassowrd() {
        System.out.println("setOldPassowrd");
        String oldPassowrd = "";
        UserLogic instance = new UserLogic();
        instance.setOldPassowrd(oldPassowrd);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class UserLogic.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        UserLogic instance = new UserLogic();
        instance.setFirstName("Lucy");
        String expResult = "Lucy";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstName method, of class UserLogic.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "";
        UserLogic instance = new UserLogic();
        instance.setFirstName(firstName);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class UserLogic.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        UserLogic instance = new UserLogic();
        String expResult = ("");
        instance.setLastName("");
        String result = instance.getLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of setLastName method, of class UserLogic.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "";
        UserLogic instance = new UserLogic();
        instance.setLastName(lastName);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getClearance method, of class UserLogic.
     */
    @Test
    public void testGetClearance() {
        System.out.println("getClearance");
        UserLogic instance = new UserLogic();
        int expResult = -1;
        instance.setClearance(-1);
        int result = instance.getClearance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      // fail("The test case is a prototype.");
    }

    /**
     * Test of setClearance method, of class UserLogic.
     */
    @Test
    public void testSetClearance() {
        System.out.println("setClearance");
        int clearance = 0;
        UserLogic instance = new UserLogic();
        instance.setClearance(clearance);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of isValid method, of class UserLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testIsValid() throws Exception {
        System.out.println("isValid");
        String username = "Lucy";
        String password = "pass";
        UserLogic instance = new UserLogic();
       // boolean expResult = false;
        boolean expResult = true;
        boolean result = instance.isValid(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of select method, of class UserLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testSelect() throws Exception {
        System.out.println("select");
        Object username = null;
        UserLogic instance = new UserLogic();
        instance.select(username);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of insertUser method, of class UserLogic.
     * after you run the test unless you want to add another user 
     * comment this part out or take out the names and leave the"" blank
     * @throws java.lang.Exception
     */
//    @Test
//    public void testInsertUser() throws Exception {
//        System.out.println("insertUser");
//        String username = "Lucy";     //input a name you want to use as a userName
//        String password = "pass";     //input a password
//        String firstName = "Lucy";    //input a firstname
//        String lastName = "Ricardo";  //input a last name
//        Object clearance = "1";       //input a clearance number
//        UserLogic instance = new UserLogic();
//        instance.insertUser(username, password, firstName, lastName, clearance);
//        // TODO review the generated test code and remove the default call to fail.
//       // fail("The test case is a prototype.");
//    }

    /**
     * Test of updateUser method, of class UserLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateUser() throws Exception {
        System.out.println("updateUser");
        String username = "";
        String password = "";
        String firstName = "";
        String lastName = "";
        Object clearance = "";
        Object oldUsername = "";
        
        UserLogic instance = new UserLogic();
        instance.updateUser();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of clearanceCheck method, of class UserLogic.
     */
    @Test
    public void testClearanceCheck_String() {
        System.out.println("clearanceCheck");
        String clearance = "Admin";
        UserLogic instance = new UserLogic();
        int expResult = 0;
        int result = instance.clearanceCheck(clearance);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of clearanceCheck method, of class UserLogic.
     */
    @Test
    public void testClearanceCheck_int() {
        System.out.println("clearanceCheck");
        int clearance = 0;
        UserLogic instance = new UserLogic();
        String expResult = "Admin";
        String result = instance.clearanceCheck(clearance);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
