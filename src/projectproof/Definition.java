/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <p>Represents a definition object, which is a name and a list of arguments.
 * </p>
 */
public class Definition {
    
    private String myName;
    private ArrayList<String> myArgs;
    
    /**
     * <p> Creates a <code>Definition</code> from a string of the format:<br/>
     * Definition functionName(arg0, arg1, arg2, ...)</p>
     * @param def A string describing the Definition object to be created.
     */
    public Definition(String def)
    {
        myArgs = new ArrayList<String>();
        StringTokenizer shredder = new StringTokenizer(def, "(,) ", false);
        String token;
        int wordCount = 0;
        while(shredder.hasMoreTokens())
        {
            token = shredder.nextToken();
            switch(wordCount++)
            {
                case 0:
                    break;
                case 1:
                    myName = token;
                    break;
                default:
                    myArgs.add(token);
            }
        }
    }
    
    /**
     * <p>Returns the name of this Definition</p>
     * @return the name of this Definition
     */
    public String getName()
    {
        return myName;
    }
    
    /**
     * <p>Creates a copy of this Definition's list of arguments and returns it.
     * </p>
     * @return an <code>ArrayList</code> of arguments for this Definition
     */
    public ArrayList<String> getArgs()
    {
        ArrayList<String> retVal = new ArrayList<String>();
        for(String a : myArgs)
        {
            retVal.add(a);
        }
        return retVal;
    }
    
    /**
     * <p>Converts this Definition object into a string identical to the one
     * used to create it.</p>
     * @return a String representation of this Definition
     */
    @Override
    public String toString()
    {
        String retVal = "Definition " + myName + "(";
        int commaCounter = 0;
        for(String a : myArgs)
        {
            retVal += a;
            if(commaCounter++ != myArgs.size()-1)
            {
                retVal += ", ";
            }
        }
        return retVal + ")";
    }

    @Override
    @SuppressWarnings({"EqualsWhichDoesntCheckParameterClass"})
    public boolean equals(Object o)
    {
        Definition comp = (Definition)o;
        return (this.toString().compareTo(comp.toString())==0);
    }
    
}
