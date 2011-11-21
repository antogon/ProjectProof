/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <p>Represents a definition, consisting of a name and its arguments (if
 * applicable), such as {@code Get_Element_At()},
 * {@code Subsequence()}, or {@code GreaterThan(a, b)}.</p>
 */
public class Definition {

    private String myName;
    private ArrayList<String> myArgs;

    /**
     * <p> Creates a {@code Definition} from a {@code String} beginning with the
	 * word "Definition ", followed by its name and a comma-separated list of
	 * its arguments. For example, a {@code Definition} called "functionName"
	 * with <i>n</i> arguments could be created with the format: <blockquote>
	 * <code>Definition functionName(arg<sub>0</sub>, arg<sub>1</sub>,
	 * arg<sub>2</sub>, ..., arg<sub>n</sub>)</code>,</blockquote></p>
     * @param def A properly-formatted, non-null {@code String} describing the
	 * {@code Definition} object to be created.
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
     * <p>Returns the name of this {@code Definition}</p>
     * @return the name of this {@code Definition} object.
     */
    public String getName()
    {
        return myName;
    }

    /**
     * <p>Returns a new {@code ArrayList} of {@code String}s containing this
	 * {@code Definition}'s arguments.</p>
     * @return A new {@code ArrayList} containing this {@code Definition}'s
	 * arguments.
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
     * <p>Converts this {@code Definition} object into a string identical to the
	 * one used to create it in its constructor. Therefore, a {@code Definition}
	 * named "newDefinition" with <i>n</i> arguments would have the form:
	 * <blockquote><code>Definition newDefinition(arg<sub>1</sub>, arg<sub>2
	 * </sub>, ..., arg<sub>n</sub>)</code></blockquote></p>
     * @return a String representation of this Definition.
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

	/**
	 * Tests for equality with another {@code Object}. For two
	 * {@code Definition}s to be equal, their names must be identical, and they
	 * must have the same set of arguments in the same order.
	 * @param other the other object
	 * @return {@code true} if this Definition is equal to {@code other}.
	 */
    @Override
    @SuppressWarnings({"EqualsWhichDoesntCheckParameterClass"})
    public boolean equals(Object other){
        Definition comp = (Definition)other;
        return (this.toString().compareTo(comp.toString())==0);
    }
}
