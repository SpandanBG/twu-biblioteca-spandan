package com.biblioteca.controller;

import com.biblioteca.controller.Options;
import com.biblioteca.view.ApplicationIO;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class OptionsTest {

    @Test
    void expectsAHelloWorldPrintWhenUserChooseOptionOne() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        Options options = new Options();

        options.addOption("hello", "says hello", () -> appIO.print("Hello World"));
        options.select("hello");

        verify(appIO).print("Hello World");
    }

    @Test
    void expectsDefaultActionPrintWhenUserChoosesWrongOption() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        Options options = new Options();

        options.selectOrDefault("hello", () -> appIO.print("Default Action"));

        verify(appIO).print("Default Action");
    }
}