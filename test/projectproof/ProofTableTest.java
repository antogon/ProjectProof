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
public class ProofTableTest {
    Environment myE;

    public ProofTableTest() {
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
        myE = Environment.getInstance();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class ProofTable.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Proof Table for [ReplaceTop]\n\n" +
                "State 0\n" +
                "========\n" +
                "Assuming:\n" +
                "GreaterThan(Length(a0), Zero())\n\n" +
                "Confirm:\n" +
                "GreaterThan(Length(a0), Zero())\n\n" +
                "State 1\n" +
                "========\n" +
                "Assuming:\n" +
                "And(Equals(a1, All_But_First(a0)), Equals(c1, First(a0)))\n" +
                "\nConfirm:\n" +
                "LessThan(Length(a1), MaxDepth())\n\n" +
                "State 2\n" +
                "========\n" +
                "Assuming:\n" +
                "Equals(b2, Concatenate(MakeString(a1), b1))\n\n" +
                "Confirm:\n" +
                "Equals(a2, Concatenate(MakeString(b2), All_But_First(a2)))";
        ProofTable myTest = null;
        try {
            myTest = new ProofTable(myE.getProcedures().get(0));
        } catch (Exception ex) {
            fail("ProofTable failed to check for bad input.");
        }
        String result = myTest.toString();
        assertEquals(expResult, result);
    }

}