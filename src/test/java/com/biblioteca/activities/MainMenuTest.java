package com.biblioteca.activities;

import com.biblioteca.library.Library;
import com.biblioteca.userinteface.ApplicationIO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;

import static com.biblioteca.library.Book.book;
import static com.biblioteca.library.Library.library;
import static java.util.Collections.EMPTY_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MainMenuTest {

    @Test
    void expectsToDisplayMainMenu() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        MainMenu activity = new MainMenu(appIO, library(EMPTY_LIST));

        String expectedMenu = "\n\t\tAvailable commands:\n" +
                "\tlist - List books\n" +
                "\tcheckout - Checkout book\n" +
                "\treturn - Return book\n" +
                "\thelp - Show menu\n" +
                "\texit - Exit application\n";
        when(appIO.read()).thenReturn("exit");
        activity.run();

        verify(appIO, times(3)).print(captor.capture());
        assertEquals(expectedMenu, captor.getAllValues().get(0));
    }

    @Test
    void expectsListOfBooksWhenListBookOptionIsSelected() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        Library library = library(Collections.singletonList(
                book("Hunger Games", "Suzanne Collins", 2008)
        ));
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        MainMenu activity = new MainMenu(appIO, library);

        String expectedList = "\nAvailable books:\n" +
                "1 - Hunger Games, by Suzanne Collins (2008)\n";
        when(appIO.read()).thenReturn("list").thenReturn("exit");
        activity.run();

        verify(appIO, times(5)).print(captor.capture());
        assertEquals(expectedList, captor.getAllValues().get(2));
    }

    @Test
    void expectsAGoodByeMessageOnExit() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        MainMenu activity = new MainMenu(appIO, library(EMPTY_LIST));

        String expectedMessage = "\nGoodbye user. There is always something more to read.\n";
        when(appIO.read()).thenReturn("exit");
        activity.run();

        verify(appIO, times(3)).print(captor.capture());
        assertEquals(expectedMessage, captor.getAllValues().get(2));
    }

    @Test
    void expectsListOFBooksWhenLibraryHasTwoBooks() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Library library = library(Arrays.asList(
                book("Hunger Games", "Suzanne Collins", 2008),
                book("Fever Code", "James Dashner", 2009)
        ));
        MainMenu activity = new MainMenu(appIO, library);

        String expectedList = "\nAvailable books:\n" +
                "1 - Hunger Games, by Suzanne Collins (2008)\n" +
                "2 - Fever Code, by James Dashner (2009)\n";
        when(appIO.read()).thenReturn("list").thenReturn("exit");
        activity.run();

        verify(appIO, times(5)).print(captor.capture());
        assertEquals(expectedList, captor.getAllValues().get(2));
    }

    @Test
    void expectsNoBooksAvailableWhenLibraryIsEmptyOnListBookOptionSelected() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        MainMenu activity = new MainMenu(appIO, library(EMPTY_LIST));

        String expectedList = "\nNo books available.\n";
        when(appIO.read()).thenReturn("list").thenReturn("exit");
        activity.run();

        verify(appIO, times(5)).print(captor.capture());
        assertEquals(expectedList, captor.getAllValues().get(2));
    }
}