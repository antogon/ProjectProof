/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectproof;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author amalvag
 */
public class ProofTableStateTest {

    public ProofTableStateTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class ProofTableState.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ProofTableState instance = new ProofTableState("State: 0\n========\n",
                new Expression("Equals(y, Concatenate(MakeString(#x), #y))"),
                new Expression("And(Equals(x,"
                    + " All_But_First(#x)),"
                    + " Equals(y, First(#x)))")
                );
        String expResult = "State: 0\n" +
                "========\n" +
                "Assuming:\n" +
                "Equals(y, Concatenate(MakeString(#x), #y))\n\n" +
                "Confirm:\n" +
                "And(Equals(x, All_But_First(#x)), Equals(y, First(#x)))\n\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}