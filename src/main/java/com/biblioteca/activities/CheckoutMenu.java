package com.biblioteca.activities;

import com.biblioteca.library.BookTemplates;
import com.biblioteca.library.Library;
import com.biblioteca.userinteface.ApplicationIO;
import com.biblioteca.utils.Incrementor;
import com.biblioteca.utils.OptionTemplates;
import com.biblioteca.utils.Options;

// Represents a series of actions to borrow a book
public class CheckoutMenu {

    private static final String SEARCH_BY_STRING = "\nSearch by name: ";
    public static final String INAVLID_INPUT_STRING = "Unknown option!\n";
    public static final String OPTIONS_PREFIX = "Books by that name";
    public static final String OPTIONS_SUFFIX = "Enter book number [default: cancel]";

    private final ApplicationIO appIO;
    private final Library library;

    public CheckoutMenu(ApplicationIO appIO, Library library) {
        this.appIO = appIO;
        this.library = library;
    }

    public void run() {
        appIO.print(SEARCH_BY_STRING);
        String bookName = appIO.read();
        Options options = createBookOptions(bookName);
        displayOptions(options);
        chooseOption(options);
    }

    private void chooseOption(Options options) {
        options.selectOrDefault(appIO.read(), this::invalidOption);
    }

    private void invalidOption() {
        appIO.print(INAVLID_INPUT_STRING);
    }

    private void displayOptions(Options options) {
        options.setPrefix(OPTIONS_PREFIX);
        options.setSuffix(OPTIONS_SUFFIX);
        options.addOption("cancel", "Cancel checkout", () -> {});
        String bookOptionsMenu = OptionTemplates.CUSTOM_VIEW.view(options);
        appIO.print(bookOptionsMenu);
    }

    private Options createBookOptions(String bookName) {
        Library filteredBooks = library.filterBookByName(bookName);
        Options bookOptions = new Options();
        Incrementor index = new Incrementor(1);
        filteredBooks.forEachBook(book -> {
            bookOptions.addOption(
                    index.value().toString(),
                    BookTemplates.INFORMAL.view(book),
                    () -> library.checkoutBook(book)
            );
            index.increment(1);
        });
        return bookOptions;
    }
}
