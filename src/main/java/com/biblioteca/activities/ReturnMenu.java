package com.biblioteca.activities;

import com.biblioteca.library.Book;
import com.biblioteca.library.BookTemplates;
import com.biblioteca.library.Library;
import com.biblioteca.userinteface.ApplicationIO;
import com.biblioteca.utils.Incrementor;
import com.biblioteca.utils.OptionTemplates;
import com.biblioteca.utils.Options;

// Represents the business policy to return book to library
class ReturnMenu {

    private static final String OPTIONS_PREFIX = "Borrowed books:";
    private static final String OPTIONS_SUFFIX = "Enter book number [default: cancel]: ";
    private static final String CANCEL_OPTION = "cancel";

    private static final String BOOK_RETURNED_MESSAGE = "\nBook returned. There is always something to read.\n";
    private static final String NO_BOOKS_MESSAGE = "\nNo books borrowed.\n";
    private static final String INVALID_INPUT_MESSAGE = "Unknown option!\n";

    private final ApplicationIO appIO;
    private final Library library;

    ReturnMenu(ApplicationIO appIO, Library library) {
        this.appIO = appIO;
        this.library = library;
    }

    void run() {
        if (!library.hasUnavailableBooks()) {
            appIO.print(NO_BOOKS_MESSAGE);
            return;
        }
        Options options = putBooksInOptions();
        displayOptions(options);
        interactWithUser(options);
    }

    private Options putBooksInOptions() {
        Options options = new Options();
        Incrementor index = new Incrementor(1);
        library.forEachUnavailable(book -> {
            options.addOption(
                    index.value().toString(),
                    BookTemplates.INFORMAL.view(book),
                    () -> this.returnBook(book)
            );
            index.increment(1);
        });
        return options;
    }

    private void displayOptions(Options options) {
        options.setPrefix(OPTIONS_PREFIX);
        options.setSuffix(OPTIONS_SUFFIX);
        options.addOption(CANCEL_OPTION, "Cancel return", () -> {
        });
        appIO.print(OptionTemplates.CUSTOM_VIEW.view(options));
    }

    private void interactWithUser(Options options) {
        String option = appIO.read();
        if (option.equals("")) {
            option = CANCEL_OPTION;
        }
        options.selectOrDefault(option, this::invalidInput);
    }

    private void returnBook(Book book) {
        library.returnBook(book);
        appIO.print(BOOK_RETURNED_MESSAGE);
    }

    private void invalidInput() {
        appIO.print(INVALID_INPUT_MESSAGE);
    }
}
