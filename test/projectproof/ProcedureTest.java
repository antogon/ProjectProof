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
public class ProcedureTest {

    private Procedure myTest;
    
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
        myTest = new Procedure("Procedure ReplaceTop(a, b)",
                "requires GreaterThan(Length(a), Zero())",
                "ensures  Equals(a, Concatenate(MakeString(b)," +
                " All_But_First(a)))"
                );
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addInstruction method, of class Procedure.
     */
    @Test
    public void testAddInstruction() {
        System.out.println("addInstruction");
        String ins = "Pop(a, c)";
        myTest.addInstruction(ins);
        ArrayList<Expression> expResult = new ArrayList<Expression>();
        expResult.add(new Expression("Pop(a, c)"));
        ArrayList<Expression> result = myTest.getInstructions();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInstructions method, of class Procedure.
     */
    @Test
    public void testGetInstructions() {
        System.out.println("getInstructions");
        String ins = "Pop(a, c)";
        myTest.addInstruction(ins);
        ins = "Push(a, b)";
        myTest.addInstruction(ins);
        ArrayList<Expression> expResult = new ArrayList<Expression>();
        expResult.add(new Expression("Pop(a, c)"));
        expResult.add(new Expression("Push(a, b)"));
        ArrayList<Expression> result = myTest.getInstructions();
        assertEquals(expResult, result);
    }
}
