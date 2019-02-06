package com.biblioteca.library;

// Represents view styles
public enum ViewTemplates {

    INFORMAL {
        @Override
        public String view(Book book) {
            String format = "%s\n\t- %s (%s)\n";
            return String.format(
                    format, book.name(), book.author(), book.year()
            );
        }
    };

    abstract public String view(Book book);
}
