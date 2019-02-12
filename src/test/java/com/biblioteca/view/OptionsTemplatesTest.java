package com.biblioteca.view;

import com.biblioteca.controller.Options;
import com.biblioteca.view.templates.OptionTemplates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionsTemplatesTest {

    @Test
    void expectsAViewOfMenuFromBasicTemplate() {
        Options options = new Options();
        options.addOption("list", "List books", () -> {
        });
        options.addOption("exit", "Exit application", () -> {
        });

        String menuView = OptionTemplates.COMMAND_VIEW.view(options);
        String expectedView = "\n\t\tCommand Options\n" +
                "\tlist - List books\n" +
                "\texit - Exit application\n";

        assertEquals(expectedView, menuView);
    }

}