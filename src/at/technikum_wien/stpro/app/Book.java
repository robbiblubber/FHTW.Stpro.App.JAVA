package at.technikum_wien.stpro.app;


import java.util.ArrayList;
import java.util.HashMap;



/** This class represents a book. */
public class Book
{
    //////////////////////////////////////////////////////////////////////////////
    // private static members                                                   //
    //////////////////////////////////////////////////////////////////////////////

    /** Next ID field. */
    private static int _nextID = 0;



    //////////////////////////////////////////////////////////////////////////////
    // public static members                                                    //
    //////////////////////////////////////////////////////////////////////////////

    /** All books in the application. */
    public static HashMap<Integer, Book> books = new HashMap<>();

    /** Currently open book. */
    public static Book currentBook = null;

    /** Author field constant. */
    public static final int AUTHOR_FIELD = 1;

    /** Title field constant. */
    public static final int TITLE_FIELD = 0;



    //////////////////////////////////////////////////////////////////////////////
    // public members                                                           //
    //////////////////////////////////////////////////////////////////////////////

    /** Book ID. */
    int id;

    /** Book title. */
    public String title = "New Book";

    /** Author. */
    public String author = "Unknown";

    /** Publishing year. */
    int year = 9999;

    /** Price. */
    double price = 0;

    /** Ratings. */
    int[] ratings = new int[0];

    /** Publisher. */
    Publisher publisher;



    //////////////////////////////////////////////////////////////////////////////
    // constructors                                                             //
    //////////////////////////////////////////////////////////////////////////////

    /** Creates a new instance of this class. */
    public Book()
    {
        id = _nextID++;
    }



    //////////////////////////////////////////////////////////////////////////////
    // public methods                                                           //
    //////////////////////////////////////////////////////////////////////////////

    /** Gets the average rating for the book.
     * @return Returns the book's average rating. */
    public double getAvgRating()
    {
        if(ratings.length == 0) { return 0; }

        double v = 0;
        for(int i: ratings) { v += i; }

        return v / ratings.length;
    }



    //////////////////////////////////////////////////////////////////////////////
    // public static methods                                                    //
    //////////////////////////////////////////////////////////////////////////////

    /** Gets a sorted array of all available books.
     *  This uses the BubbleSort algorithm for sorting.
     * @param field Sort field.
     * @return Returns a sorted array of books. */
    public static Book[] sort(int field)
    {
        Book[] rval = books.values().toArray(new Book[0]);
        Book tmp;

        for(int i = 1; i < rval.length; i++)
        {
            for(int k = 0; k < rval.length - i; k++)
            {
                if(compare(field, rval[k], rval[k + 1]) > 0)
                {
                    tmp = rval[k];
                    rval[k] = rval[k + 1];
                    rval[k + 1] = tmp;
                }
            }
        }
        return rval;
    }


    /** Finds books.
     * @param exp Search expression.
     * @return Returns an array of found books. */
    public static Book[] find(String exp)
    {
        ArrayList<Book> rval = new ArrayList<>();

        for(Book i: Book.books.values())
        {
            if(i.title.toLowerCase().contains(exp.toLowerCase()))
            {
                rval.add(i);
            }
            else if(i.author.toLowerCase().contains(exp.toLowerCase()))
            {
                rval.add(i);
            }
            else if((i.publisher != null) && i.publisher.name.toLowerCase().contains(exp.toLowerCase()))
            {
                rval.add(i);
            }
        }

        return rval.toArray(new Book[0]);
    }


    /** Compares two books.
     * @param field Comparison field.
     * @param a A book.
     * @param b Another book.
     * @return Returns a value indicating the relation between the books. */
    private static int compare(int field, Book a, Book b)
    {
        if(field == Book.AUTHOR_FIELD)
        {
            return a.author.compareToIgnoreCase(b.author);
        }
        else { return a.title.compareToIgnoreCase(b.title); }
    }
}
