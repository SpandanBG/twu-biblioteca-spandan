package com.biblioteca.controller.menus;

import com.biblioteca.model.library.Library;
import com.biblioteca.view.ApplicationIO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;

import static com.biblioteca.model.library.Book.book;
import static com.biblioteca.model.library.Library.library;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ListBooksTest {

    @Test
    void expectsListOfBooksWithOneBookInLibrary() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Library library = library(Collections.singletonList(
                book("Hunger Games", "Suzanne Collins", 2008)
        ));
        ListBooks listBooks = new ListBooks(appIO, library);

        String expectedHeader = "\n\t\tAvailable books:\n";
        String expectedList = "\t1 - Hunger Games, by Suzanne Collins (2008)\n";
        listBooks.run();

        verify(appIO, times(2)).print(captor.capture());
        assertEquals(expectedHeader, captor.getAllValues().get(0));
        assertEquals(expectedList, captor.getAllValues().get(1));
    }

    @Test
    void expectsListOfBooksWithTwoBookInLibrary() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Library library = library(Arrays.asList(
                book("Hunger Games", "Suzanne Collins", 2008),
                book("Fever Code", "James Dashner", 2009)
        ));
        ListBooks listBooks = new ListBooks(appIO, library);

        String expectedHeader = "\n\t\tAvailable books:\n";
        String expectedList = "\t1 - Hunger Games, by Suzanne Collins (2008)\n" +
                "\t2 - Fever Code, by James Dashner (2009)\n";
        listBooks.run();

        verify(appIO, times(2)).print(captor.capture());
        assertEquals(expectedHeader, captor.getAllValues().get(0));
        assertEquals(expectedList, captor.getAllValues().get(1));
    }

    @Test
    void expectsNoBookAvailableWhenNoBookInLibrary() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Library library = mock(Library.class);
        ListBooks listBooks = new ListBooks(appIO, library);

        when(library.isEmpty()).thenReturn(true);
        String expectedHeader = "\nNo books available.\n";
        listBooks.run();

        verify(appIO, times(1)).print(captor.capture());
        assertEquals(expectedHeader, captor.getAllValues().get(0));
    }
}