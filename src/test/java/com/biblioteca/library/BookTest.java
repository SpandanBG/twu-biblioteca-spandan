package com.biblioteca.library;

import org.junit.jupiter.api.Test;

import static com.biblioteca.library.Book.book;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    @Test
    void expectsHungerGamesAsTheNameOfTheBook() {
        Book aBook = book("Hunger Games");

        assertEquals("Hunger Games", aBook.toString());
    }

    @Test
    void expectsMazeRunnerAsTheNameOfTheBook() {
        Book aBook = book("Maze Runner");

        assertEquals("Maze Runner", aBook.toString());
    }
}