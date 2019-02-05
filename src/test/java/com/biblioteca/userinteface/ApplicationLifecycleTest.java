package com.biblioteca.userinteface;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

class ApplicationLifecycleTest {

    @Test
    void expectsAGreetingMessageWhenApplicationIsStarted() throws IOException {
        ApplicationIO appIO = mock(ApplicationIO.class);
        ApplicationLifecycle app = new ApplicationLifecycle(appIO);

        app.greet();

        verify(appIO).print(anyString());
    }
}