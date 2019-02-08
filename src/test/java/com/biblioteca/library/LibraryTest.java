package com.biblioteca.library;

import com.biblioteca.utils.Incrementor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.biblioteca.library.Book.book;
import static com.biblioteca.library.Library.library;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void expectsLibraryOfBooksWithNamePatternMatched() {
        List<Book> books = Arrays.asList(
                book("Hunger Games", "Suzanne Collins", 2008),
                book("Catching Fire (Hunger Games)", "Suzanne Collins", 2010),
                book("Fever Code", "James Dashner", 2016)
        );
        Library library = library(books);

        Library selectedLibrary = library.filterBookByName("hUNGER gAMES");

        Incrementor count = new Incrementor(1);
        selectedLibrary.forEachBook(book -> {
            assertTrue(books.contains(book));
            count.increment(1);
        });

        assertEquals(3, count.value());
    }

    @Test
    void expectsABookToBeCheckoutSuccessfully() {
        Book book = book("Some book", "A author", 2020);
        Library library = library(singletonList(book));

        assertDoesNotThrow(() -> library.checkoutBook(book));
    }

    @Test
    void expectsBookNotAvailableIfBookNotInLibrary() {
        Book book = book("Some book", "A author", 2020);
        Library library = library(EMPTY_LIST);

        assertThrows(BookNotAvailableException.class, () -> library.checkoutBook(book));
    }

}