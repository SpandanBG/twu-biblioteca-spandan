package com.biblioteca.activities;

import com.biblioteca.library.Library;
import com.biblioteca.library.LibraryTemplates;
import com.biblioteca.userinteface.ApplicationIO;

// Represents an act to display books available in library
public class ListBooks {

    private static final String LIST_BOOKS_HEADER = "\n\t\tAvailable books:\n";
    private static final String NO_BOOKS_MESSAGE = "\nNo books available.\n";

    private final ApplicationIO appIO;
    private final Library library;

    ListBooks(ApplicationIO appIO, Library library) {
        this.appIO = appIO;
        this.library = library;
    }

    public void run() {
        if (library.isEmpty()) {
            appIO.print(NO_BOOKS_MESSAGE);
            return;
        }
        showAvailableBooks();
    }

    private void showAvailableBooks() {
        appIO.print(LIST_BOOKS_HEADER);
        appIO.print(LibraryTemplates.INFORMAL_LIST_VIEW.view(library));
    }
}
