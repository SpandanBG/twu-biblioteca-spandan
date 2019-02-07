package com.biblioteca.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsTemplatesTest {

    @Test
    void expectsAViewOfMenuFromBasicTemplate() {
        Options options = new Options();
        options.addOption("list", "List books", ()->{});
        options.addOption("exit", "Exit application", ()->{});

        String menuView = OptionTemplates.COMMAND_VIEW.view(options);
        String expectedView = "\n\t\tCommand Options\n" +
                "\texit - Exit application\n" +
                "\tlist - List books\n" +
                "Command >> ";

        assertEquals(expectedView, menuView);
    }

}