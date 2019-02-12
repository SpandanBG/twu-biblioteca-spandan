package com.biblioteca.controller;

import com.biblioteca.controller.Incrementor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncrementorTest {

    @Test
    void expectsIncremantorToIncreaseByOneUnit() {
        Incrementor incrementor = new Incrementor(1);

        incrementor.increment(1);

        assertEquals(2, incrementor.value());
    }
}