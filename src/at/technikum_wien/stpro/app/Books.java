package at.technikum_wien.stpro.app;


/** This class implements the user interface for books. */
public class Books
{
    //////////////////////////////////////////////////////////////////////////////
    // public static methods                                                    //
    //////////////////////////////////////////////////////////////////////////////

    /** This implements the books main menu. */
    public static void mainMenu()
    {
        while(true)
        {
            System.out.println("\n[L] ... LIST BOOKS BY INDEX");
            System.out.println("[T] ... LIST BOOKS BY TITLE");
            System.out.println("[A] ... LIST BOOKS BY AUTHOR");
            System.out.println("[F] ... FIND BOOKS");
            System.out.println("[N] ... NEW BOOK");
            System.out.println("[n] ... OPEN BOOK (ID: n)");
            System.out.println("[B] ... BACK");
            System.out.println();

            String cmd = Main.getCommand();
            switch(cmd)
            {
                case "L":
                    _list();
                    break;
                case "T":
                    _list(Book.sort(Book.TITLE_FIELD));
                    break;
                case "A":
                    _list(Book.sort(Book.AUTHOR_FIELD));
                    break;
                case "F":
                    _findBooks();
                    break;
                case "N":
                    _addBook();
                    break;
                case "B": return;
                default:
                    _openBook(cmd);
            }
        }
    }


    /** This implements the book menu. */
    public static void bookMenu()
    {
        while(true)
        {
            System.out.printf("\n(%d) %s by %s\n\n", Book.currentBook.id, Book.currentBook.title, Book.currentBook.author);
            System.out.println("[S] ... SHOW DETAILS");
            System.out.println("[T] ... EDIT TITLE");
            System.out.println("[A] ... EDIT AUTHOR");
            System.out.println("[Y] ... EDIT YEAR");
            System.out.println("[P] ... EDIT PRICE");
            System.out.println("[U] ... EDIT PUBLISHER");
            System.out.println("[R] ... ADD RATING");
            System.out.println("[D] ... DELETE");
            System.out.println("[B] ... BACK");
            System.out.println();

            switch (Main.getCommand())
            {
                case "S":
                    _showCurrentBook();
                    break;
                case "T":
                    _changeTitle();
                    break;
                case "A":
                    _changeAuthor();
                    break;
                case "Y":
                    _changeYear();
                    break;
                case "P":
                    _changePrice();
                    break;
                case "U":
                    _changePublisher();
                    break;
                case "R":
                    _addRating();
                    break;
                case "D":
                    if(_deleteBook()) return;
                    break;
                case "B":
                    Book.currentBook = null;
                    return;
            }
        }
    }



    //////////////////////////////////////////////////////////////////////////////
    // private static methods                                                   //
    //////////////////////////////////////////////////////////////////////////////

    /** Adds a rating. */
    private static void _addRating()
    {
        System.out.print("Add rating: ");
        String v = Main.sc.nextLine();

        int r = -1;
        try
        {
            r = Integer.parseInt(v);
        }
        catch(Exception ignored) {}

        if((r > 0) && (r < 6))
        {
            int[] ratings = Book.currentBook.ratings;
            Book.currentBook.ratings = new int[ratings.length + 1];
            for(int i = 0; i < ratings.length; i++)
            {
                Book.currentBook.ratings[i] = ratings[i];
            }
            Book.currentBook.ratings[ratings.length] = r;
        }
        else { System.out.println("Invalid rating."); }
    }


    /** Changes the publisher of the current book. */
    private static void _changePublisher()
    {
        if(Book.currentBook.publisher == null)
        {
            System.out.println("Old publisher: (none).");
        }
        else
        {
            System.out.printf("Old publisher: (%d) %s.\n", Book.currentBook.publisher.id, Book.currentBook.publisher.name);
        }
        System.out.print("New publisher: ");
        String v = Main.sc.nextLine();

        if(!v.isBlank())
        {
            int p = -1;
            try
            {
                p = Integer.parseInt(v);
            }
            catch(Exception ignored) {}

            if(Publisher.publishers.containsKey(p))
            {
                Book.currentBook.publisher = Publisher.publishers.get(p);
                return;
            }

            for(Publisher i: Publisher.publishers.values())
            {
                if(i.name.toLowerCase().startsWith(v.toLowerCase()))
                {
                    Book.currentBook.publisher = i;
                    return;
                }
            }

            System.out.println("Invalid publisher.");
        }
    }


    /** Changes the price of the current book. */
    private static void _changePrice()
    {
        System.out.printf("Old price: %.2f.\n", Book.currentBook.price);
        System.out.print("New price: ");
        String v = Main.sc.nextLine();

        if(!v.isBlank())
        {
            double p = -1;
            try
            {
                v = v.replace(',', '.');
                p = Double.parseDouble(v);
            }
            catch(Exception ignored) {}

            if(p > 0)
            {
                Book.currentBook.price = p;
            }
            else { System.out.println("Invalid price."); }
        }
    }


    /** Changes the year of the current book. */
    private static void _changeYear()
    {
        System.out.printf("Old year: %d.\n", Book.currentBook.year);
        System.out.print("New year: ");
        String v = Main.sc.nextLine();

        if(!v.isBlank())
        {
            int y = -1;
            try
            {
                y = Integer.parseInt(v);
            }
            catch(Exception ignored) {}

            if((y > 1500) && (y < 2026))
            {
                Book.currentBook.year = y;
            }
            else { System.out.println("Invalid year."); }
        }
    }


    /** Changes the title of the current book. */
    private static void _changeTitle()
    {
        System.out.printf("Old title: %s.\n", Book.currentBook.title);
        System.out.print("New title: ");
        String v = Main.sc.nextLine();

        if(!v.isBlank())
        {
            Book.currentBook.title = v;
        }
    }


    /** Changes the author of the current book. */
    private static void _changeAuthor()
    {
        System.out.printf("Old author: %s.\n", Book.currentBook.title);
        System.out.print("New author: ");
        String v = Main.sc.nextLine();

        if(!v.isBlank())
        {
            Book.currentBook.author = v;
        }
    }


    /** Deletes the current book.
     * @return Returns TRUE if the current book is deleted, otherwise returns FALSE. */
    private static boolean _deleteBook()
    {
        System.out.print("Do you really want to delete this book ([y]es|[N]o)? ");
        if(Main.sc.nextLine().toLowerCase().startsWith("y"))
        {
            Book.books.remove(Book.currentBook.id);
            Book.currentBook = null;
            return true;
        }

        return false;
    }


    /** Shows the current book. */
    private static void _showCurrentBook()
    {
        System.out.printf("Title:       %s\n", Book.currentBook.title);
        System.out.printf("Author:      %s\n", Book.currentBook.author);
        System.out.printf("Publisher:   %s\n", Book.currentBook.publisher == null ? "(none)" : Book.currentBook.publisher.name);
        System.out.printf("Year:        %d\n", Book.currentBook.year);
        System.out.printf("Price:       %.2f\n", Book.currentBook.price);
        System.out.print("Ratings:     ");
        for(int i = 0; i < Book.currentBook.ratings.length; i++)
        {
            if(i > 0) { System.out.print(", "); }
            System.out.printf("%d", Book.currentBook.ratings[i]);
        }
        System.out.printf("\nAvg. Rating: %.2f\n", Book.currentBook.getAvgRating());
    }


    /** Opens a book. */
    private static void _openBook(String id)
    {
        int n = -1;
        try
        {
            n = Integer.parseInt(id);
        }
        catch(Exception ignored) {}

        if(Book.books.containsKey(n))
        {
            Book.currentBook = Book.books.get(n);
            bookMenu();
        }
        else { System.out.println("Invalid option."); }
    }


    /** Adds a book. */
    private static void _addBook()
    {
        Book.currentBook = new Book();
        Book.books.put(Book.currentBook.id, Book.currentBook);
        bookMenu();
    }


    /** Implements finding books. */
    private static void _findBooks()
    {
        System.out.print("Search: ");
        _list(Book.find(Main.sc.nextLine()));
    }


    /** List books. */
    private static void _list()
    {
        _list(Book.books.values().toArray(new Book[0]));
    }


    /** Lists books.
     * @param books An array of books. */
    private static void _list(Book[] books)
    {
        System.out.printf("%-3s   %-30s   %-30s   %-20s   %-4s   %-8s   %-6s\n", "ID", "Title", "Author", "Publisher", "Year", "Price", "Rating");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        for(Book i: books)
        {
            System.out.printf("%3d   %-30s   %-30s   %-20s   %4d   %8.2f   %6.2f\n", i.id, i.title, i.author, (i.publisher == null) ? "" : i.publisher.name,  i.year, i.price, i.getAvgRating());
        }
    }
}
