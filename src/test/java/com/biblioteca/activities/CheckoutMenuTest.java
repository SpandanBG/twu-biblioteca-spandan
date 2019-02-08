package com.biblioteca.activities;

import com.biblioteca.library.Book;
import com.biblioteca.library.Library;
import com.biblioteca.userinteface.ApplicationIO;
import com.biblioteca.utils.Incrementor;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static com.biblioteca.library.Book.book;
import static com.biblioteca.library.Library.library;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CheckoutMenuTest {

    @Test
    void expectsUserInputRequestForBookNameToCheckout() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        Library library = library(singletonList(
                book("One Hundred Years Of Solitude", "Gabriel García Márquez", 1967)
        ));
        CheckoutMenu activity = new CheckoutMenu(appIO, library);

        when(appIO.read()).thenReturn("cancel");
        String expectedString = "\nSearch by name: ";
        activity.run();

        verify(appIO).print(expectedString);
    }

    @Test
    void expectsListOfBooksThatCanBeCheckedOutWhenSearchedByName() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Library library = library(singletonList(
                book("Hunger Games", "Suzzane Collins", 2008)
        ));
        CheckoutMenu activity = new CheckoutMenu(appIO, library);

        when(appIO.read()).thenReturn("Hunger Games");
        String expectedString = "\n\t\tBooks by that name:\n" +
                "\t1 - Hunger Games, by Suzzane Collins (2008)\n" +
                "\tcancel - Cancel checkout\n" +
                "Enter book number [default: cancel]: ";
        activity.run();

        verify(appIO, times(2)).read();
        verify(appIO, times(3)).print(captor.capture());
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
                "\t1 - Hunger Games, by Suzanne Collins (2008)\n" +
                "\t2 - Catching Fire (Hunger Games), by Suzanne Collins (2010)\n" +
                "\tcancel - Cancel checkout\n" +
                "Enter book number [default: cancel]: ";
        activity.run();

        verify(appIO, times(2)).read();
        verify(appIO, times(3)).print(captor.capture());
        assertEquals(expectedString, captor.getAllValues().get(1));
    }

    @Test
    void expectsNoBookInLibraryOnCheckout() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        List<Book> books = singletonList(
                book("Hunger Games", "Suzanne Collins", 2008)
        );
        Library library = library(books);
        CheckoutMenu menu = new CheckoutMenu(appIO, library);

        when(appIO.read()).thenReturn("hunger").thenReturn("1");
        menu.run();

        Incrementor count = new Incrementor(0);
        library.forEachBook(book -> {
            if (books.contains(book)) {
                count.increment(1);
            }
        });

        assertEquals(0, count.value());
    }

    @Test
    void expectsNoBookFoundIfBookDoesNotExistInLibrary() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Library library = library(EMPTY_LIST);
        CheckoutMenu menu = new CheckoutMenu(appIO, library);

        when(appIO.read()).thenReturn("hunger");
        menu.run();

        verify(appIO, times(2)).print(captor.capture());
        assertEquals("\nNo book with that name\n", captor.getAllValues().get(1));
    }

}