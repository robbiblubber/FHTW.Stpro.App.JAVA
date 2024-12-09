package at.technikum_wien.stpro.app;

/** This class implements the user interface for books. */
public class Publishers
{
    //////////////////////////////////////////////////////////////////////////////
    // public static methods                                                    //
    //////////////////////////////////////////////////////////////////////////////

    /** This implements the publishers main menu. */
    public static void mainMenu()
    {
        while(true)
        {
            System.out.println("\n[L] ... LIST PUBLISHERS");
            System.out.println("[F] ... FIND PUBLISHERS");
            System.out.println("[N] ... NEW PUBLISHER");
            System.out.println("[n] ... OPEN PUBLISHER (ID: n)");
            System.out.println("[B] ... BACK");
            System.out.println();

            String cmd = Main.getCommand();
            switch(cmd)
            {
                case "L":
                    _list();
                    break;
                case "F":
                    _findPublishers();
                    break;
                case "N":
                    _addPublisher();
                    break;
                case "B": return;
                default:
                    _openPublisher(cmd);
            }
        }
    }


    /** This implements the publisher menu. */
    public static void publisherMenu()
    {
        while(true)
        {
            System.out.printf("\n(%d) %s at %s\n\n", Publisher.currentPublisher.id, Publisher.currentPublisher.name, Publisher.currentPublisher.address);
            System.out.println("[S] ... SHOW DETAILS");
            System.out.println("[N] ... EDIT NAME");
            System.out.println("[A] ... EDIT ADDRESS");
            System.out.println("[D] ... DELETE");
            System.out.println("[B] ... BACK");
            System.out.println();

            switch (Main.getCommand())
            {
                case "S":
                    _showCurrentPublisher();
                    break;
                case "N":
                    _changeName();
                    break;
                case "A":
                    _changeAddress();
                    break;
                case "D":
                    if(_deletePublisher()) return;
                    break;
                case "B":
                    Publisher.currentPublisher = null;
                    return;
            }
        }
    }



    //////////////////////////////////////////////////////////////////////////////
    // private static methods                                                   //
    //////////////////////////////////////////////////////////////////////////////

    /** Lists publishers. */
    private static void _list()
    {
        _list(Publisher.publishers.values().toArray(new Publisher[0]));
    }


    /** Lists publishers. */
    private static void _list(Publisher[] publishers)
    {
        System.out.printf("%-3s   %-30s   %-30s\n", "ID", "Name", "Address");
        System.out.println("---------------------------------------------------------------------");
        for(Publisher i: publishers)
        {
            System.out.printf("%3d   %-30s   %-30s\n", i.id, i.name, i.address);
        }
    }


    /** Changes the name of the current publisher. */
    private static void _changeName()
    {
        System.out.printf("Old name: %s.\n", Publisher.currentPublisher.name);
        System.out.print("New name: ");
        String v = Main.sc.nextLine();

        if(!v.isBlank())
        {
            Publisher.currentPublisher.name = v;
        }
    }


    /** Changes the address of the current publisher. */
    private static void _changeAddress()
    {
        System.out.printf("Old address: %s.\n", Publisher.currentPublisher.address);
        System.out.print("New address: ");
        String v = Main.sc.nextLine();

        if(!v.isBlank())
        {
            Publisher.currentPublisher.address = v;
        }
    }


    /** Deletes the current publisher.
     * @return Returns TRUE if the current publisher is deleted, otherwise returns FALSE. */
    private static boolean _deletePublisher()
    {
        System.out.print("Do you really want to delete this publisher ([y]es|[N]o)? ");
        if(Main.sc.nextLine().toLowerCase().startsWith("y"))
        {
            Publisher.publishers.remove(Publisher.currentPublisher.id);
            Publisher.currentPublisher = null;
            return true;
        }

        return false;
    }


    /** Shows the current publisher. */
    private static void _showCurrentPublisher()
    {
        System.out.printf("Name:    %s\n", Publisher.currentPublisher.name);
        System.out.printf("Address: %s\n", Publisher.currentPublisher.address);
    }


    /** Opens a publisher. */
    private static void _openPublisher(String id)
    {
        int n = -1;
        try
        {
            n = Integer.parseInt(id);
        }
        catch(Exception ignored) {}

        if(Publisher.publishers.containsKey(n))
        {
            Publisher.currentPublisher = Publisher.publishers.get(n);
            publisherMenu();
        }
        else { System.out.println("Invalid option."); }
    }


    /** Adds a publisher. */
    private static void _addPublisher()
    {
        Publisher.currentPublisher = new Publisher();
        Publisher.publishers.put(Publisher.currentPublisher.id, Publisher.currentPublisher);
        publisherMenu();
    }


    /** Implements finding publishers. */
    private static void _findPublishers()
    {
        System.out.print("Search: ");
        _list(Publisher.find(Main.sc.nextLine()));
    }
}
