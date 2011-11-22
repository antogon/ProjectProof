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

		try{
			File inputFile = null;
			JFileChooser chooser = new JFileChooser();
					JFrame frame = new JFrame("applicationFrame");
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					chooser.setDialogTitle("Please choose an input file.");
					int returnVal = chooser.showOpenDialog(frame);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					inputFile = chooser.getSelectedFile();
				}
				else
					frame.dispose();
			if(inputFile != null){
				String filename = inputFile.getAbsolutePath();
				Environment.setFile(inputFile);
				Environment mainEnv = Environment.getInstance();
				ArrayList<Procedure> pList = mainEnv.getProcedures();
				for(Procedure p : pList){
					ProofTable table = new ProofTable(p);
					System.out.println(table);
				}
			}
			frame.dispose();
		}
		catch (NullPointerException npe) {
			System.err.println("File error: " + npe.getMessage());
		}
		catch (IllegalArgumentException iae) {
			System.err.println("File error: " + iae.getMessage());
		}

	}
}
