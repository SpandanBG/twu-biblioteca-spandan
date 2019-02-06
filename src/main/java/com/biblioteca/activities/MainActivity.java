package com.biblioteca.activities;

import com.biblioteca.userinteface.ActivityLifecycle;
import com.biblioteca.userinteface.ApplicationIO;

// Represents the beginning of the system
public class MainActivity extends ActivityLifecycle {

    private static final String START_UP_MESSAGE = "Hello user!\nWelcome to the Biblioteca Application.\n";
    private static final String MENU = "\n\t\tMenu\n\t1) List Books\n\t0) Quit\nChoice: ";
    private static final String EXIT_MESSAGE = "Bye";

    private static final String LIST_BOOK_OPTION = "1";
    private static final String QUIT_OPTION = "0";
    private static final String INVALID_INPUT_STRING = "Unknown option!\n";


    public MainActivity(ApplicationIO appIO) {
        super(appIO);
    }

    @Override
    public void onStart() {
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
                break;
            default:
                appIO.print(INVALID_INPUT_STRING);
        }
    }

}
