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

    private static final String START_UP_MESSAGE = "Hello user!\nWelcome to the Biblioteca Application.\n";
    private static final String MENU = "\n\t\tMenu\n\t1) List Books\n\t0) Quit\nChoice: ";
    private static final String EXIT_MESSAGE = "Bye";

    private static final String LIST_BOOK_OPTION = "1";
    private static final String QUIT_OPTION = "0";
    private static final String INVALID_INPUT_STRING = "Unknown option!\n";

    private Library library;

    public MainActivity(ApplicationIO appIO) {
        super(appIO);
    }

    @Override
    public void onStart() {
        library = library(Collections.singletonList(
                book("Hunger Games", "Suzanne Collins", 2008)
        ));
        appIO.print(START_UP_MESSAGE);
    }

    @Override
    public void onRunning() {
        menu();
    }

    @Override
    public void onExit() {
        appIO.print(EXIT_MESSAGE);
    }

    private void menu() {
        String option;
        do {
            appIO.print(MENU);
            option = appIO.read();
            actOnOption(option);
        } while (!option.equals(QUIT_OPTION));
    }

    private void actOnOption(String option) {
        switch (option) {
            case QUIT_OPTION:
                break;
            case LIST_BOOK_OPTION:
                showBooks();
                break;
            default:
                appIO.print(INVALID_INPUT_STRING);
        }
    }

    private void showBooks() {
        StringBuilder builder = new StringBuilder();
        int index = 1;
        library.forEachBook(book -> {
            builder.append(index).append(") ").append(ViewTemplates.INFORMAL.view(book));
        });
        appIO.print("\n\tAvailable Books\n");
        appIO.print(builder.toString());
    }

}
