package com.biblioteca.library;

// Represents a literary composition that is published
public class Book {

    private final String name;
    private final String author;
    private final Integer year;

    private Book(String name, String author, Integer year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public static Book book(String name, String author, Integer year) {
        return new Book(name, author, year);
    }

    String name() {
        return name;
    }

    String author() {
        return author;
    }

    String year() {
        return year.toString();
    }
}
