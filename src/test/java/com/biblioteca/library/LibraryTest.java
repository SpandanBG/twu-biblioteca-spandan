package com.biblioteca.library;

import com.biblioteca.utils.Incrementor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.biblioteca.library.Book.book;
import static com.biblioteca.library.Library.library;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
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

    @Test
    void expectsLibraryToBeEmpty() {
        Library library = library(EMPTY_LIST);

        assertTrue(library.isEmpty());
    }

    @Test
    void expectsLibraryToBeNotEmpty() {
        Book book = book("Some book", "A author", 2020);
        Library library = library(singletonList(book));

        assertFalse(library.isEmpty());
    }

    @Test
    void expectsLibraryToReturnBorrowedBook() {
        Book book = book("Some book", "A author", 2020);
        Library library = library(singletonList(book));

        library.checkoutBook(book);

        assertDoesNotThrow(() -> library.returnBook(book));
    }

    @Test
    void expectsLibraryToNotReturnUnBorrowedBook() {
        Book book = book("Some book", "A author", 2020);
        Library library = library(singletonList(book));

        assertThrows(InvalidBookException.class, () -> library.returnBook(book));
    }

    @Test
    void expectsBooksWithHungerInTheName() {
        Library library = library(Arrays.asList(
                book("Hunger Games", "Suzanne Collins", 2008),
                book("Fever Code", "James Dashner", 2009)
        ));

        Incrementor count = new Incrementor(0);
        library.forEachBook("hunger", book -> count.increment(1));

        assertEquals(1, count.value());
    }

    @Test
    void expectsLibraryToHaveBooksByNameHunger() {
        Library library = library(Arrays.asList(
                book("Hunger Games", "Suzanne Collins", 2008),
                book("Fever Code", "James Dashner", 2009)
        ));

        assertTrue(library.hasAvailableBooks("hUNGER"));
    }

    @Test
    void expectsLibraryToHaveItsBookBorrowed() {
        Book book = book("Fever Code", "James Dashner", 2009);
        Library library = library(Collections.singletonList(book));

        library.checkoutBook(book);

        assertTrue(library.hasUnavailableBooks());
    }

    @Test
    void expectsToIterateThroughUnavailableBooks() {
        List<Book> books = Arrays.asList(
                book("Hunger Games", "Suzanne Collins", 2008),
                book("Fever Code", "James Dashner", 2009)
        );
        Library library = library(books);
        Incrementor count = new Incrementor(0);

        library.checkoutBook(books.get(0));
        library.checkoutBook(books.get(1));
        library.forEachUnavailable(book -> count.increment(1));

        assertEquals(2, count.value());
    }
}