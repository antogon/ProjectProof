/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectproof;

/**
 * <p>Represents one state of the procedure to be stored in the ProofTable.
 * Each state has a header, enumerating the state.
 * Each state has an Asumming clause and a Confirms clause, represented as
 * expressions.</p>
 */
public class ProofTableState {
    String myStateHeader;
    Expression myAssuming;
    Expression myConfirms;

    /**
     * <p>Creates a new instance of ProofTableState containing the passed in
     * header, the assumption Expression, and the confirms Expression.</p>
     * @param header a String representing the header formatting
     * @param assuming an Expression representing assumptions of this state
     * @param confirms an Expression representing confirmations of this state
     */
    public ProofTableState(String header,
            Expression assuming,
            Expression confirms){
        myStateHeader = header;
        myAssuming = assuming;
        myConfirms = confirms;
    }

    /**
     * <p>Returns a String representation of this ProofTableState with specific
     *  formatting recognized by the ProofTable toString method.</p>
     * @return a String representing this ProofTableState
     * @see ProofTable
     */
    @Override
    public String toString()
    {
        return myStateHeader + "Assuming:\n" +
                myAssuming.toString() +
                "\n\nConfirm:\n" + myConfirms.toString() +
                "\n\n";
    }
}
