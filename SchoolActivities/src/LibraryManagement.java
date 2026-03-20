import java.util.InputMismatchException;
import java.util.Scanner;

class Linked {
    public int BookId;
    public String BookName;
    public String Author;
    public Linked next;
    public Linked previous;

    Linked (int BookId, String BookName, String Author) {
        this.BookId = BookId;
        this.BookName = BookName;
        this.Author = Author;
    }

    public void DisplayBooks () {
        System.out.println("Book [ID: " + BookId + " , Title: " + BookName + " , Author:  " + Author + "]");
    }
}

class Doubly {
    private Linked first;
    private Linked last;

    public Doubly () {
        first = null;
        last = null;
    }

    public boolean empty() {
        return first == null;
    }


    // add
    public void AddBooks (int BookId, String BookName, String Author) throws Exception {
        Linked current = first;
        while (current != null) {
            if (current.BookId == BookId) {
                throw new Exception("Book Already Exists!");
            }
            current = current.next;
        }
        Linked newLinked = new Linked(BookId, BookName, Author);
        if (first == null) {
            first = newLinked;
            last = newLinked;
        }
        else {
            last.next = newLinked;
            newLinked.previous = last;
            last = newLinked;
        }
        System.out.print("Book: ");
        newLinked.DisplayBooks();
        System.out.print(" added successfully.");
    }

    // view
    public void ViewBooks () throws Exception {
        Linked current = first;

        if (first == null) {
            throw new Exception("This LinkList is Empty.");
        }
        while (current != null) {
            current.DisplayBooks();
            current = current.next;
        }
    }

    // update
    public void UpdateBook (String input, Scanner sc) throws Exception {
        Linked current = first;
        Boolean found = false;

        if (first == null) {
            throw new Exception("LinkList empty. There is no book that can be updated.");
        }
        while (current != null) {
            if (current.BookName.equalsIgnoreCase(input) || current.Author.equalsIgnoreCase(input)) {
                current.DisplayBooks();
                System.out.println();

                // update
                System.out.print("BookId: ");
                current.BookId = sc.nextInt();
                sc.nextLine();
                System.out.print("Title: ");
                current.BookName = sc.nextLine();
                System.out.print("Author: ");
                current.Author = sc.nextLine();

                System.out.println();
                System.out.print("Book [" + current.BookId + "] updated to: ");
                current.DisplayBooks();
                break;
            }
            else if (!found) {
                throw new Exception("No book found.");
            }
            current = current.next;
        }
    }

    // delete
    public void DeleteBook (int BookId) throws Exception {
        Linked current = first;

        if (first == null) {
            throw new Exception("Nothing to delete. LinkList is empty.");
        }

        while (current != null && current.BookId != BookId) {
            current = current.next;
        }
        if (current == first) {
            first = current.next;
            if (first != null) {
                first.previous = null;
            }
        }
        else if (current == last) {
            last = current.previous;
            if (last != null) {
                last.next = null;
            }
        }
        else {
            current.previous.next = current.next;
            current.next.previous = current.previous;
        }

        System.out.println("Book [ " + current.BookId + " Title: " + current.BookName + " removed from the Library.");
    }

}


public class LibraryManagement {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Doubly newDoubly = new Doubly();

        try {
            while (true) {
                System.out.println("== LIBRARY ==");
                System.out.println("[1] Add Book");
                System.out.println("[2] View Book");
                System.out.println("[3] Update Book");
                System.out.println("[4] Remove Book");
                System.out.println("[5] Exit");
                System.out.print("> ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.println("--- Add Book ---");
                    System.out.print("Book Id: ");
                    int BookId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Title: ");
                    String BookName = sc.nextLine();
                    System.out.println("Author: ");
                    String Author = sc.nextLine();

                    newDoubly.AddBooks(BookId, BookName, Author);
                }
                else if (choice == 2) {
                    System.out.println("--- View Book ---");
                    newDoubly.ViewBooks();
                }
                else if (choice == 3) {
                    System.out.println("-- Update Book --");
                    System.out.println("Search by Title/Author:");
                    System.out.print("> ");
                    String input = sc.nextLine();
                    newDoubly.UpdateBook(input,sc);
                }
                else if (choice == 4) {
                    System.out.println("-- Delete Book --");
                    System.out.println("Input BookId:");
                    System.out.print("> ");
                    int BookId = sc.nextInt();
                    sc.nextLine();
                    newDoubly.DeleteBook(BookId);
                }
                else if (choice == 5) {
                    sc.close();
                    System.exit(0);
                    break;
                }
                else {
                    System.out.println("Invalid option. Try again.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input, try again.");
            sc.nextLine();
            main(args);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            main(args);
        }
    }
}
