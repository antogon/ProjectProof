/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antoniomalvagomes
 */
public class OperationTest {
    
    private Operation myTest, myTest1;
    
    public OperationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        Environment.setFilePath("Project2Input.txt");
        myTest = new Operation("Operation Pop(x, y)",
                "requires GreaterThan(Length(x), Zero())",
                "ensures  And(Equals(x, All_But_First(#x)),"
                + " Equals(y, First(#x)))"
                );
        myTest1 = new Operation("Operation Push(x, y)",
                "requires LessThan(Length(x), MaxDepth())",
                "ensures  Equals(y, Concatenate(MakeString(#x), #y))"
                );
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRequires method, of class Operation.
     */
    @Test
    public void testGetRequires() {
        System.out.println("getRequires");
        Expression expResult = new Expression("GreaterThan(Length(x), Zero())");
        Expression result = myTest.getRequires();
        assertEquals(expResult, result);
        result = myTest1.getRequires();
        assertEquals(expResult.equals(result), false);
    }

    /**
     * Test of getEnsures method, of class Operation.
     */
    @Test
    public void testGetEnsures() {
        System.out.println("getEnsures");
        Expression expResult = new Expression("And(Equals(x,"
                + " All_But_First(#x)), Equals(y, First(#x)))");
        Expression result = myTest.getEnsures();
        assertEquals(expResult, result);
        result = myTest1.getEnsures();
        assertEquals(expResult==result, false);
    }

    /**
     * Test of toString method, of class Operation.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Operation Pop(x, y)";
        String result = myTest.toString();
        assertEquals(expResult, result);
        expResult = "Operation Push(x, y)";
        result = myTest1.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Operation.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Operation instance = new Operation("Operation Pop(x, y)",
                "requires GreaterThan(Length(x), Zero())",
                "ensures  And(Equals(x, All_But_First(#x)),"
                + " Equals(y, First(#x)))"
                );
        boolean expResult = true;
        boolean result = instance.equals(myTest);
        assertEquals(expResult, result);
    }
}
