package com.biblioteca.activities;

import com.biblioteca.library.Library;
import com.biblioteca.library.LibraryTemplates;
import com.biblioteca.userinteface.ApplicationIO;
import com.biblioteca.utils.OptionTemplates;
import com.biblioteca.utils.Options;

// Represents the beginning of the system
public class MainMenu {

    private static final String START_UP_MESSAGE = "Hello user!\nWelcome to the Biblioteca Library Application.\n";
    private static final String LIST_BOOK_HEADER = "\nAvailable books:\n";
    private static final String NO_BOOKS_AVAILABLE = "\nNo books available.\n";

    private final ApplicationIO appIO;
    private final Library library;
    private final Options options;

    private boolean toExit;

    public MainMenu(ApplicationIO appIO, Library library) {
        this.appIO = appIO;
        this.library = library;
        this.options = new Options();
        this.toExit = false;
        setupMenu();
    }

    public void run() {
        greet();
        showMenu();
    }

    private void setupMenu() {
        options.addOption("list", "List books", this::listBook);
        options.addOption("checkout", "Checkout book", this::launchCheckoutMenu);
        options.addOption("return", "Return book", this::launchReturnMenu);
        options.addOption("exit", "Exit application", this::exit);
    }

    private void launchReturnMenu() {
        ReturnMenu returnMenu = new ReturnMenu(appIO, library);
        returnMenu.run();
    }

    private void showMenu() {
        String menuString = OptionTemplates.COMMAND_VIEW.view(options);
        while (!toExit) {
            appIO.print(menuString);
            String option = appIO.read();
            options.selectOrDefault(option, () -> appIO.print("Unknown option!\n"));
        }
    }

    private void greet() {
        appIO.print(START_UP_MESSAGE);
    }

    private void listBook() {
        String libraryView = LibraryTemplates.INFORMAL_LIST_VIEW.view(library);
        if (libraryView.equals("")) {
            appIO.print(NO_BOOKS_AVAILABLE);
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(LIST_BOOK_HEADER);
        builder.append(libraryView);
        appIO.print(builder.toString());
    }

    private void launchCheckoutMenu() {
        CheckoutMenu activity = new CheckoutMenu(appIO, library);
        activity.run();
    }

    private void exit() {
        this.toExit = true;
        appIO.print("\tBye");
    }
}
