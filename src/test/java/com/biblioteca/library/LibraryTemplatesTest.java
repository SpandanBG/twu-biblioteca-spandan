package com.biblioteca.library;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.biblioteca.library.Book.book;
import static com.biblioteca.library.Library.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LibraryTemplatesTest {

    @Test
    void expectsInformalViewFormatOfLibrary() {
        Library library = library(Collections.singletonList(
                book("Hunger Games", "Suzanne Collins", 2008)
        ));

        String expectedString = "\t1 - Hunger Games, by Suzanne Collins (2008)\n";

        assertEquals(expectedString, LibraryTemplates.INFORMAL_LIST_VIEW.view(library));
    }

}