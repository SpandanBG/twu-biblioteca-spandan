package com.biblioteca.controller.menus;

import com.biblioteca.model.library.Library;
import com.biblioteca.view.ApplicationIO;

// Represents a system that starts off an application
public class Launcher {

    private static final String START_UP_MESSAGE = "Hello user!\nWelcome to the Biblioteca Application.\n";

    private final ApplicationIO appIO;
    private final Library library;

    public Launcher(ApplicationIO appIO, Library library) {
        this.appIO = appIO;
        this.library = library;
    }


    public void run() {
        greet();
        launchMainMenu();
    }

    private void greet() {
        appIO.print(START_UP_MESSAGE);
    }

    private void launchMainMenu() {
        MainMenu mainMenu = new MainMenu(appIO, library);
        mainMenu.run();
    }
}
