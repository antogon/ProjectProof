/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author antoniomalvagomes
 */
public class Expression {
    
    private String myName;
    private ArrayList<Expression> myArgs;
    private boolean isVariable = true;
    
    /**
     * <p>Creates an Expression from a string descriptor.</p>
     * @param def a String that describes this Expression
     */
    public Expression(String def)
    {
        def = def.replaceAll(" ", "");
        myArgs = new ArrayList<Expression>();
        Stack<String> parens = new Stack<String>();
        StringTokenizer shredder = new StringTokenizer(def,
                "(,)",
                true
                );
        String token;
        String expression = "";
        int wordCount = 0;
        while(shredder.hasMoreTokens())
        {
            token = shredder.nextToken();
            if(wordCount++ == 0)
            {
                myName = token;
                continue;
            }
            if(token.compareTo("(")==0)
            {
                isVariable = false;
                parens.push(token);
            }
            if(token.compareTo(")")==0)
            {
                if(parens.size()<0)
                {
                    System.out.println("Parenthesis mismatch.");
                }
                parens.pop();
            }
            if((token.compareTo(",")==0 && parens.size()==1) || parens.size()==0)
            {
                if(expression.startsWith("("))
                {
                    expression = expression.substring(1);
                }
                if(expression.endsWith(","))
                {
                    expression = expression.substring(0, expression.length()-1);
                }
                myArgs.add(new Expression(expression));
                expression = "";
            }
            else
            {
                expression+=token;
            }
        }
    }
    
    /**
     * <p>Substitutes any Expressions within this Expression that appear as keys
     * in the <code>Map</code> given with their respective keys.  Returns a new
     * Expression and leaves this Expression unchanged.</p>
     * @param map a Map relating searched keys to their replacement values
     * @return a new Expression with the given substitution performed
     */
    public Expression substitute(Map<String, String> map)
    {
        Expression retVal = new Expression(this.toString());
        Set<String> keys = map.keySet();
        for(String a : keys)
        {
            Expression f = new Expression(a);
            Expression r = new Expression(map.get(a));
            retVal.replace(f, r);
        }
        return retVal;
    }
    
    /**
     * <p>A helper function for substitute that completes the request</p>
     * @param find an Expression for which this Expression is searched
     * @param repl an Expression with which the searched value will be replaced
     */
    protected void replace(Expression find, Expression repl)
    {
        if(myName.compareTo(find.getName())==0 && myArgs.equals(find.getArgs()))
        {
            myName = repl.getName();
            myArgs = repl.getArgs();
        }
        for(Expression a : myArgs)
        {
            a.replace(find, repl);
        }
    }
    
    /**
     * <p>Returns the name of this Expression.</p>
     * @return a String representation of the name of this Expression
     */
    public String getName()
    {
        return myName;
    }
    
    /**
     * <p>Returns a list of arguments in Expression form for this Expression</p>
     * @return an ArrayList of arguments for this Expression
     */
    public ArrayList<Expression> getArgs()
    {
        ArrayList<Expression> retVal = new ArrayList<Expression>();
        for(Expression a : myArgs)
        {
            retVal.add(a);
        }
        return retVal;
    }
    
    /**
     * <p>Returns a String representation of this Expression identical to
     *  the String used to construct it.</p>
     * @return a String representation of this Expression.
     */
    @Override
    public String toString()
    {
        String leftParen = (isVariable)?"":"(";
        String rightParen = (isVariable)?"":")";
        String retVal = myName + leftParen;
        int commaCounter = 0;
        for(Expression a : myArgs)
        {
            retVal += a.toString();
            if(commaCounter++ != myArgs.size()-1)
            {
                retVal += ", ";
            }
        }
        return retVal + rightParen;
    }
    
    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object rhs)
    {
        Expression comp = (Expression)rhs;
        return (this.toString().compareTo(comp.toString())==0);
    }
    
}
