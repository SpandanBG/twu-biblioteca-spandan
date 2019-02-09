package com.biblioteca.activities;

import com.biblioteca.library.Library;
import com.biblioteca.library.LibraryTemplates;
import com.biblioteca.userinteface.ApplicationIO;
import com.biblioteca.utils.OptionTemplates;
import com.biblioteca.utils.Options;

// Represents the first set of options available to an user
public class MainMenu {

    private static final String LIST_BOOK_HEADER = "\nAvailable books:\n";
    private static final String NO_BOOKS_AVAILABLE = "\nNo books available.\n";
    private static final String MENU_HEADER = "Available commands:";
    private static final String MENU_FOOTER = "";
    private static final String INVALID_INPUT_MESSAGE = "Unknown option!\n";
    private static final String INPUT_PROMOTER = ">> ";
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
        setupMenu();
        showMenu();
        interactWithUser();
    }

    private void interactWithUser() {
        while (!toExit) {
            appIO.print(INPUT_PROMOTER);
            String option = appIO.read();
            options.selectOrDefault(option, this::invalidOption);
        }
    }

    private void invalidOption() {
        appIO.print(INVALID_INPUT_MESSAGE);
    }

    private void setupMenu() {
        options.addOption("list", "List books", this::listBook);
        options.addOption("checkout", "Checkout book", this::launchCheckoutMenu);
        options.addOption("return", "Return book", this::launchReturnMenu);
        options.addOption("help", "Show menu", this::showMenu);
        options.addOption("exit", "Exit application", this::exit);
        options.setPrefix(MENU_HEADER);
        options.setSuffix(MENU_FOOTER);
    }

    private void launchReturnMenu() {
        ReturnMenu returnMenu = new ReturnMenu(appIO, library);
        returnMenu.run();
    }

    private void showMenu() {
        String menuString = OptionTemplates.CUSTOM_VIEW.view(options);
        appIO.print(menuString);
    }

    private void listBook() {
        if (library.isEmpty()) {
            appIO.print(NO_BOOKS_AVAILABLE);
            return;
        }
        String libraryView = LibraryTemplates.INFORMAL_LIST_VIEW.view(library);
        String builder = LIST_BOOK_HEADER + libraryView;
        appIO.print(builder);
    }

    private void launchCheckoutMenu() {
        CheckoutMenu activity = new CheckoutMenu(appIO, library);
        activity.run();
    }

    private void exit() {
        this.toExit = true;
        appIO.print(EXIT_MESSAGE);
    }
}
