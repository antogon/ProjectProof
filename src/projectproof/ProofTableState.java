/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectproof;

/**
 *
 * @author jmaggio
 */
public class ProofTableState {
    String myStateHeader;
    String myAssuming;
    String myConfirms;

    public ProofTableState(String header, String assuming, String confirms){
        myStateHeader = header;
        myAssuming = assuming;
        myConfirms = confirms;
    }
}
