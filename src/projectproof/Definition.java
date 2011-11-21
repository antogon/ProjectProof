/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author antoniomalvagomes
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
        StringTokenizer shred = new StringTokenizer(def, "(,) ", false);
        String token;
        int wordCount = 0;
        while(shred.hasMoreTokens())
        {
            token = shred.nextToken();
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
    
}
