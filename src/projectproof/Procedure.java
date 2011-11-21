/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

/**
 *
 * @author antoniomalvagomes
 */
public class Procedure extends Operation {
    
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
    }
    
    public ProofTable generateProofTable(){
        return new ProofTable();
    }
}
