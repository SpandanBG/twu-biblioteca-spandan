package com.biblioteca.userinterface;

import com.biblioteca.view.ApplicationIO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ApplicationIOTest {

    @Test
    void expectsToOutputHelloToTheOutputDevice() {
        ApplicationIO appIO = ApplicationIO.createConsoleIO();

        assertDoesNotThrow(() -> appIO.print("Hello"));
    }
}