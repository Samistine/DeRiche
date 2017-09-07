/*
 * UserDBConnectTest
 * Re-done on March 28 2016
 * Code Monkeys Triston, Bonnie, Bunmi, Natacha, Kristen and Taylor
 * CIST 2931 for the DeRichie Group
 */
package Data_Access;

import Business.UserLogic;
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
 * @author Triston_Gregoire
 */
public class UserDBConnectTest {
    
    /**
     *
     */
    public UserDBConnectTest() {
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
     * Test of login method, of class UserDBConnect.
     * to make sure that we can login correctly
     * @throws java.lang.Exception
     */
    @Test
    public void testLogin() throws Exception {
        
        UserLogic ul = new UserLogic();
        
        
        System.out.println("login");
        String userName = "Admin";      //this was one of the usernNames we used in the DB
        ul.setUsername(userName);
        UserDBConnect instance = new UserDBConnect();
        //String expResult = "bonnief-pass";        //this was our expected result
       // String expResult = "John-Admin";          //another expected result
        UserLogic newObject = instance.login(ul);
        //Object expResult = ul;
        
        
        String result = newObject.getUsername() + newObject.getPassword();
        String expResult = "Adminpassword"; //this is both the userName and password used
        assertEquals(expResult, result);
        System.out.println("Result: " + result);
        System.out.println("Exp : " + expResult);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");       //comment out these or the test's will always fail
    }

    /**
     * Test of populateUser method, of class UserDBConnect.
     * @throws java.lang.Exception
     */
    
    @Test
    public void testPopulateUser() throws Exception {
        System.out.println("populateUser");
        String actualResult = null;
        String actualExpResult = null;
        
        ArrayList<String> expResult = null;
        //input each userName that is in the database to populate the user()
        String [] expResultArray = new String [] {"Admin","TristonG","Lucy","David"};   //this is what is in our current DB for the test to pass
       // String[] expResultArray = new String[]{"Admin", "Lucy" }; //this has to reflect what is in your database for for the test
       // String[] expResultArray = new String[]{"Amy", "mary", "bonnief", "Wendyh", "Davidr", "Sami" };//this was the old one for the test on an old DB
        ArrayList<String> result = UserDBConnect.populateUser();
        Object[] resultArray = result.toArray();
       
        System.out.println(result.toString());
        //assertTrue(Arrays.equals(resultArray, expResultArray));
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of UserSelect method, of class UserDBConnect.
     * a test to select a particular user
     * @throws java.lang.Exception
     */
    @Test
    public void testUserSelect() throws Exception {
        System.out.println("userSelect");
        String name = "Admin";      //this was the userName we used to test selecting a user
        UserLogic ul = new UserLogic();
        ul.setUsername(name);
        UserDBConnect instance = new UserDBConnect();
        String expResult = "Doe";       //this is the last name for the above userName
        UserLogic result = (UserLogic) instance.userSelect(ul);
        assertEquals(expResult, result.getLastName() );
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class UserDBConnect.
     * this test is used to delete a user
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteUser() throws Exception {
        System.out.println("deleteUser");
        String userID = "";     //put and ID of a user to delete inside the ""
        UserDBConnect instance = new UserDBConnect();
        instance.deleteUser(userID);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of insertUser method, of class UserDBConnect.
     * the following code is what was needed to pass the insertTest
     * It is commented out because if it is not commented out
     * every time you run this file it will insert the information again
     */
//    @Test
//    public void testInsertUser() throws Exception {
//        System.out.println("insertUser");
//        String userName = "TristonG";   //input a userName  
//        String pass = "pass";             //input a password I used pass
//        String firstName = "Triston";     //input a first name
//        String lastName = "Gregoire";     //input a last name
//        int clearance = 1;                //input a clearnce level number 
//        UserLogic ul = new UserLogic();
//        ul.setUsername(userName);     //you can leave these blank since the new info is above
//        ul.setPassword(pass);
//        ul.setFirstName(firstName);
//        ul.setLastName(lastName);
//        ul.setClearance(clearance);
//        UserDBConnect instance = new UserDBConnect();
//        instance.insertUser(ul);
//        //instance.insertUser(userName, pass, firstName, lastName, clearance);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

    /**
     * Test of update method, of class UserDBConnect.
     * this test's the update users
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdate() throws Exception {
        UserLogic ul = new UserLogic();
        System.out.println("update");
        String newUsername = "";    //put a username inside of the ""
        String password = "";       //put the password for the user name above
        String firstName = "";      //put first name
        String lastName = "";       //put last name
        int clearance = 0;          //you can change the 0 to another clearance level if you choose
        String oldUsername = "";    //put the oldUserName here!
        UserDBConnect instance = new UserDBConnect();
        instance.update(ul);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }
    
}
