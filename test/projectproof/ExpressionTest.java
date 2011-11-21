/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
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
public class ExpressionTest {
    
    private Expression myTest, myTest1;
    
    public ExpressionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        myTest = new Expression("Equals(y, Concatenate(MakeString(#x), #y))");
        myTest1 = new Expression("And(Equals(x,"
                + " All_But_First(#x)),"
                + " Equals(y, First(#x)))");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of substitute method, of class Expression.
     */
    @Test
    public void testSubstitute() {
        System.out.println("substitute");
        Map<String, String> map = new HashMap<String, String>();
        map.put("y", "q");
        map.put("#x", "#t");
        map.put("#y", "#z");
        Expression expResult = new Expression("Equals(q, Concatenate(MakeString(#t), #z))");
        System.out.println(myTest); 
        Expression result = myTest.substitute(map);
        System.out.println(expResult);
        System.out.println(result);
        
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of replace method, of class Expression.
     */
    @Test
    public void testReplace() {
        System.out.println("replace");
        Expression find = new Expression("MakeString(#x)");
        Expression repl = new Expression("DeleteString(#z)");
        myTest.replace(find, repl);
        assertEquals("Equals(y, Concatenate(DeleteString(#z), #y))",
                myTest.toString());
    }

    /**
     * Test of getName method, of class Expression.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Equals";
        String result = myTest.getName();
        assertEquals(expResult, result);
        expResult = "And";
        result = myTest1.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getArgs method, of class Expression.
     */
    @Test
    public void testGetArgs() {
        System.out.println("getArgs");
        ArrayList<Expression> expResult = new ArrayList<Expression>();
        expResult.add(new Expression("y"));
        expResult.add(new Expression("Concatenate(MakeString(#x), #y)"));
        ArrayList<Expression> result = myTest.getArgs();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Expression.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Equals(y, Concatenate(MakeString(#x), #y))";
        String result = myTest.toString();
        assertEquals(expResult, result);
        expResult = "And(Equals(x,"
                + " All_But_First(#x)),"
                + " Equals(y, First(#x)))";
        result = myTest1.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Expression.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object rhs = new Expression("Equals(y, Concatenate(MakeString(#x), #y))");
        boolean expResult = true;
        boolean result = myTest.equals(rhs);
        assertEquals(expResult, result);
        expResult = false;
        result = myTest1.equals(rhs);
        assertEquals(expResult, result);
    }
}
