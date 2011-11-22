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

    private static Environment myInstance = null;
	private static File myFile;
    private static String myFilePath;
    private ArrayList<Definition> myDefinitions;
    private ArrayList<Operation> myOperations;
    private ArrayList<Procedure> myProcedures;

    /**
     * <p>A private constructor for this {@code Singleton} class.
     * Sets the internal member variable to an instance of this class.
     * Requires that the {@code setFilePath} function be called beforehand in
	 * order to set the file to be read in.</p>
     * @param i the path to the formal specification file to be processed
     */
    private Environment(File inputFile)
	{
		myFile = inputFile;
        myFilePath = myFile.getAbsolutePath();
        myDefinitions = new ArrayList<Definition>();
        myOperations = new ArrayList<Operation>();
        myProcedures = new ArrayList<Procedure>();
        try
        {
            Scanner fileScanner = new Scanner(myFile);
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
                        inputLine = fileScanner.nextLine();
                        while(inputLine.compareTo("end")!=0)
                        {
                            p.addInstruction(inputLine);
                            inputLine = fileScanner.nextLine();
                        }
                    }
                    myProcedures.add(p);
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Input file not found: " + myFilePath);
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
            myInstance = new Environment(myFile);
        }
        return myInstance;
    }

    /**
     * <p>Sets the input file for this {@code Environment}.</p>
     * @param inFile the {@code File} object for the formal specification file.
     */
    public static void setFile(File inFile){
        myFile = inFile;
		myFilePath = inFile.getAbsolutePath();
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

    /**
     * <p>Searches this Environment's recognized operations for one that
     *  matches the passed Expression in both name and argument length.</p>
     * @param searchOperation an Expression for which the Environment will be searched
     * @return an Operation with name and argument equivalent to <code>ex</code>
     */
    public Operation searchEnvironmentOps(Expression searchOperation)
    {
        Operation similar = null;
        for(Operation o : myOperations)
        {
            if(o.getName().compareTo(searchOperation.getName())==0)
            {
                similar = o;
                if(o.getArgs().size()==searchOperation.getArgs().size())
                {
                    return o;
                }
            }
        }
        if(similar!=null)
        {
            System.err.println("Implicit declaration of operation: \n\t"+
                    searchOperation.getName()+"\nFound operation with different arguments:" +
                    "\n\t"+similar);
        }
        else
        {
            System.err.println("Implicit declaration of operation: \n\t"+
                    searchOperation.getName()+"\nNo suggestions available.");
        }
        return null;
    }

    /**
     * <p>Searches this Environment's recognized procedures for one that
     *  matches the passed Expression in both name and argument length.</p>
     * @param searchProcedure an Expression for which the Environment will be searched
     * @return a Procedure with name and argument equivalent to <code>ex</code>
     */
    public Procedure searchEnvironmentProcs(Expression searchProcedure)
    {
        for(Procedure o : myProcedures)
        {
            if(o.getName().compareTo(searchProcedure.getName())==0 &&
                    o.getArgs().size()==searchProcedure.getArgs().size()
                    )
            {
                return o;
            }
        }
        return null;
    }

    /**
     * <p>Searches this Environment's recognized definitions for one that
     *  matches the passed Expression in both name and argument length.</p>
     * @param searchDefinition an Expression for which the Environment will be searched
     * @return a Definition with name and argument equivalent to <code>ex</code>
     */
    public Definition searchEnvironmentDefs(Expression searchDefinition)
    {
        Definition similar = null;
        for(Definition o : myDefinitions)
        {
            if(o.getName().compareTo(searchDefinition.getName())==0)
            {
                similar = o;
                if(o.getArgs().size()==searchDefinition.getArgs().size())
                {
                    return o;
                }
            }
        }
        if(similar!=null)
        {
            System.err.println("Implicit declaration of definition: \n\t"+
                    searchDefinition.getName()+"\nFound definition with different arguments:" +
                    "\n\t"+similar);
        }
        else
        {
            System.err.println("Implicit declaration of definition: \n\t"+
                    searchDefinition.getName()+"\nNo suggestions available.");
        }
        System.exit(1);
        return null;
    }
}
