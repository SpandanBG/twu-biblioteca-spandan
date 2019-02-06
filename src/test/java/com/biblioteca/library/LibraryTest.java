package com.biblioteca.library;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.biblioteca.library.Book.book;
import static com.biblioteca.library.Library.library;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LibraryTest {

    @Test
    void expectsHarryPotterAsAListOfBooks() {
        Library library = library(Arrays.asList(
                book("Harry Potter")
        ));

        String expectedList =
                "List of available books\n" +
                "Harry Potter\n";

        assertEquals(expectedList, library.bookList());
    }

    @Test
    void expectsGodfatherAndLordOfTheRingsAsListOfBooks() {
        Library library = library(Arrays.asList(
                book("Godfather"),
                book("Lord Of The Rings")
        ));

        String expectedList =
                "List of available books\n" +
                "Godfather\n" +
                "Lord Of The Rings\n";

        assertEquals(expectedList, library.bookList());
    }

}