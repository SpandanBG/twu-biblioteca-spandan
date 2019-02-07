package com.biblioteca.utils;

import com.biblioteca.userinteface.ApplicationIO;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MenuTest {

    @Test
    void expectsAHelloWorldPrintWhenUserChooseOptionOne() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        Menu menu = new Menu();

        menu.addOption("hello", "says hello", () -> appIO.print("Hello World"));
        menu.select("hello");

        verify(appIO).print("Hello World");
    }

    @Test
    void expectsDefaultActionPrintWhenUserChoosesWrongOption() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        Menu menu = new Menu();

        menu.selectOrDefault("hello", () -> appIO.print("Default Action"));

        verify(appIO).print("Default Action");
    }
}