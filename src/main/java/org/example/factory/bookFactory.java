package org.example.factory;

import org.example.Book;

public class bookFactory {
    public static Book createBook(String title, String author, String ISBN, int publicationYear) {
        return new Book(title, author, ISBN, publicationYear);
    }
}
