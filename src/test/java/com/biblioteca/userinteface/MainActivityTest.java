package com.biblioteca.userinteface;

import com.biblioteca.activities.MainActivity;
import com.biblioteca.library.Library;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;

import static com.biblioteca.library.Book.*;
import static com.biblioteca.library.Library.*;
import static java.util.Collections.EMPTY_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MainActivityTest {

    @Test
    void expectsApplicationToGreetOnStartUp() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        MainActivity activity = new MainActivity(appIO, library(EMPTY_LIST));

        when(appIO.read()).thenReturn("exit");
        activity.run();

        verify(appIO).print("Hello user!\nWelcome to the Biblioteca Library Application.\n");
    }

    @Test
    void expectsApplicationToDisplayMenuAfterGreeting() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        MainActivity activity = new MainActivity(appIO, library(EMPTY_LIST));

        String expectedMenu = "\t\tCommand Menu\n" +
                "\texit - Exit application\n" +
                "\tlist - List books\n" +
                "Command >> ";
        when(appIO.read()).thenReturn("exit");
        activity.run();

        verify(appIO, times(3)).print(captor.capture());
        assertEquals(expectedMenu, captor.getAllValues().get(1));
    }

    @Test
    void expectsListOfBooksWhenListBookOptionIsSelected() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        Library library = library(Collections.singletonList(
                book("Hunger Games", "Suzanne Collins", 2008)
        ));
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        MainActivity activity = new MainActivity(appIO, library);

        String expectedList = "Available books:\n" +
                "1) Hunger Games\n\t- Suzzane Collins (2008)\n";
        when(appIO.read()).thenReturn("list").thenReturn("exit");
        activity.run();

        verify(appIO, times(5)).print(captor.capture());
        assertEquals(expectedList, captor.getAllValues().get(2));
    }

    @Test
    void expectsAGoodByeMessageOnExit() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        MainActivity activity = new MainActivity(appIO, library(EMPTY_LIST));

        String expectedMessage = "\tBye";
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
                book("Hunger Games", "Suzzane Collins", 2008),
                book("Fever Code", "James Dashner", 2009)
        ));
        MainActivity activity = new MainActivity(appIO, library);

        String expectedList = "Available books:\n" +
                "1) Hunger Games\n\t- Suzzane Collins (2008)\n" +
                "2) Fever Code\n\t- James Dashner (2009)\n";
        when(appIO.read()).thenReturn("list").thenReturn("exit");
        activity.run();

        verify(appIO, times(5)).print(captor.capture());
        assertEquals(expectedList, captor.getAllValues().get(2));
    }
}