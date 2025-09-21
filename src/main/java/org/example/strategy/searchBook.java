package org.example.strategy;

import org.example.Book;

import java.util.List;

public interface searchBook {
    List<Book> search(String query, List<Book> bookList);
}
