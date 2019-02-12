package com.biblioteca.model;

import com.biblioteca.model.library.Book;
import org.junit.jupiter.api.Test;

import static com.biblioteca.model.library.Book.*;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void expectsNameOfTheBook() {
        Book book = book("Hunger Games", "Suzanne Collins", 2008);

        assertEquals("Hunger Games", book.name());
    }

    @Test
    void expectsAuthorOfTheBook() {
        Book book = book("Hunger Games", "Suzanne Collins", 2008);

        assertEquals("Suzanne Collins", book.author());
    }

    @Test
    void expectsYearOfTheBook() {
        Book book = book("Hunger Games", "Suzanne Collins", 2008);

        assertEquals("2008", book.year());
    }
}