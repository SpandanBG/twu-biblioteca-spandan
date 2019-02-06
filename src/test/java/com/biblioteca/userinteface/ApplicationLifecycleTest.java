package com.biblioteca.userinteface;

import com.biblioteca.library.Book;
import com.biblioteca.library.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import static com.biblioteca.library.Book.*;
import static com.biblioteca.library.Library.*;
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

    @Test
    void expectsAListOfBooksToBePrintedAfterGreeting() throws IOException {
        ApplicationIO appIO = mock(ApplicationIO.class);
        Library library = library(Arrays.asList(
                book("Godfather"),
                book("Harry Potter")
        ));
        ApplicationLifecycle app = new ApplicationLifecycle(appIO, library);

        app.greet();
        verify(appIO).print(anyString());

        app.listBooks();
        verify(appIO).print(library.bookList());
    }
}