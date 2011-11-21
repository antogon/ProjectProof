/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

/**
 *
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
    
}
