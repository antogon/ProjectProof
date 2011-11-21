/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

import java.util.ArrayList;

/**
 *
 * @author antoniomalvagomes
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

    public void addInstruction(String ins)
    {
        myInstructions.add(new Expression(ins));
    }

    public ArrayList<Expression> getInstructions()
    {
        ArrayList<Expression> retVal = new ArrayList<Expression>();
        for(Expression a : myInstructions)
        {
            retVal.add(a);
        }
        return retVal;
    }
    
    public ProofTable generateProofTable(){
        return new ProofTable();
    }
}
