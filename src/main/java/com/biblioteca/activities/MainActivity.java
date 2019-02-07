package com.biblioteca.activities;

import com.biblioteca.library.Library;
import com.biblioteca.library.LibraryTemplates;
import com.biblioteca.userinteface.ApplicationIO;
import com.biblioteca.utils.Menu;
import com.biblioteca.utils.MenuTemplates;

// Represents the beginning of the system
public class MainActivity {

    private static final String START_UP_MESSAGE = "Hello user!\nWelcome to the Biblioteca Library Application.\n";
    public static final String LIST_BOOK_HEADER = "Available books:\n";

    private final ApplicationIO appIO;
    private final Library library;
    private final Menu menu;

    private boolean toExit;

    public MainActivity(ApplicationIO appIO, Library library) {
        this.appIO = appIO;
        this.library = library;
        this.menu = new Menu();
        this.toExit = false;
        setupMenu();
    }

    public void run() {
        greet();
        showMenu();
    }

    private void setupMenu() {
        menu.addOption("exit", "Exit application", this::exit);
        menu.addOption("list", "List books", this::listBook);
    }

    private void showMenu() {
        String menuString = MenuTemplates.COMMAND_VIEW.view(menu);
        while (!toExit) {
            appIO.print(menuString);
            String option = appIO.read();
            menu.selectOrDefault(option, () -> appIO.print("Unknown option!\n"));
        }
    }

    private void greet() {
        appIO.print(START_UP_MESSAGE);
    }

    private void listBook() {
        StringBuilder builder = new StringBuilder();
        builder.append(LIST_BOOK_HEADER);
        builder.append(LibraryTemplates.INFORMAL.view(library));
        appIO.print(builder.toString());
    }

    private void exit() {
        this.toExit = true;
        appIO.print("\tBye");
    }
}
