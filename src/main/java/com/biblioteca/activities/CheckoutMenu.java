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

    private static final String NO_SUCH_BOOK_MESSAGE = "\nNo such book in library\n";
    private static final String SEARCH_BY_STRING = "\nSearch by name: ";
    private static final String INVALID_INPUT_MESSAGE = "Unknown option!\n";
    private static final String OPTIONS_PREFIX = "Books by that name";
    private static final String OPTIONS_SUFFIX = "Enter book number [default: cancel]";
    private static final String NO_BOOK_WITH_NAME_MESSAGE = "\nNo book with that name\n";
    private static final String CANCEL_OPTION = "cancel";
    private static final String BOOK_CHECKED_MESSAGE = "\nBook checked out. Happy Reading!\n";
    private static final String NO_BOOKS_MESSAGE = "\nNo books in library\n";

    private final ApplicationIO appIO;
    private final Library library;

    CheckoutMenu(ApplicationIO appIO, Library library) {
        this.appIO = appIO;
        this.library = library;
    }

    void run() {
        if(library.isEmpty()) {
            appIO.print(NO_BOOKS_MESSAGE);
            return;
        }
        appIO.print(SEARCH_BY_STRING);
        String bookName = appIO.read();
        Library filteredBooks = library.filterBookByName(bookName);
        if (filteredBooks.isEmpty()) {
            appIO.print(NO_BOOK_WITH_NAME_MESSAGE);
            return;
        }
        Options options = createSelectableBooks(filteredBooks);
        displayOptions(options);
        chooseOption(options);
    }

    private void chooseOption(Options options) {
        String option = appIO.read();
        if (option.equals("")) {
            option = CANCEL_OPTION;
        }
        options.selectOrDefault(option, this::invalidOption);
    }

    private void invalidOption() {
        appIO.print(INVALID_INPUT_MESSAGE);
    }

    private void displayOptions(Options options) {
        options.setPrefix(OPTIONS_PREFIX);
        options.setSuffix(OPTIONS_SUFFIX);
        options.addOption("cancel", "Cancel checkout", () -> {
        });
        appIO.print(OptionTemplates.CUSTOM_VIEW.view(options));
    }

    private Options createSelectableBooks(Library filteredBooks) {
        Options bookOptions = new Options();
        Incrementor index = new Incrementor(1);
        filteredBooks.forEachBook(book -> {
            bookOptions.addOption(
                    index.value().toString(),
                    BookTemplates.INFORMAL.view(book),
                    () -> this.checkoutBook(book)
            );
            index.increment(1);
        });
        return bookOptions;
    }

    private void checkoutBook(Book book) {
        try {
            library.checkOutBook(book);
            appIO.print(BOOK_CHECKED_MESSAGE);
        } catch (BookNotAvailableException ignored) {
            appIO.print(NO_SUCH_BOOK_MESSAGE);
        }
    }
}
