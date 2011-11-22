/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectproof;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a proof table, which is a list of <code>ProofTableState</code>
 * generated from a procedure, <code>myProcedure</code>
 */
public class ProofTable {
    ArrayList<ProofTableState> myStates;
    Procedure myProcedure;

    /**
     * <p>Creates a new ProofTable for the Procedure passed in with respect to
     * the Definitions and Operations described by the current Environment.</p>
     * @param p the Procedure that this ProofTable attempts to prove
     */
    public ProofTable(Procedure p)
    {
        myProcedure = p;
        myStates = new ArrayList<ProofTableState>();
        HashMap<String, String> subMap = new HashMap<String, String>();
        ArrayList<Expression> instructions = p.getInstructions();
        Environment e = Environment.getInstance();
        for(int sC = 0; sC<=instructions.size(); sC++)
        {
            subMap.clear();
            String header = "State " + sC + "\n========\n";
            ProofTableState currState;
            Expression assumes, confirms;
            if(sC==0)
            {
                Expression in = instructions.get(sC);
                Operation op = e.searchEnvironmentOps(in);
                ArrayList<String> values = new ArrayList<String>();
                ArrayList<String> keys = new ArrayList<String>(op.getArgs());
                keys.addAll(p.getArgs());
                for(int mapNdx = 0; mapNdx<keys.size(); mapNdx++)
                {
                    values.add(in.getArgs()
                            .get(mapNdx%in.getArgs().size())
                            .toString()+sC
                            );
                    subMap.put(keys.get(mapNdx),
                            in.getArgs()
                            .get(mapNdx%in.getArgs().size())
                            .toString()+sC
                            );
                    subMap.put("#"+keys.get(mapNdx),
                            in.getArgs()
                            .get(mapNdx%in.getArgs().size())
                            .toString()+(sC-1)
                            );
                }
                assumes = p.getRequires().substitute(subMap);
                confirms = op.getRequires().substitute(subMap);
            }
            else if(sC==instructions.size())
            {
                Expression in = instructions.get(sC-1);
                Operation op = e.searchEnvironmentOps(in);
                ArrayList<String> values = new ArrayList<String>();
                ArrayList<String> keys = new ArrayList<String>(op.getArgs());
                keys.addAll(p.getArgs());
                for(int mapNdx = 0; mapNdx<keys.size(); mapNdx++)
                {
                    values.add(in.getArgs()
                            .get(mapNdx%in.getArgs().size())
                            .toString()+(sC));
                    subMap.put(keys.get(mapNdx),
                            in.getArgs()
                            .get(mapNdx%in.getArgs().size())
                            .toString()+(sC));
                    subMap.put("#"+keys.get(mapNdx),
                            in.getArgs()
                            .get(mapNdx%in.getArgs().size())
                            .toString()+(sC-1));
                }
                assumes = e.searchEnvironmentOps(instructions.get(sC-1))
                        .getEnsures().substitute(subMap);
                confirms = p.getEnsures().substitute(subMap);
            }
            else
            {
                Expression in = instructions.get(sC-1);
                Operation op = e.searchEnvironmentOps(in);
                ArrayList<String> values = new ArrayList<String>();
                ArrayList<String> keys = new ArrayList<String>(op.getArgs());
                for(int mapNdx = 0; mapNdx<keys.size(); mapNdx++)
                {
                    values.add(in.getArgs().get(mapNdx).toString()+sC);
                    subMap.put(keys.get(mapNdx),
                            in.getArgs().get(mapNdx).toString()+sC);
                    subMap.put("#"+keys.get(mapNdx),
                            in.getArgs().get(mapNdx).toString()+(sC-1));
                }
                assumes = e.searchEnvironmentOps(instructions.get(sC-1))
                        .getEnsures().substitute(subMap);
                confirms = e.searchEnvironmentOps(instructions.get(sC))
                        .getRequires().substitute(subMap);
            }
            myStates.add(new ProofTableState(header, assumes, confirms));
        }
    }

    /**
     * <p>Returns a String representation of this ProofTable and all of the
     * ProofTableStates within it.</p>
     * @return a String representing this ProofTable in full
     */
    @Override
    public String toString()
    {
        String retVal = "Proof Table for ["+myProcedure.getName()+"]\n\n";
        for(ProofTableState a : myStates)
        {
            retVal += a;
        }
        return retVal.trim();
    }
    
}
