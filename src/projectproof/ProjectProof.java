/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

/**
 *
 * @author antoniomalvagomes
 */
public class ProjectProof {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filename = "Project2Input.txt";
        Environment.setFilePath(filename);
        Environment mainEnv = Environment.getInstance();
        System.out.println(mainEnv.getDefinitions());
        System.out.println(mainEnv.getOperations());
        System.out.println(mainEnv.getProcedures());
    }
}
