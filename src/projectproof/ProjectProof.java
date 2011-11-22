/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;


import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>Main driver for {@code ProjectProof}. Takes input from a file specified at
 * runtime from the file chooser. The input file may include {@link
 * Definition}s, {@link Operation}s, and {@link Procedure}s in the form
 * specified in their respective classes. Requires and ensures clauses from the
 * input file must span only one line.</p>
 *
 */
public class ProjectProof {

    /**
     * <p>Opens the file specified and adds it to the {@code Environment}.
     * Prints a proof table of each <i>procedure</i> found in the
	 * {@code Environment}.</p>
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
                System.err.println(ex.getMessage());
                break;
            }
        }
    }
}
