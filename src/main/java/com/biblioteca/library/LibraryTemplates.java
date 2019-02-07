package com.biblioteca.library;

import com.biblioteca.utils.Incrementor;

// Represents view styles
public enum LibraryTemplates {

    INFORMAL {
        @Override
        public String view(Library library) {
            StringBuilder builder = new StringBuilder();
            String format = "%d) %s\n\t- %s (%s)\n";
            Incrementor index = new Incrementor(1);
            library.forEachBook(book -> {
                builder.append(String.format(
                        format,
                        index.value(), book.name(), book.author(), book.year()
                ));
                index.increment(1);
            });
            return builder.toString();
        }
    };

    abstract public String view(Library library);
}
