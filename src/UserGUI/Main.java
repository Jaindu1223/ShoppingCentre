package UserGUI;
import Manager.WestminsterShoppingManager;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();  // Create an instance of WestminsterShoppingManager
        shoppingManager.startConsoleMenu();   // Start the console menu
    }
}
