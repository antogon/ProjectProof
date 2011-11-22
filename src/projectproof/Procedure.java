/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

import java.util.ArrayList;

/**
 * <p>A procedure is an Operation that has some code with it, stored in
 * <code>myInstructions</code>.</p>
 */
public class Procedure extends Operation {

    private ArrayList<Expression> myInstructions;
    
    /**
     * <p>Creates a new Procedure object from a string definition descriptor, 
     * a string requires descriptor, and a string ensures descriptor.</p>
     * @param def a String defining this Procedure
     * @param req a String defining what this Procedure requires.
     * @param ens a String describing what this Procedure ensures.
     * @see Operation
     */
    public Procedure(String def, String req, String ens)
    {
        super(def, req, ens);
        myInstructions = new ArrayList<Expression>();
    }

    /**
     * <p>Adds an instruction described by the String parameter to this
     * Procedure.  Requires that the instructions be in the order in which
     *   they are intended to be solved.</p>
     * @param ins a String describing an instruction
     */
    public void addInstruction(String ins)
    {
        myInstructions.add(new Expression(ins));
    }

    /**
     * <p>Returns a list of instructions in order of execution that defines
     *   this Procedure.</p>
     * @return an ArrayList of instructions, each represented as an Expression
     */
    public ArrayList<Expression> getInstructions()
    {
        ArrayList<Expression> retVal = new ArrayList<Expression>();
        for(Expression a : myInstructions)
        {
            retVal.add(a);
        }
        return retVal;
    }

    /**
     * <p>Converts this Procedure object into a string identical to the one
     * used to create it.</p>
     * @return a String representation of this Procedure
     */
    @Override
    public String toString()
    {
        String retVal = "Procedure " + super.getName() + "(";
        int commaCounter = 0;
        for(String a : super.getArgs())
        {
            retVal += a;
            if(commaCounter++ != super.getArgs().size()-1)
            {
                retVal += ", ";
            }
        }
        return retVal + ")";
    }

    /**
     * Tests for equality with another {@code Object}. For two
     * {@code Procedure}s to be equal, their names must be identical, and they
     * must have the same set of arguments in the same order.
     * @param other the other object
     * @return {@code true} if this Procedure is equal to {@code other}.
     */
    @Override
    @SuppressWarnings({"EqualsWhichDoesntCheckParameterClass"})
    public boolean equals(Object o)
    {
        Procedure comp = (Procedure)o;
        return (this.toString().compareTo(comp.toString())==0);
    }
}
