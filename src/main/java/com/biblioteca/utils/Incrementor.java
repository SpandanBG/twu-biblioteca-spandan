package com.biblioteca.utils;

// Represents a unit that increases by some unit
public class Incrementor {

    private Integer value;

    public Incrementor(Integer seedValue) {
        this.value = seedValue;
    }

    public void increment(Integer byValue) {
        this.value += byValue;
    }

    public Integer value() {
        return value;
    }
}
