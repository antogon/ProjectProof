/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

/**
 * <p>Operation is a definition object that also has a requires and ensures
 * clause. These are stored as expressions.</p>
 * @author antoniomalvagomes
 */
public class Operation extends Definition {
    
    private Expression myRequires, myEnsures;
    
    /**
     * <p>Creates a new Operation object from a string definition descriptor, 
     * a string requires descriptor, and a string ensures descriptor.</p>
     * @param def a String defining this Operation
     * @param req a String defining what this Operation requires.
     * @param ens a String describing what this Operation ensures.
     * @see Definition
     */
    public Operation(String def, String req, String ens)
    {
        super(def);
        myRequires = new Expression(req.replaceFirst("requires ", ""));
        myEnsures = new Expression(ens.replaceFirst("ensures ", ""));
    }
    
    /**
     * <p>Returns what this Operation requires in the form of an Expression.</p>
     * @return an Expression representing what this Operation requires.
     */
    public Expression getRequires()
    {
        return myRequires;
    }
    
    /**
     * <p>Returns what this Operation ensures in the form of an Expression.</p>
     * @return an Expression representing what this Operation ensures.
     */
    public Expression getEnsures()
    {
        return myEnsures;
    }

    /**
     * <p>Converts this Operation object into a string identical to the one
     * used to create it.</p>
     * @return a String representation of this Operation
     */
    @Override
    public String toString()
    {
        String retVal = "Operation " + super.getName() + "(";
        int commaCounter = 0;
        for(String a : super.getArgs())
        {
            retVal += (a==null)?"":a;
            if(commaCounter++ != super.getArgs().size()-1)
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
        Operation comp = (Operation)o;
        return (this.toString().compareTo(comp.toString())==0);
    }
    
}
