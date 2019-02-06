package com.biblioteca.library;

import java.util.List;
import java.util.function.Consumer;

// Represents a collection of books or periodicals
public class Library {

    private final List<Book> books;

    private Library(List<Book> books) {
        this.books = books;
    }

    public static Library library(List<Book> books) {
        return new Library(books);
    }

    public void forEachBook(Consumer<? super Book> action) {
        for (Book book : books) {
            action.accept(book);
        }
    }
}
