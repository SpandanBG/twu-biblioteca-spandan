package com.biblioteca.activities;

import com.biblioteca.library.Library;
import com.biblioteca.userinteface.ApplicationIO;
import com.biblioteca.utils.Incrementor;
import com.biblioteca.utils.OptionTemplates;
import com.biblioteca.utils.Options;

// Represents a series of actions to borrow a book
public class CheckoutMenu {

    private static final String SEARCH_BY_STRING = "\nSearch by name: ";

    private final ApplicationIO appIO;
    private final Library library;

    public CheckoutMenu(ApplicationIO appIO, Library library) {
        this.appIO = appIO;
        this.library = library;
    }

    public void run() {
        appIO.print(SEARCH_BY_STRING);
        String bookName = appIO.read();
        Options options = getBookOptions(bookName);
        displayOptions(options);
    }

    private void displayOptions(Options options) {
        options.setPrefix("Books by that name");
        options.setSuffix("Enter book number [default: cancel]");
        String bookOptionsMenu = OptionTemplates.CUSTOM_VIEW.view(options);
        appIO.print(bookOptionsMenu);
    }

    private Options getBookOptions(String bookName) {
        Options bookOptions = new Options();
        Incrementor index = new Incrementor(1);
        library.forBookByPattern(bookName, (book) -> {
            library.addBookToOption(index.value(), bookOptions, book);
            index.increment(1);
        });
        bookOptions.addOption("cancel", "Cancel checkout", () -> {});
        return bookOptions;
    }
}
