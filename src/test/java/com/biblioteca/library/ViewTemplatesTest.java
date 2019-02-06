package com.biblioteca.library;

import org.junit.jupiter.api.Test;

import static com.biblioteca.library.Book.book;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewTemplatesTest {

    @Test
    void expectsInformalViewFormatOfLibrary() {
        Book book = book("Hunger Games", "Suzanne Collins", 2008);

        String expectedString = "Hunger Games\n" +
                "\t- Suzanne Collins (2008)\n";

        assertEquals(expectedString, ViewTemplates.INFORMAL.view(book));
    }

}