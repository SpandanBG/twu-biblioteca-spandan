package com.biblioteca.library;

import com.biblioteca.utils.Incrementor;
import com.biblioteca.utils.OptionTemplates;
import com.biblioteca.utils.Options;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.biblioteca.library.Book.book;
import static com.biblioteca.library.Library.library;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void expectsToLoopOverTheListOfBooksWithMatchingNamePatternInLibrary() {
        List<Book> books = Arrays.asList(
                book("Catching Fire (Hunger Games)", "Suzanne Collins", 2010),
                book("Fever Code", "James Dashner", 2016)
        );
        Library library = library(books);

        library.forBookByPattern("hUNGER gAMES", (book) -> {
            assertEquals("Catching Fire (Hunger Games)", book.name());
        });
    }

    @Test
    void expectsToAddBookAsAnOptionToOptions() {
        List<Book> books = Arrays.asList(
                book("Hunger Games", "Suzanne Collins", 2008),
                book("Catching Fire (Hunger Games)", "Suzanne Collins", 2010),
                book("Fever Code", "James Dashner", 2016)
        );
        Library library = library(books);
        Options options = new Options();
        Incrementor index = new Incrementor(1);

        options.setPrefix("Books to choose from");
        options.setSuffix("Enter serial number of book");

        String expectedString = "\n\t\tBooks to choose from:\n" +
                "\t1 - Hunger Games, by Suzanne Collins (2008)\n" +
                "\t2 - Catching Fire (Hunger Games), by Suzanne Collins (2010)\n" +
                "\t3 - Fever Code, by James Dashner (2016)\n" +
                "Enter serial number of book: ";

        library.forEachBook(book -> {
            library.addBookToOption(index.value(), options, book);
            index.increment(1);
        });

        assertEquals(expectedString, OptionTemplates.CUSTOM_VIEW.view(options));
    }

}