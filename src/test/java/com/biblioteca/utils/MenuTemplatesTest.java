package com.biblioteca.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTemplatesTest {

    @Test
    void expectsAViewOfMenuFromBasicTemplate() {
        Menu menu = new Menu();
        menu.addOption("list", "List books", ()->{});
        menu.addOption("exit", "Exit application", ()->{});

        String menuView = MenuTemplates.COMMAND_VIEW.view(menu);
        String expectedView = "\t\tCommand Menu\n" +
                "\texit - Exit application\n" +
                "\tlist - List books\n" +
                "Command >> ";

        assertEquals(expectedView, menuView);
    }

}