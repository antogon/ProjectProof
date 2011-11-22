/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

import java.util.ArrayList;

/**
 * <p>Represents a <i>procedure</i>, which is a type of {@link Operation},
 * formally specified with <i> definitions</i> and implemented with previously-
 * defined {@code Operation}s, excluding other {@code Procedure}s.</p>
 * <p> A {@code Procedure} object contains a list of its instructions, in the
 * form of {@link Expression}s, from which the proof table is to be generated.
 * </p>
 */
public class Procedure extends Operation {

    private ArrayList<Expression> myInstructions;

    /**
     * <p>Creates a new Procedure object from a string definition descriptor,
     * a string requires descriptor, and a string ensures descriptor.</p>
     * @param procedureDefinition a String defining this Procedure
     * @param requires a String defining what this Procedure requires.
     * @param ensures a String describing what this Procedure ensures.
     * @see Operation
     */
    public Procedure(String procedureDefinition, String requires, String ensures)
    {
        super(procedureDefinition, requires, ensures);
        myInstructions = new ArrayList<Expression>();
    }

    /**
     * <p>Adds an instruction described by the sole parameter to this
     * {@code Procedure}.  Instructions be added in the order in which
     * they are intended to be solved.</p>
     * @param instruction a String describing an instruction
     */
    public void addInstruction(String instruction){
        myInstructions.add(new Expression(instruction));
    }

    /**
     * <p>Returns an ArrayList of instructions in order of execution that
     * defines this Procedure.</p>
     * @return an ArrayList of instructions, each represented as an Expression
     */
    public ArrayList<Expression> getInstructions(){
        ArrayList<Expression> returnList = new ArrayList<Expression>();
        for(Expression a : myInstructions)
        {
            returnList.add(a);
        }
        return returnList;
    }

    /**
     * <p>Converts this Procedure object into a {@code String} identical to the
     * one used to create it in the input file. That is, for a {@code Procedure}
	 * {@code exampleProcedure} with <i>n</i> arguments:<blockquote>
	 * <code>Procedure exampleProcedure(arg<sub>1</sub>, arg<sub>2</sub>, ...,
	 * arg<sub>n</sub>)</code></blockquote></p>
     * @return a String representation of this Procedure
     */
    @Override
    public String toString()
    {
        String returnString = "Procedure " + super.getName() + "(";
        int commaCounter = 0;
        for(String a : super.getArgs())
        {
            returnString += a;
            if(commaCounter++ != super.getArgs().size()-1)
            {
                returnString += ", ";
            }
        }
        return returnString + ")";
    }

	/**
	 * <p>Tests this {@code Procedure} for equality with another {@code
	 * Procedure} object. For two {@code Procedure}s to be equal, they must
	 * have identical names and identical arguments in the same order.</p>
	 * @param other the {@code Procedure} to be checked against.
 	 * @return {@code true} if the {@code Procedure}s are equal; {@code false}
	 * otherwise.
	 */
    @Override
    @SuppressWarnings({"EqualsWhichDoesntCheckParameterClass"})
    public boolean equals(Object other)
    {
        Procedure comp = (Procedure)other;
        return (this.toString().compareTo(comp.toString())==0);
    }
}
