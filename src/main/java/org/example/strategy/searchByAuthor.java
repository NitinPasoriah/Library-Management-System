package org.example.strategy;

import org.example.Book;

import java.util.List;
import java.util.stream.Collectors;

public class searchByAuthor implements searchBook {
    @Override
    public List<Book> search(String query, List<Book> bookList) {
        return bookList.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(query))
                .collect(Collectors.toList());
    }
}
