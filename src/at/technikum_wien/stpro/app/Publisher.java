package at.technikum_wien.stpro.app;

import java.util.ArrayList;
import java.util.HashMap;



/** This class represents a publisher. */
public class Publisher
{
    //////////////////////////////////////////////////////////////////////////////
    // private static members                                                   //
    //////////////////////////////////////////////////////////////////////////////

    /** Next ID field. */
    private static int _nextID = 0;



    //////////////////////////////////////////////////////////////////////////////
    // public static members                                                    //
    //////////////////////////////////////////////////////////////////////////////

    /** All publishers in the application. */
    public static HashMap<Integer, Publisher> publishers = new HashMap<>();

    /** Current publisher. */
    public static Publisher currentPublisher = null;



    //////////////////////////////////////////////////////////////////////////////
    // public members                                                           //
    //////////////////////////////////////////////////////////////////////////////

    /** Publisher ID. */
    public int id;

    /** Publisher Name. */
    public String name = "New Publisher";

    /** Publisher address. */
    public String address = "No Address";



    //////////////////////////////////////////////////////////////////////////////
    // constructors                                                             //
    //////////////////////////////////////////////////////////////////////////////

    /** Creates a new instance of this class. */
    public Publisher()
    {
        id = _nextID++;
    }



    //////////////////////////////////////////////////////////////////////////////
    // public static methods                                                    //
    //////////////////////////////////////////////////////////////////////////////

    /** Finds publishers.
     * @param exp Search expression.
     * @return Returns an array of found publishers. */
    public static Publisher[] find(String exp)
    {
        ArrayList<Publisher> rval = new ArrayList<>();

        for(Publisher i: Publisher.publishers.values())
        {
            if(i.name.toLowerCase().contains(exp.toLowerCase()))
            {
                rval.add(i);
            }
            else if(i.address.toLowerCase().contains(exp.toLowerCase()))
            {
                rval.add(i);
            }
        }

        return rval.toArray(new Publisher[0]);
    }
}
