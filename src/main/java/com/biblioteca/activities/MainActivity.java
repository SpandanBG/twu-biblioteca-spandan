package com.biblioteca.activities;

import com.biblioteca.library.Library;
import com.biblioteca.library.ViewTemplates;
import com.biblioteca.userinteface.ActivityLifecycle;
import com.biblioteca.userinteface.ApplicationIO;

import java.util.Collections;

import static com.biblioteca.library.Book.book;
import static com.biblioteca.library.Library.library;

// Represents the beginning of the system
public class MainActivity extends ActivityLifecycle {

    private static final String GREETING_MESSAGE = "Hello user!\nWelcome to the Biblioteca Application.\n";
    private static final String LIST_BOOK_HEADER = "\n\tAvailable books\n";
    private static final String INDEX_SEPARATOR = ") ";

    private Library library;

    public MainActivity(ApplicationIO appIO) {
        super(appIO);
    }

    @Override
    public void onCreate() {
        library = library(Collections.singletonList(
                book("Hunger Games", "Suzanne Collins", 2008)
        ));
    }

    @Override
    public void onRunning() {
        greet();
        listBooks();
    }

    @Override
    public void onExit() {

    }

    private void greet() {
        appIO.print(GREETING_MESSAGE);
    }

    private void listBooks() {
        appIO.print(LIST_BOOK_HEADER);
        int index = 1;
        library.forEachBook(book -> appIO.print(
                index + INDEX_SEPARATOR + ViewTemplates.INFORMAL.view(book)
        ));
    }
}
