package com.biblioteca.library;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.biblioteca.library.Book.book;
import static com.biblioteca.library.Library.library;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibraryTest {

    @Test
    void expectsToLoopOverTheListOfBooksInLibrary() {
        List<Book> books = Arrays.asList(
                book("Hunger Games", "Suzanne Collins", 2008),
                book("Fever Code", "James Dashner", 2016)
        );
        Library library = library(books);

        library.forEachBook(book -> assertTrue(books.contains(book)));
    }

}