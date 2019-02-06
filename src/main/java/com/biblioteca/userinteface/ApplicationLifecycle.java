package com.biblioteca.userinteface;

import com.biblioteca.library.Library;

import java.io.IOException;
import java.util.Collections;

import static com.biblioteca.library.Library.library;

// Represents the progression of a system
public class ApplicationLifecycle {

    private static final String GREETING_MESSAGE = "Hello user!\nWelcome to the Biblioteca Application.";

    private final ApplicationIO appIO;
    private final Library library;

    public ApplicationLifecycle(ApplicationIO appIO) {
        this.appIO = appIO;
        this.library = library(Collections.emptyList());
    }

    public ApplicationLifecycle(ApplicationIO appIO, Library library) {
        this.appIO = appIO;
        this.library = library;
    }

    public void greet() {
        try {
            appIO.print(GREETING_MESSAGE);
        } catch (IOException ioException) {
            throw new NoOutputDeviceException();
        }
    }

    public void listBooks() {
        try {
            appIO.print(
                    library.bookList()
            );
        } catch (IOException ioException) {
            throw new NoOutputDeviceException();
        }
    }
}
