package com.biblioteca.activities;

import com.biblioteca.library.Book;
import com.biblioteca.library.Library;
import com.biblioteca.userinteface.ApplicationIO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static com.biblioteca.library.Book.book;
import static com.biblioteca.library.Library.library;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReturnMenuTest {

    @Test
    void expectsToListOneBorrowedBooksToBeReturned() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        List<Book> books = Arrays.asList(
                book("Digital Fortress", "Dan Brown", 1998),
                book("War and Peace", "Leo Tolstoy", 1865)
        );
        Library library = library(books);
        ReturnMenu menu = new ReturnMenu(appIO, library);

        when(appIO.read()).thenReturn("cancel");
        library.checkoutBook(books.get(1));
        String expectedString = "\n\t\tBorrowed books:\n" +
                "\t1 - War and Peace, by Leo Tolstoy (1865)\n" +
                "\tcancel - Cancel return\n" +
                "Enter book number [default: cancel]: ";
        menu.run();

        verify(appIO, times(1)).print(captor.capture());
        assertEquals(expectedString, captor.getAllValues().get(0));
    }

    @Test
    void expectsToListTwoBorrowedBooksToBeReturned() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        List<Book> books = Arrays.asList(
                book("Digital Fortress", "Dan Brown", 1998),
                book("War and Peace", "Leo Tolstoy", 1865)
        );
        Library library = library(books);
        ReturnMenu menu = new ReturnMenu(appIO, library);

        library.checkoutBook(books.get(0));
        library.checkoutBook(books.get(1));
        when(appIO.read()).thenReturn("cancel");
        String expectedString = "\n\t\tBorrowed books:\n" +
                "\t1 - Digital Fortress, by Dan Brown (1998)\n" +
                "\t2 - War and Peace, by Leo Tolstoy (1865)\n" +
                "\tcancel - Cancel return\n" +
                "Enter book number [default: cancel]: ";
        menu.run();

        verify(appIO, times(1)).print(captor.capture());
        assertEquals(expectedString, captor.getAllValues().get(0));
    }

    @Test
    void expectsNoBooksBorrowedMessageWhenNonBorrowed() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        List<Book> books = singletonList(book("Glass Sword", "Victoria Aveyard", 2016));
        Library library = library(books);
        ReturnMenu menu = new ReturnMenu(appIO, library);

        menu.run();

        verify(appIO).print("\nNo books borrowed\n");
    }

    @Test
    void expectsABookToBeReturnedOnBookReturned() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Book book = book("Glass Sword", "Victoria Aveyard", 2016);
        Library library = mock(Library.class);
        ReturnMenu menu = new ReturnMenu(appIO, library);

        when(library.borrowedBooks()).thenReturn(library(
                singletonList(book)
        ));
        when(appIO.read()).thenReturn("1");
        menu.run();

        verify(library).returnBook(book);
    }

}