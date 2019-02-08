package com.biblioteca.activities;

import com.biblioteca.library.Book;
import com.biblioteca.library.BookTemplates;
import com.biblioteca.library.Library;
import com.biblioteca.userinteface.ApplicationIO;
import com.biblioteca.utils.Incrementor;
import com.biblioteca.utils.OptionTemplates;
import com.biblioteca.utils.Options;

// Represents the business policy to return book to library
public class ReturnMenu {

    public static final String BOOK_RETURNED_MESSAGE = "\nBook returned. There is always something to read.\n";
    public static final String CANCEL_OPTION = "cancel";
    private final ApplicationIO appIO;
    private final Library library;

    public ReturnMenu(ApplicationIO appIO, Library library) {
        this.appIO = appIO;
        this.library = library;
    }


    public void run() {
        Library borrowedBooks = library.borrowedBooks();
        if (borrowedBooks.isEmpty()) {
            appIO.print("\nNo books borrowed\n");
            return;
        }
        Options options = createSelectableBooks(borrowedBooks);
        displayOptions(options);
        chooseOption(options);
    }

    private Options createSelectableBooks(Library borrowedBooks) {
        Options options = new Options();
        Incrementor index = new Incrementor(1);
        borrowedBooks.forEachBook(book -> {
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
        options.setPrefix("Borrowed books");
        options.setSuffix("Enter book number [default: cancel]");
        options.addOption("cancel", "Cancel return", () -> {
        });
        appIO.print(OptionTemplates.CUSTOM_VIEW.view(options));
    }

    private void chooseOption(Options options) {
        String option = appIO.read();
        if (option.equals("")) {
            option = CANCEL_OPTION;
        }
        options.selectOrDefault(option, this::invalidInput);
    }

    private void invalidInput() {
        appIO.print("Unknown option!\n");
    }

    private void returnBook(Book book) {
        library.checkInBook(book);
        appIO.print(BOOK_RETURNED_MESSAGE);
    }
}
