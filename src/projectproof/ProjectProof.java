/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main driver for ProjectProof. Takes input from a file specified at run-time.
 * Requires and ensures clauses from input file must span only one line.
 *
 */
public class ProjectProof {

    /**
     * Opens the file specified and adds it to the Environment.
     * Prints a proof table of each procedure found in the Environment.
     */
    public static void main(String[] args) {
        if(args.length!=1)
        {
            System.err.println("Not enough parameters!\n\t" +
                    "usage: java ProjectProof [formal specification file.]");
            System.exit(1);
        }
        String filename = args[0];
        Environment.setFilePath(filename);
        Environment mainEnv = Environment.getInstance();
        ArrayList<Procedure> pList = mainEnv.getProcedures();
        for(Procedure p : pList){
            try {
                ProofTable table = new ProofTable(p);
                System.out.println(table);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                break;
            }
        }
    }
}
