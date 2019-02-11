package com.biblioteca.activities;

import com.biblioteca.library.Library;
import com.biblioteca.userinteface.ApplicationIO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LauncherTest {

    @Test
    void expectToGreetUserWhenItApplicationStarts() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        Library library = Library.library(EMPTY_LIST);
        Launcher launcher = new Launcher(appIO, library);

        when(appIO.read()).thenReturn("exit");
        launcher.run();

        verify(appIO).print("Hello user!\nWelcome to the Biblioteca Application.\n");
    }

    @Test
    void expectsToDisplayTheMenuAfterGreeting() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Library library = Library.library(EMPTY_LIST);
        Launcher launcher = new Launcher(appIO, library);

        String expectedMenu = "\n\t\tAvailable commands:\n" +
                "\tlist - List books\n" +
                "\tcheckout - Checkout book\n" +
                "\treturn - Return book\n" +
                "\thelp - Show menu\n" +
                "\texit - Exit application\n";
        when(appIO.read()).thenReturn("exit");
        launcher.run();

        verify(appIO, times(4)).print(captor.capture());
        assertEquals(expectedMenu, captor.getAllValues().get(1));
    }

}