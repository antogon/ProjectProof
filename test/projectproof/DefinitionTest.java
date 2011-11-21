/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

import java.util.ArrayList;
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
public class DefinitionTest {
    
    private Definition myTest;
    private Definition myTest1;
    
    public DefinitionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        myTest = new Definition("Definition Zero()");
        myTest1 = new Definition("Definition GreaterThan(a, b)");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Definition.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Zero";
        String result = myTest.getName();
        assertEquals(expResult, result);
        expResult = "GreaterThan";
        result = myTest1.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getArgs method, of class Definition.
     */
    @Test
    public void testGetArgs() {
        System.out.println("getArgs");
        ArrayList<String> expResult = new ArrayList<String>();
        ArrayList result = myTest.getArgs();
        assertEquals(expResult, result);
        expResult = new ArrayList<String>();
        expResult.add("a");
        expResult.add("b");
        result = myTest1.getArgs();
        assertEquals(expResult, result);
    }
}
