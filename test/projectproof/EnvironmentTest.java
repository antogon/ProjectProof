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
public class EnvironmentTest {

    public EnvironmentTest() {
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
     * Test of getInstance method, of class Environment.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Environment.setFilePath("Project2Input.txt");
        Environment result = Environment.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of setFilePath method, of class Environment.
     */
    @Test
    public void testSetFilePath() {
        System.out.println("setFilePath");
        String fp = "Project2Input.txt";
        Environment.setFilePath(fp);
        String result = Environment.getInstance().getFilePath();
        assertEquals(fp, result);
    }

    /**
     * Test of getDefinitions method, of class Environment.
     */
    @Test
    public void testGetDefinitions() {
        System.out.println("getDefinitions");
        String fp = "Project2Input.txt";
        Environment.setFilePath(fp);
        Environment instance = Environment.getInstance();
        ArrayList<Definition> expResult = new ArrayList<Definition>();
        expResult.add(new Definition("Definition Zero()"));
        expResult.add(new Definition("Definition MaxDepth()"));
        expResult.add(new Definition("Definition GreaterThan(a, b)"));
        expResult.add(new Definition("Definition LessThan(a, b)"));
        expResult.add(new Definition("Definition Length(a)"));
        expResult.add(new Definition("Definition Equals(a, b)"));
        expResult.add(new Definition("Definition Concatenate(a, b)"));
        expResult.add(new Definition("Definition MakeString(a)"));
        expResult.add(new Definition("Definition First(a)"));
        expResult.add(new Definition("Definition All_But_First(a)"));
        expResult.add(new Definition("Definition And(a, b)"));
        ArrayList<Definition> result = instance.getDefinitions();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOperations method, of class Environment.
     */
    @Test
    public void testGetOperations() {
        System.out.println("getOperations");
        String fp = "Project2Input.txt";
        Environment.setFilePath(fp);
        Environment instance = Environment.getInstance();
        ArrayList<Operation> expResult = new ArrayList<Operation>();
        expResult.add(new Operation("Operation Pop(x, y)",
                "requires GreaterThan(Length(x), Zero())",
                "	ensures  And(Equals(x, All_But_First(#x)), "
                + "Equals(y, First(#x)))"));
        expResult.add(new Operation("Operation Push(x, y)",
                "requires LessThan(Length(x), MaxDepth())",
                "ensures  Equals(y, Concatenate(MakeString(#x), #y))"));
        ArrayList<Operation> result = instance.getOperations();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProcedures method, of class Environment.
     */
    @Test
    public void testGetProcedures() {
        System.out.println("getProcedures");
        String fp = "Project2Input.txt";
        Environment.setFilePath(fp);
        Environment instance = Environment.getInstance();
        ArrayList<Procedure> expResult = new ArrayList<Procedure>();
        expResult.add(new Procedure("Procedure ReplaceTop(a, b)",
                "requires GreaterThan(Length(a), Zero())",
                "ensures  Equals(a,"
                + " Concatenate(MakeString(b),"
                + " All_But_First(a)))"));
        ArrayList<Procedure> result = instance.getProcedures();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFilePath method, of class Environment.
     */
    @Test
    public void testGetFilePath() {
        System.out.println("getFilePath");
        String fp = "Project2Input.txt";
        Environment.setFilePath(fp);
        Environment instance = Environment.getInstance();
        String result = instance.getFilePath();
        assertEquals(fp, result);
    }

    /**
     * Test of searchEnvironmentOps method, of class Environment.
     */
    @Test
    public void testSearchEnvironmentOps() {
        System.out.println("searchEnvironmentOps");
        String fp = "Project2Input.txt";
        Environment.setFilePath(fp);
        Environment instance = Environment.getInstance();
        Expression searchOperation = new Expression(
                "Pop(x, y)");
        Operation result = instance.searchEnvironmentOps(searchOperation);
        assertNotNull(result);
        searchOperation = new Expression(
                "PurplePeople(x, y)");
        result = instance.searchEnvironmentOps(searchOperation);
        assertEquals(true, result==null);
    }

    /**
     * Test of searchEnvironmentProcs method, of class Environment.
     */
    @Test
    public void testSearchEnvironmentProcs() {
        System.out.println("searchEnvironmentProcs");
        String fp = "Project2Input.txt";
        Environment.setFilePath(fp);
        Environment instance = Environment.getInstance();
        Expression searchProcess = new Expression(
                "ReplaceTop(x, y)");
        Operation result = instance.searchEnvironmentProcs(searchProcess);
        assertNotNull(result);
        searchProcess = new Expression(
                "PurplePeople(x, y)");
        result = instance.searchEnvironmentProcs(searchProcess);
        assertEquals(null, result);
    }

    /**
     * Test of searchEnvironmentDefs method, of class Environment.
     */
    @Test
    public void testSearchEnvironmentDefs() {
        System.out.println("searchEnvironmentDefs");
        String fp = "Project2Input.txt";
        Environment.setFilePath(fp);
        Environment instance = Environment.getInstance();
        Expression searchDefinition = new Expression(
                "Zero()");
        Definition result = instance.searchEnvironmentDefs(searchDefinition);
        assertNotNull(result);
        searchDefinition = new Expression(
                "PurplePeople(x, y)");
        result = instance.searchEnvironmentDefs(searchDefinition);
        assertEquals(null, result);
    }
}
