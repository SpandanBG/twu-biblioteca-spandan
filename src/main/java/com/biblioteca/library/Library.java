package com.biblioteca.library;

import com.biblioteca.utils.Options;

import java.util.ArrayList;
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

    public Library filterBookByName(String bookName) {
        List<Book> selectedBook = new ArrayList<>();
        books.forEach(book -> {
            if(book.name().toLowerCase().contains(bookName.toLowerCase())) {
                selectedBook.add(book);
            }
        });
        return new Library(selectedBook);
    }

    public void forEachBook(Consumer<? super Book> action) {
        for (Book book : books) {
            action.accept(book);
        }
    }

    public void checkoutBook(Book book) {}
}
