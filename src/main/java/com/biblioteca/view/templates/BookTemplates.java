package com.biblioteca.view.templates;

import com.biblioteca.model.library.Book;

// Represents view styles
public enum  BookTemplates {

    INFORMAL {
        @Override
        public String view(Book book) {
            String format = "%s, by %s (%s)";
            return String.format(
                    format,
                    book.name(), book.author(), book.year()
            );
        }
    };

    public abstract String view(Book book) ;
}
