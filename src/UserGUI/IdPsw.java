/*package UserGUI;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IdPsw {

    private HashMap<String, String> logininfo = new HashMap<>();

    public IdPsw() {        // Load existing users from file on initialization

        loadUsers();
    }

    public HashMap<String, String> getLoginInfo() {
        return logininfo;
    }

    public boolean userExists(String username) {
        return logininfo.containsKey(username);
    }

    public void addUser(String username, String password) {
        logininfo.put(username, password);
    }

    public void saveUsers() throws IOException {
        try (FileWriter writer = new FileWriter("user_data.txt")) {
            for (Map.Entry<String, String> entry : logininfo.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }
    }

    public HashMap getUsers() {
        return logininfo;
    }

    public void loadUsers() {
        File file = new File("user_data.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    logininfo.put(username, password);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error loading user data: " + e.getMessage());
        }
    }

    // Static method to save users without creating an instance of IdAndPassword
    public static void saveUsers(Map<String, String> users) throws IOException {
        try (FileWriter writer = new FileWriter("user_data.txt")) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }

    }
}*/
package UserGUI;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IdPsw {

    private HashMap<String, String> loginInfo = new HashMap<>();        // HashMap to store user login information (username, password)

    public IdPsw() {            // Constructor: Load existing users from file on initialization
        loadUsers();
    }

    public HashMap<String, String> getLoginInfo() {     // Getter for loginInfo
        return loginInfo;
    }

    public boolean userExists(String username) {        // Check if a user with the given username exists
        return loginInfo.containsKey(username);
    }

    public void addUser(String username, String password) {     // Add a new user with the given username and password
        loginInfo.put(username, password);
    }

    public void saveUsers() throws IOException {            // Save user data to a file (user_data.txt)
        try (FileWriter writer = new FileWriter("user_data.txt")) {
            for (Map.Entry<String, String> entry : loginInfo.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");   // Write each user entry to the file
            }
            System.out.println("User data saved successfully to user_data.txt");   // Inform the user that the data has been successfully saved
        }
    }

    public void loadUsers() {       // Load user data from the file (user_data.txt)
        File file = new File("user_data.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    loginInfo.put(username, password);      // Add loaded user data to the loginInfo HashMap
                }
            }
            System.out.println("User data loaded successfully from user_data.txt");     // Inform the user that the data has been successfully loaded
        } catch (FileNotFoundException e) {
            System.err.println("Error loading user data: " + e.getMessage());       // Handle file not found exception
        }
    }


    public static void saveUsers(Map<String, String> users) throws IOException {  // Static method to save users without creating an instance of IdPsw
        try (FileWriter writer = new FileWriter("user_data.txt")) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");       // Write each user entry to the file
            }
            System.out.println("User data saved successfully to user_data.txt");        // Inform the user that the data has been successfully saved
        }
    }
}
