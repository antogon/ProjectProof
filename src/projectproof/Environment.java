/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>Stores all definitions, operations, and procedures from the input file
 * as their respective {@link Definition}, {@link Operation}, and
 * {@link Procedure} objects.  Because there should only exist one
 * {@code Environment}, this class is a {@code Singleton}.</p>
 */
public class Environment {

    private static Environment myInstance;
    private static String myFilePath;
    public ArrayList<Definition> myDefinitions;
    public ArrayList<Operation> myOperations;
    public ArrayList<Procedure> myProcedures;

    /**
     * <p>A private constructor for this {@code Singleton} class.
     * Sets the internal member variable to an instance of this class.
     * Requires that the {@code setFilePath} function be called beforehand in
	 * order to set the file to be read in.</p>
     * @param filePath the path to the formal specification file to be processed
     */
    private Environment(String filePath)
    {
        myFilePath = filePath;
        myDefinitions = new ArrayList<Definition>();
        myOperations = new ArrayList<Operation>();
        myProcedures = new ArrayList<Procedure>();
        File filePointer = new File(filePath);
        try
        {
            Scanner fileScanner = new Scanner(filePointer);
            String inputLine;
            while(fileScanner.hasNextLine())
            {
                inputLine = fileScanner.nextLine();
                if(inputLine.startsWith("Definition"))
                {
                    myDefinitions.add(new Definition(inputLine));
                }
                else if(inputLine.startsWith("Operation"))
                {
                    myOperations.add(
                            new Operation(inputLine,
                                    fileScanner.nextLine(),
                                    fileScanner.nextLine()
                                    )
                            );
                }
                else if(inputLine.startsWith("Procedure"))
                {
                    Procedure p = new Procedure(inputLine,
                                    fileScanner.nextLine(),
                                    fileScanner.nextLine()
                                    );
                    inputLine = fileScanner.nextLine();
                    if(inputLine.compareTo("begin")==0)
                    {
                        while(inputLine.compareTo("end")!=0)
                        {
                            inputLine = fileScanner.nextLine();
                            p.addInstruction(inputLine);
                        }
                    }
                    myProcedures.add(p);
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("The input file you requested was not found.");
        }
    }

    /**
     * <p>Interfaces with the private constructor to return the instance of the
	 * {@code Environment} Singleton.</p>
     * @return an instance of Environment after parsing
     */
    public static Environment getInstance()
    {
        if(myInstance == null)
        {
            myInstance = new Environment(myFilePath);
        }
        return myInstance;
    }

    /**
     * <p>Sets the path for the formal specification file to be read in and
	 * processed.</p>
     * @param inFilePath the path to the formal specification file to be
	 * processed.
     */
    public static void setFilePath(String inFilePath){
        myFilePath = inFilePath;
    }

    /**
     * <p>Returns a new {@code ArrayList} containing all {@link Definition}
	 * objects known to this {@code Environment}</p>
     * @return a new {@code ArrayList} of {@link Definition}s
	 * known by this {@code Environment}
     */
    public ArrayList<Definition> getDefinitions(){
		return( new ArrayList<Definition>(myDefinitions) );
    }

    /**
     * <p>Returns a new {@code ArrayList} containing all {@link Operation}
	 * objects known to this {@code Environment}</p>
     * @return a new {@code ArrayList} of {@link Operation}s
	 * known by this {@code Environment}
     */
    public ArrayList<Operation> getOperations(){
		return(new ArrayList<Operation>(myOperations));
    }

    /**
     * <p>Returns a new {@code ArrayList} containing all {@link Procedure}
	 * objects known to this {@code Environment}</p>
     * @return a new {@code ArrayList} of {@link Procedure}s
	 * known by this {@code Environment}
     */
    public ArrayList<Procedure> getProcedures(){
		return(new ArrayList<Procedure>(myProcedures));
    }

    /**
     * <p>Returns the filepath from which this {@code Environment} generates the
	 * proof table</p>
     * @return the filepath of the the input file
     */
    public String getFilePath(){
        return myFilePath;
    }
}
