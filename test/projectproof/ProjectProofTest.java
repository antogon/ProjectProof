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
public class ProjectProofTest {

    public ProjectProofTest() {
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
     * Test of main method, of class ProjectProof.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = {"Project2Input.txt"};
        ProjectProof.main(args);
    }

}