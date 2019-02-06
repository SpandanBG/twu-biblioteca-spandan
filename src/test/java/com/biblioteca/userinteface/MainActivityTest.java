package com.biblioteca.userinteface;

import com.biblioteca.activities.MainActivity;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MainActivityTest {

    @Test
    void expectsAGreetingMessageWhenApplicationIsStarted() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        MainActivity activity = new MainActivity(appIO);

        String greetingString = "Hello user!\nWelcome to the Biblioteca Application.\n";
        activity.onStart();

        verify(appIO, times(1)).print(greetingString);
    }

    @Test
    void expectsAMenuWhenApplicationIsRunning() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        MainActivity activity = new MainActivity(appIO);

        String menuString = "\n\t\tMenu\n\t1) List Books\n\t0) Quit\nChoice: ";
        when(appIO.read()).then((Answer<String>) invocation -> "0");

        activity.onRunning();

        verify(appIO).print(menuString);
    }

    @Test
    void expectsAExitMessageWhenApplicationEnds() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        MainActivity activity = new MainActivity(appIO);

        String exitString = "Bye";
        activity.onExit();

        verify(appIO).print(exitString);
    }

    @Test
    void expectsQuitWhenUserChoosesQuitOption() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        MainActivity activity = new MainActivity(appIO);

        when(appIO.read()).then((Answer<String>) invocation -> "0");
        activity.run();

        verify(appIO, times(3)).print(captor.capture());
        assertEquals("Bye", captor.getAllValues().get(2));
    }

    @Test
    void expectsInvalidOptionWhenUserChoosesWrongOption() {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        MainActivity activity = new MainActivity(appIO);

        when(appIO.read()).thenReturn("-1", "0");
        activity.run();

        verify(appIO, times(5)).print(captor.capture());
        assertEquals("Unknown option!\n", captor.getAllValues().get(2));
    }
}