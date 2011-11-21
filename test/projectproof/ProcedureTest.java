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
 * @author antoniomalvagomes
 */
public class ProcedureTest {
    
    public ProcedureTest() {
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
     * Test of generateProofTable method, of class Procedure.
     */
    @Test
    public void testGenerateProofTable() {
        System.out.println("generateProofTable");
        Environment e = null;
        Procedure instance = null;
        ProofTable expResult = null;
        ProofTable result = instance.generateProofTable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
