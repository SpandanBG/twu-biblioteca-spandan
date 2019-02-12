package com.biblioteca.library;

import com.biblioteca.utils.Incrementor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Represents a collection of availableBooks or periodicals
public class Library {

    private final List<Book> availableBooks;
    private final List<Book> unavailableBooks;

    private Library(List<Book> availableBooks) {
        this.availableBooks = new ArrayList<>(availableBooks);
        this.unavailableBooks = new ArrayList<>();
    }

    public static Library library(List<Book> books) {
        return new Library(books);
    }

    public void forEachBook(Consumer<? super Book> action) {
        for (Book book : availableBooks) {
            action.accept(book);
        }
    }

    public void forEachBook(String byName, Consumer<? super Book> action) {
        for (Book book : availableBooks) {
            if (book.name().toLowerCase().contains(byName.toLowerCase())) {
                action.accept(book);
            }
        }
    }

    public void checkOutBook(Book book) {
        if (!availableBooks.remove(book)) {
            throw new BookNotAvailableException();
        }
        unavailableBooks.add(book);
    }

    public boolean isEmpty() {
        return availableBooks.size() == 0;
    }

    public boolean hasBooks(String byName) {
        Incrementor count = new Incrementor(0);
        forEachBook(byName, book -> count.increment(1));
        return count.value() > 0;
    }

    public Library borrowedBooks() {
        return new Library(unavailableBooks);
    }

    public void checkInBook(Book book) {
        if (!unavailableBooks.remove(book)) {
            throw new BookNotFoundException();
        }
        availableBooks.add(book);
    }
}
