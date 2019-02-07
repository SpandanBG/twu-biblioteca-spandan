package com.biblioteca.activities;

import com.biblioteca.library.Library;
import com.biblioteca.userinteface.ApplicationIO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;

import static com.biblioteca.library.Book.book;
import static com.biblioteca.library.Library.library;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CheckoutMenuTest {

    @Test
    void expectsUserInputRequestForBookNameToCheckout() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        Library library = library(Collections.singletonList(
                book("One Hundred Years Of Solitude", "Gabriel García Márquez", 1967)
        ));
        CheckoutMenu activity = new CheckoutMenu(appIO, library);

        String expectedString = "\nSearch by name: ";
        activity.run();

        verify(appIO).print(expectedString);
    }

    @Test
    void expectsListOfBooksThatCanBeCheckedOutWhenSearchedByName() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Library library = library(Collections.singletonList(
                book("Hunger Games", "Suzzane Collins", 2008)
        ));
        CheckoutMenu activity = new CheckoutMenu(appIO, library);

        when(appIO.read()).thenReturn("Hunger Games");
        String expectedString = "\n\tBooks by that name:\n" +
                "1 - Hunger Games, by Suzzane Collins (2008)\n" +
                "cancel - Cancel checkout\n" +
                "Enter book number [default: cancel]: ";
        activity.run();

        verify(appIO).read();
        verify(appIO, times(2)).print(captor.capture());
        assertEquals(expectedString, captor.getAllValues().get(1));
    }

    @Test
    void expectsTwoBooksThatCanBeCheckedOUtWhenSearchedByName() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Library library = library(Arrays.asList(
                book("Hunger Games", "Suzanne Collins", 2008),
                book("Catching Fire (Hunger Games)", "Suzanne Collins", 2010)
        ));
        CheckoutMenu activity = new CheckoutMenu(appIO, library);

        when(appIO.read()).thenReturn("Hunger Games");
        String expectedString = "\n\t\tBooks by that name:\n" +
                "\tcancel - Cancel checkout\n" +
                "\t1 - Hunger Games, by Suzanne Collins (2008)\n" +
                "\t2 - Catching Fire (Hunger Games), by Suzanne Collins (2010)\n" +
                "Enter book number [default: cancel]: ";
        activity.run();

        verify(appIO).read();
        verify(appIO, times(2)).print(captor.capture());
        assertEquals(expectedString, captor.getAllValues().get(1));
    }

}