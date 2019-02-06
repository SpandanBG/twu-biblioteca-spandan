package com.biblioteca.userinteface;

import com.biblioteca.activities.MainActivity;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MainActivityTest {

    @Test
    void expectsAGreetingMessageWhenApplicationIsStarted() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        MainActivity mainActivity = new MainActivity(appIO);

        String greetingString = "Hello user!\nWelcome to the Biblioteca Application.\n";
        mainActivity.run();

        verify(appIO, times(3)).print(argumentCaptor.capture());
        assertEquals(greetingString, argumentCaptor.getAllValues().get(0));
    }

    @Test
    void expectsAListOfBooksToBePrintedAfterGreeting() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        MainActivity mainActivity = new MainActivity(appIO);

        String firstExpectedString = "\n\tAvailable books\n";
        String secondExpectedString = "1) Hunger Games\n\t- Suzanne Collins (2008)\n";

        mainActivity.run();

        verify(appIO, times(3)).print(argumentCaptor.capture());
        assertEquals(firstExpectedString, argumentCaptor.getAllValues().get(1));
        assertEquals(secondExpectedString, argumentCaptor.getAllValues().get(2));
    }
}