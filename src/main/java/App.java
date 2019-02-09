import com.biblioteca.activities.MainMenu;
import com.biblioteca.library.Library;
import com.biblioteca.userinteface.ApplicationIO;

import java.util.Arrays;

import static com.biblioteca.library.Book.book;
import static com.biblioteca.library.Library.library;
import static com.biblioteca.userinteface.ApplicationIO.createConsoleIO;

public class App {
    public static void main(String... args) {
        ApplicationIO appIO = createConsoleIO();
        Library library = library(Arrays.asList(
                book("One Hundred Years Of Solitude", "Gabriel García Márquez", 1967),
                book("Fever Code", "James Dashner", 2009),
                book("Hunger Games", "Suzanne Collins", 2008),
                book("Catching Fire (Hunger Games)", "Suzanne Collins", 2010)

        ));
        MainMenu activity = new MainMenu(appIO, library);
        activity.run();
    }
}
