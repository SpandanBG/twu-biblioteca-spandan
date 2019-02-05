package com.biblioteca;

import com.biblioteca.userinteface.ApplicationIO;
import com.biblioteca.userinteface.ApplicationLifecycle;

public class BibliotecaApp {
    public static void main(String...args) {
        ApplicationIO appIO = ApplicationIO.createConsoleIO();
        ApplicationLifecycle app = new ApplicationLifecycle(appIO);

        app.greet();
    }
}
