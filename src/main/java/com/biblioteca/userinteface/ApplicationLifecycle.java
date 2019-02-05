package com.biblioteca.userinteface;

import java.io.IOException;

// Represents the progression of a system
public class ApplicationLifecycle {

    private static final String GREETING_MESSAGE = "Hello user!\nWelcome to the Biblioteca Application.";

    private final ApplicationIO appIO;

    public ApplicationLifecycle(ApplicationIO appIO) {
        this.appIO = appIO;
    }

    public void greet() {
        try {
            appIO.print(GREETING_MESSAGE);
        } catch (IOException ioException) {
            throw new NoOutputDeviceException();
        }
    }
}
