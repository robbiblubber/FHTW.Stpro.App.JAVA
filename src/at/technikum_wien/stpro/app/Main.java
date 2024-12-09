package at.technikum_wien.stpro.app;

import java.util.Scanner;
import java.util.Vector;


public class Main
{
    //////////////////////////////////////////////////////////////////////////////
    // public static members                                                    //
    //////////////////////////////////////////////////////////////////////////////

    /** Scanner instance. */
    public static Scanner sc = new Scanner(System.in);

    /** Command history. */
    public static Vector<String> commandHistory = new Vector<>();



    //////////////////////////////////////////////////////////////////////////////
    // entry point                                                              //
    //////////////////////////////////////////////////////////////////////////////

    /** Application entry point.
     * @param args Command line arguments. */
    public static void main(String[] args)
    {
        _makeupData();

        mainMenu();
    }



    //////////////////////////////////////////////////////////////////////////////
    // public static methods                                                    //
    //////////////////////////////////////////////////////////////////////////////

    /** Gets a command string.
     * @return Returns a command string. */
    public static String getCommand()
    {
        System.out.print("> ");
        String cmd = sc.nextLine().toUpperCase();
        commandHistory.add(cmd);

        return cmd;
    }


    /** This implements the main menu. */
    public static void mainMenu()
    {
        while(true)
        {
            System.out.println("\n\n[B] ... BOOKS");
            System.out.println("[P] ... PUBLISHERS");
            System.out.println("[X] ... EXIT");

            switch(getCommand())
            {
                case "B":
                    Books.mainMenu();
                    break;
                case "P":
                    Publishers.mainMenu();
                    break;
                case "X":
                    System.out.print("Do you really want to exit [Y]es/[n]? ");
                    if(!sc.nextLine().toLowerCase().startsWith("n")) return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }


    /** Makes up test data. */
    private static void _makeupData()
    {
        Publisher p = new Publisher();
        p.name = "Weird Books";
        p.address = "Dublin";
        Publisher.publishers.put(p.id, p);

        Book b = new Book();
        b.title = "Weird History";
        b.author = "Patrick O'Brian";
        b.price = 25.3;
        b.year = 2014;
        b.publisher = p;
        b.ratings = new int[] { 2, 3, 4 };
        Book.books.put(b.id, b);

        b = new Book();
        b.title = "Zombie Apocalypse Now";
        b.author = "Cliff Murray";
        b.price = 12.88;
        b.year = 2014;
        b.publisher = p;
        b.ratings = new int[] { 2, 2, 3 };
        Book.books.put(b.id, b);

        b = new Book();
        b.title = "I hate Jazz";
        b.author = "Alicia Wonder";
        b.price = 26.99;
        b.year = 2017;
        b.publisher = p;
        b.ratings = new int[] { 4, 2 };
        Book.books.put(b.id, b);

        b = new Book();
        b.title = "Who framed me?";
        b.author = "Roger Rabbit";
        b.price = 15.99;
        b.year = 1988;
        b.publisher = p;
        b.ratings = new int[] { 4, 5, 4, 3, 5, 5 };
        Book.books.put(b.id, b);
    }
}