package com.biblioteca.activities;

import com.biblioteca.library.Book;
import com.biblioteca.library.BookNotAvailableException;
import com.biblioteca.library.BookTemplates;
import com.biblioteca.library.Library;
import com.biblioteca.userinteface.ApplicationIO;
import com.biblioteca.utils.Incrementor;
import com.biblioteca.utils.OptionTemplates;
import com.biblioteca.utils.Options;

// Represents the business policy to borrow book
class CheckoutMenu {

    private static final String OPTIONS_PREFIX = "Books by that name:";
    private static final String OPTIONS_SUFFIX = "Enter book number [default: cancel]: ";

    private static final String SEARCH_BY_STRING = "\nSearch by name: ";
    private static final String BOOK_CHECKED_MESSAGE = "\nThank you! Enjoy the book.\n";
    private static final String CANCEL_OPTION = "cancel";

    private static final String INVALID_INPUT_MESSAGE = "Unknown option!\n";
    private static final String NO_INPUT_MESSAGE = "\nHmmm... Looks like you didn't type anything.\n";
    private static final String NO_BOOKS_MESSAGE = "\nNo books in library.\n";
    private static final String NO_SUCH_BOOK_MESSAGE = "\nThat book is not available.\n";

    private final ApplicationIO appIO;
    private final Library library;

    CheckoutMenu(ApplicationIO appIO, Library library) {
        this.appIO = appIO;
        this.library = library;
    }

    void run() {
        if (library.isEmpty()) {
            appIO.print(NO_BOOKS_MESSAGE);
            return;
        }
        processCheckout();
    }

    private void processCheckout() {
        String bookName = inputBookName().trim();
        if (bookName.equals("")) {
            appIO.print(NO_INPUT_MESSAGE);
            return;
        }
        if (!library.hasAvailableBooks(bookName)) {
            appIO.print(NO_SUCH_BOOK_MESSAGE);
            return;
        }
        Options options = putBooksInOption(bookName);
        displayOptions(options);
        getOptionFromUser(options);
    }

    private String inputBookName() {
        appIO.print(SEARCH_BY_STRING);
        return appIO.read();
    }

    private Options putBooksInOption(String bookNameFilter) {
        Options options = new Options();
        Incrementor index = new Incrementor(1);
        library.forEachBook(bookNameFilter, book -> {
            options.addOption(
                    index.value().toString(),
                    BookTemplates.INFORMAL.view(book),
                    () -> this.checkoutBook(book)
            );
            index.increment(1);
        });
        return options;
    }

    private void getOptionFromUser(Options options) {
        String option = appIO.read();
        if (option.equals("")) {
            option = CANCEL_OPTION;
        }
        options.selectOrDefault(option, this::invalidOption);
    }

    private void displayOptions(Options options) {
        options.setPrefix(OPTIONS_PREFIX);
        options.setSuffix(OPTIONS_SUFFIX);
        options.addOption(CANCEL_OPTION, "Cancel checkout", () -> {
        });
        appIO.print(OptionTemplates.CUSTOM_VIEW.view(options));
    }

    private void checkoutBook(Book book) {
        try {
            library.checkoutBook(book);
            appIO.print(BOOK_CHECKED_MESSAGE);
        } catch (BookNotAvailableException ignored) {
            appIO.print(NO_SUCH_BOOK_MESSAGE);
        }
    }

    private void invalidOption() {
        appIO.print(INVALID_INPUT_MESSAGE);
    }
}
