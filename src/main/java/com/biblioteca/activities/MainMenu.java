package com.biblioteca.activities;

import com.biblioteca.library.Library;
import com.biblioteca.userinteface.ApplicationIO;
import com.biblioteca.utils.OptionTemplates;
import com.biblioteca.utils.Options;

// Represents the first set of options available to an user
public class MainMenu {

    private static final String MENU_HEADER = "Available commands:";
    private static final String MENU_FOOTER = "";
    private static final String INPUT_PROMOTER = ">> ";
    private static final String INVALID_INPUT_MESSAGE = "Unknown option!\n";
    private static final String EXIT_MESSAGE = "\nGoodbye user. There is always something more to read.\n";

    private final ApplicationIO appIO;
    private final Library library;
    private final Options options;

    private boolean toExit;

    MainMenu(ApplicationIO appIO, Library library) {
        this.appIO = appIO;
        this.library = library;
        this.options = new Options();
        this.toExit = false;
    }

    public void run() {
        setupMenuOptions();
        displayMenu();
        while (!toExit) {
            interactWithUser();
        }
    }

    private void setupMenuOptions() {
        options.addOption("list", "List books", this::launchListBooks);
        options.addOption("checkout", "Checkout book", this::launchCheckoutMenu);
        options.addOption("return", "Return book", this::launchReturnMenu);
        options.addOption("help", "Show menu", this::displayMenu);
        options.addOption("exit", "Exit application", this::exit);
        options.setPrefix(MENU_HEADER);
        options.setSuffix(MENU_FOOTER);
    }

    private void displayMenu() {
        String menuString = OptionTemplates.CUSTOM_VIEW.view(options);
        appIO.print(menuString);
    }

    private void interactWithUser() {
        appIO.print(INPUT_PROMOTER);
        String option = appIO.read();
        options.selectOrDefault(option, this::invalidOption);
    }

    private void launchListBooks() {
        ListBooks listBooks = new ListBooks(appIO, library);
        listBooks.run();
    }

    private void launchCheckoutMenu() {
        CheckoutMenu checkoutMenu = new CheckoutMenu(appIO, library);
        checkoutMenu.run();
    }

    private void launchReturnMenu() {
        ReturnMenu returnMenu = new ReturnMenu(appIO, library);
        returnMenu.run();
    }

    private void invalidOption() {
        appIO.print(INVALID_INPUT_MESSAGE);
    }

    private void exit() {
        this.toExit = true;
        appIO.print(EXIT_MESSAGE);
    }
}
