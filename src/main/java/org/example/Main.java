package org.example;

import org.example.factory.bookFactory;
import org.example.factory.patronFactory;
import org.example.strategy.searchByAuthor;
import org.example.strategy.searchByIsbn;
import org.example.strategy.searchByTitle;

import java.util.List;

public class Main {

    public static void main(String[] args){
        Library library = new Library();

        // Creating books using the BookFactory
        Book book1 = bookFactory.createBook("title1", "author1", "ISBN1", 2001);
        Book book2 = bookFactory.createBook("title2", "author2", "ISBN2", 2002);
        Book book3 = bookFactory.createBook("title3", "author3", "ISBN3", 2003);
        Book book4 = bookFactory.createBook("title4", "author4", "ISBN4", 2004);

        // Adding books to the Inventory
        library.getInventory().addBook(book1);
        library.getInventory().addBook(book2);
        library.getInventory().addBook(book3);
        library.getInventory().addBook(book4);

        System.out.println(" Inventory ---");
        library.getInventory().getInventory().values().forEach(System.out::println);
        System.out.println("Available Books: " + library.getInventory().getAvailableBooks().size());
        System.out.println("Borrowed Books: " + library.getInventory().getBorrowedBooks().size());
        System.out.println();

        // --- Update Book ---
        Book updatedBook2 = bookFactory.createBook("title20", "author20", "ISBN2", 2005);
        library.getInventory().updateBook(updatedBook2);
        System.out.println("Updated Book: " + library.getInventory().getInventory().get("ISBN2"));
        System.out.println();

//        Create a patron using the PatronFactory
        Patron patron1 = patronFactory.createPatron("1001", "patron1", "1234567890");
        Patron patron2 = patronFactory.createPatron("1002", "patron2", "0987654321");

        // Adding patrons to the Library
        library.addPatron(patron1);
        library.addPatron(patron2);

        System.out.println("Patrons ---");
        library.getPatronList().forEach(System.out::println);
        System.out.println();

        // --- Update Patron ---
        Patron updatedPatron1 = patronFactory.createPatron("1002", "patron2", "9999999999");
        library.updatePatron(updatedPatron1);
        System.out.println("Updated Patron: " + library.getPatron("1002"));
        System.out.println();

//        Searching books
        System.out.println("Searching Books ---");

        // Search by Title
        searchByTitle searchTitle = new searchByTitle();
        List<Book> foundByTitle = library.getInventory().searchingBook(searchTitle, "title1");
        System.out.println("byTitle: "+foundByTitle);
        // Search by Author
        searchByAuthor searchAuthor = new searchByAuthor();
        List<Book> foundByAuthor = library.getInventory().searchingBook(searchAuthor, "author3");
        System.out.println("byAuthor: "+foundByAuthor);
        // Search by ISBN
        searchByIsbn searchIsbn = new searchByIsbn();
        List<Book> foundByIsbn = library.getInventory().searchingBook(searchIsbn, "ISBN4");
        System.out.println("byISBN: "+foundByIsbn);
        System.out.println();

        // Lending operations
        System.out.println("Lending Books ---");
        Lending lending = new Lending(library.getInventory());
        // Checkout a book
        System.out.println("Checking out book1 to patron1");
        lending.checkoutBook(book1, patron1);
        System.out.println("Borrowed Books after checkout: " + library.getInventory().getBorrowedBooks().size());
        System.out.println(patron1.getPatronName() + "'s current borrowed books: " + patron1.getCurrentBorrowed());
        System.out.println();

        // Get lending info
        Lending.LendingInfo info = lending.getLendingInfo(book1);
        if (info != null) {
            System.out.println("Lending info for " + book1.getTitle() + ": " + info);
            System.out.println();
        }

        // Return a book
        System.out.println("Returning book1 from patron1");
        lending.returnBook(book1, patron1);
        System.out.println("Borrowed Books after return: " + library.getInventory().getBorrowedBooks().size());
        System.out.println(patron1.getPatronName() + "'s current borrowed books: " + patron1.getCurrentBorrowed());
        System.out.println();

    }
}