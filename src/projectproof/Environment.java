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
 * The environment stores all of the definition objects, operations, and
 * procedures from the input file.
 */
public class Environment {
    
    private static Environment myInstance;
    private static String myFilePath;
    public ArrayList<Definition> myDefinitions;
    public ArrayList<Operation> myOperations;
    public ArrayList<Procedure> myProcedures;
    
    /**
     * <p>A private constructor for this Singleton Environment class.
     *   Sets the internal member variable to an instance of this class.  
     * Requires that the setFilePath function have been called first to set
     *  the file that will be read in.</p>
     * @param filePath a String path to a formal specification file
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
            System.out.println("The input file you requested was not found.");
        }
    }
    
    /**
     * <p>A public function that interfaces with the private constructor to 
     * return an instance of the Environment singleton.</p>
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
     * <p>Sets the path for the formal specification file to be read in.</p>
     * @param fp a String representing the path to a formal specification file
     */
    public static void setFilePath(String fp)
    {
        myFilePath = fp;
    }
    
    /**
     * <p>Returns a copy of the list of definitions known to this Environment
     * after parsing.</p>
     * @return an ArrayList of Definitions known by this Environment
     */
    public ArrayList<Definition> getDefinitions()
    {
        ArrayList<Definition> retVal = new ArrayList<Definition>();
        for(Definition a : myDefinitions)
        {
            retVal.add(a);
        }
        return retVal;
    }
    
    /**
     * <p>Returns a copy of the list of operations known to this Environment
     * after parsing.</p>
     * @return an ArrayList of Operations known by this Environment
     */
    public ArrayList<Operation> getOperations()
    {
        ArrayList<Operation> retVal = new ArrayList<Operation>();
        for(Operation a : myOperations)
        {
            retVal.add(a);
        }
        return retVal;
    }
    
    /**
     * <p>Returns a copy of the list of procedures known to this Environment
     * after parsing.</p>
     * @return an ArrayList of Procedures known by this Environment
     */
    public ArrayList<Procedure> getProcedures()
    {
        ArrayList<Procedure> retVal = new ArrayList<Procedure>();
        for(Procedure a : myProcedures)
        {
            retVal.add(a);
        }
        return retVal;
    }
    
    /**
     * <p>Returns a String representation of the path that the Environment
     *  currently holds.</p>
     * @return a filepath
     */
    public String getFilePath()
    {
        return myFilePath;
    }

    /**
     * <p>Searches this Environment's recognized operations for one that
     *  matches the passed Expression in both name and argument length.</p>
     * @param ex an Expression for which the Environment will be searched
     * @return an Operation with name and argument equivalent to <code>ex</code>
     */
    public Operation searchEnvironmentOps(Expression ex)
    {
        for(Operation o : myOperations)
        {
            if(o.getName().compareTo(ex.getName())==0 &&
                    o.getArgs().size()==ex.getArgs().size()
                    )
            {
                return o;
            }
        }
        return null;
    }

    /**
     * <p>Searches this Environment's recognized procedures for one that
     *  matches the passed Expression in both name and argument length.</p>
     * @param ex an Expression for which the Environment will be searched
     * @return a Procedure with name and argument equivalent to <code>ex</code>
     */
    public Procedure searchEnvironmentProcs(Expression ex)
    {
        for(Procedure o : myProcedures)
        {
            if(o.getName().compareTo(ex.getName())==0 &&
                    o.getArgs().size()==ex.getArgs().size()
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
     * @param ex an Expression for which the Environment will be searched
     * @return a Definition with name and argument equivalent to <code>ex</code>
     */
    public Definition searchEnvironmentDefs(Expression ex)
    {
        for(Definition o : myDefinitions)
        {
            if(o.getName().compareTo(ex.getName())==0 &&
                    o.getArgs().size()==ex.getArgs().size()
                    )
            {
                return o;
            }
        }
        return null;
    }
}
