package com.biblioteca.library;

import java.util.List;

// Represents a collection of books or periodicals
public class Library {

    public static final String NEW_LINE = "\n";
    private static final String BOOK_LIST_PREFIX = "List of available books\n";
    private final List<Book> books;

    private Library(List<Book> books) {
        this.books = books;
    }

    public static Library library(List<Book> books) {
        return new Library(books);
    }

    public String bookList() {
        final StringBuilder listString = new StringBuilder(BOOK_LIST_PREFIX);
        books.forEach((book) -> listString.append(book.toString() + NEW_LINE));
        return listString.toString();
    }
}
