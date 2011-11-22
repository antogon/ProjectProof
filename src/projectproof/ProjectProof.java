/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

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
        String filename = "Project2Input.txt";
        Environment.setFilePath(filename);
        Environment mainEnv = Environment.getInstance();
        for(Procedure p : mainEnv.getProcedures()){
            ProofTable table = new ProofTable(p);
            System.out.println(table);
        }
    }
}
