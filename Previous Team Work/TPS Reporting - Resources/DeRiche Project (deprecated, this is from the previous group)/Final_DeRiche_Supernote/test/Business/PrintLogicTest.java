/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.Timestamp;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author skipp
 */
public class PrintLogicTest {
    
    public PrintLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setNote method, of class PrintLogic.
     */
    @Test
    public void testSetNote() {
        System.out.println("setNote");
        String note = "";
        PrintLogic instance = new PrintLogic();
        instance.setNote(note);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setParName method, of class PrintLogic.
     */
    @Test
    public void testSetParName() {
        System.out.println("setParName");
        String name = "";
        PrintLogic instance = new PrintLogic();
        instance.setParName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserName method, of class PrintLogic.
     */
    @Test
    public void testSetUserName() {
        System.out.println("setUserName");
        String name = "";
        PrintLogic instance = new PrintLogic();
        instance.setUserName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserTitle method, of class PrintLogic.
     */
    @Test
    public void testSetUserTitle() {
        System.out.println("setUserTitle");
        String title = "";
        PrintLogic instance = new PrintLogic();
        instance.setUserTitle(title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class PrintLogic.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Timestamp date = null;
        PrintLogic instance = new PrintLogic();
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNote method, of class PrintLogic.
     */
    @Test
    public void testGetNote() {
        System.out.println("getNote");
        PrintLogic instance = new PrintLogic();
        String expResult = "";
        String result = instance.getNote();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParName method, of class PrintLogic.
     */
    @Test
    public void testGetParName() {
        System.out.println("getParName");
        PrintLogic instance = new PrintLogic();
        String expResult = "";
        String result = instance.getParName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserName method, of class PrintLogic.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        PrintLogic instance = new PrintLogic();
        String expResult = "";
        String result = instance.getUserName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserTitle method, of class PrintLogic.
     */
    @Test
    public void testGetUserTitle() {
        System.out.println("getUserTitle");
        PrintLogic instance = new PrintLogic();
        String expResult = "";
        String result = instance.getUserTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class PrintLogic.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        PrintLogic instance = new PrintLogic();
        Timestamp expResult = null;
        Timestamp result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class PrintLogic.
     */
    @Test
    public void testPrint() throws Exception {
        System.out.println("print");
        int noteId = 0;
        String location = "";
        PrintLogic instance = new PrintLogic();
        PrintLogic expResult = null;
        PrintLogic result = instance.print(noteId,location);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of display method, of class PrintLogic.
     */
    @Test
    public void testDisplay() {
        System.out.println("display");
        PrintLogic instance = new PrintLogic();
        instance.display();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class PrintLogic.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        PrintLogic.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
