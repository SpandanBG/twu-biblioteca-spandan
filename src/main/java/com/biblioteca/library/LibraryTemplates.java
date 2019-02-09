package com.biblioteca.library;

import com.biblioteca.utils.Incrementor;

// Represents view styles
public enum LibraryTemplates {

    INFORMAL_LIST_VIEW {
        @Override
        public String view(Library library) {
            StringBuilder builder = new StringBuilder();
            String format = "\t%d - %s\n";
            Incrementor index = new Incrementor(1);
            library.forEachBook(book -> {
                builder.append(String.format(
                        format, index.value(),
                        BookTemplates.INFORMAL.view(book)
                ));
                index.increment(1);
            });
            return builder.toString();
        }
    };

    abstract public String view(Library library);
}
