package org.example;

import org.example.strategy.searchBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private List<Book> availableBooks;
    private Map<String, Book> inventory;   //ISBN to Book
    private List<Book> borrowedBooks;

//    Constructor
    public Inventory() {
        this.availableBooks = new ArrayList<>();
        this.inventory = new HashMap<>();
        this.borrowedBooks = new ArrayList<>();
    }

//    Add a book to inventory
    public void addBook(Book book){
        if (book != null && !inventory.containsKey(book.getISBN())) {
            inventory.put(book.getISBN(), book);
            availableBooks.add(book);
        }
    }
    // Remove a book from inventory using ISBN
    public void removeBook(String ISBN){
        Book book = inventory.get(ISBN);
        if(book != null){
            availableBooks.remove(book);
            inventory.remove(ISBN);
            borrowedBooks.remove(book);
        }
    }
    public void updateBook(Book updatedBook){
        String ISBN = updatedBook.getISBN();
        if(inventory.containsKey(ISBN)){
            inventory.put(ISBN, updatedBook);
            // Update in availableBooks list
            for(int i=0; i<availableBooks.size(); i++){
                if(availableBooks.get(i).getISBN().equals(ISBN)){
                    availableBooks.set(i, updatedBook);
                    break;
                }
            }
            // Update in borrowedBooks list
            for(int i=0; i<borrowedBooks.size(); i++) {
                if (borrowedBooks.get(i).getISBN().equals(ISBN)) {
                    borrowedBooks.set(i, updatedBook);
                    break;
                }
            }
        }
    }

    public List<Book> searchingBook(searchBook searchStrategy, String query) {

        List<Book> allBooks = new ArrayList<>(inventory.values());

        return searchStrategy.search(query, allBooks);
    }

    public Book isAvailable(String ISBN){
        Book book = getInventory().get(ISBN);
        if(book != null && getAvailableBooks().contains(book)){
            return book;
        }
        return null;
    }

    public List<Book> getAvailableBooks() {
        return availableBooks;
    }
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    public Map<String, Book> getInventory() {
        return inventory;
    }
}
