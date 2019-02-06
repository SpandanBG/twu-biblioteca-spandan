package com.biblioteca.library;

// Represents a literary composition that is published
public class Book {

    private final String name;

    private Book(String name) {
        this.name = name;
    }

    public static Book book(String name) {
        return new Book(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
