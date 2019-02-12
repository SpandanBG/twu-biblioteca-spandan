import com.biblioteca.controller.menus.Launcher;
import com.biblioteca.model.library.Library;
import com.biblioteca.view.ApplicationIO;

import java.util.Arrays;

import static com.biblioteca.model.library.Book.book;
import static com.biblioteca.model.library.Library.library;
import static com.biblioteca.view.ApplicationIO.createConsoleIO;

public class App {
    public static void main(String... args) {
        ApplicationIO appIO = createConsoleIO();
        Library library = library(Arrays.asList(
                book("One Hundred Years Of Solitude", "Gabriel García Márquez", 1967),
                book("Fever Code", "James Dashner", 2009),
                book("Hunger Games", "Suzanne Collins", 2008),
                book("Catching Fire (Hunger Games)", "Suzanne Collins", 2010)

        ));
        Launcher launcher = new Launcher(appIO, library);
        launcher.run();
    }
}
