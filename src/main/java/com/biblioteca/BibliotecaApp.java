package com.biblioteca;

import com.biblioteca.userinteface.ApplicationIO;
import com.biblioteca.activities.MainActivity;

public class BibliotecaApp {
    public static void main(String...args) {
        ApplicationIO appIO = ApplicationIO.createConsoleIO();
        MainActivity mainActivity = new MainActivity(appIO);
        mainActivity.run();
    }
}
