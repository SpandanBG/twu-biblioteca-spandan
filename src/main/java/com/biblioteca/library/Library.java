package com.biblioteca.library;

import com.biblioteca.utils.Options;

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

    public void forBookByPattern(String bookName, Consumer<? super Book> action) {
        books.forEach(book -> {
            if (book.name().toLowerCase().contains(bookName.toLowerCase())) {
                action.accept(book);
            }
        });
    }

    public void addBookToOption(Integer index, Options options, Book book) {
        String option = index.toString();
        String description = BookTemplates.INFORMAL.view(book);
        final Book bookAsOption = book;
        options.addOption(option, description, () -> this.checkoutBook(bookAsOption));
    }

    private void checkoutBook(Book book) {}
}
