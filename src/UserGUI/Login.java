/*
package UserGUI;


import Manager.Clothing;
import Manager.Electronics;
import Manager.Product;
import Manager.User;
import Manager.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Login implements ActionListener {

    private JFrame frame = new JFrame("Login Page");
    private JLabel loginLabel = new JLabel();
    private JButton loginButton = new JButton("Login");
    private JButton resetButton = new JButton("Reset");
    private JButton signUpButton = new JButton("Sign Up");
    private JTextField userIDField = new JTextField();
    private JPasswordField userPasswordField = new JPasswordField();
    private JLabel userIDLabel = new JLabel("Username:");
    private JLabel userPasswordLabel = new JLabel("Password:");
    private JLabel messageLabel = new JLabel();
    private JLabel signUpLabel = new JLabel("If you don't have an account");
    private HashMap<String, String> logininfo = new HashMap<>();

    public Login(HashMap<String, String> loginInfoOriginal) {
        loadUserData();
        logininfo = loginInfoOriginal;

        loginLabel.setBounds(50, 20, 100, 25);
        loginLabel.setFont(new Font(null, Font.BOLD, 15));
        loginLabel.setText("Login");

        userIDLabel.setBounds(60, 80, 75, 25);
        userPasswordLabel.setBounds(60, 130, 75, 25);

        messageLabel.setBounds(110, 320, 250, 25);
        messageLabel.setFont(new Font(null, Font.BOLD, 18));

        signUpLabel.setBounds(60, 260, 250, 35);

        userIDField.setBounds(135, 80, 200, 25);
        userPasswordField.setBounds(135, 130, 200, 25);

        loginButton.setBounds(135, 180, 95, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(240, 180, 95, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        signUpButton.setBounds(235, 265, 100, 25);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(this);

        frame.add(loginLabel);
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(signUpButton);
        frame.add(signUpLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void loadUserData() {
    }


    private void loadProducts(ArrayList<Product> products) {
        try (Scanner scanner = new Scanner(new File("ProductsList.txt"))) {
            while (scanner.hasNextLine()) {
                String productLine = scanner.nextLine();
                // Split the line into product attributes, trimming any leading/trailing whitespace
                String[] productData = productLine.split(",", -1); // Preserve trailing empty strings
                for (int i = 0; i < productData.length; i++) {
                    productData[i] = productData[i].trim(); // Trim each element
                }

                // Create the appropriate Product object based on the product type
                Product product;
                if (productData.length >= 7) {
                    String productType = productData[0];

                    if (productType.equals("Electronics")) {
                        product = new Electronics(
                                productData[1], // productId
                                productData[2], // productName
                                Integer.parseInt(productData[3]), // noItems
                                Double.parseDouble(productData[4]), // price
                                productData[5], // brand
                                Integer.parseInt(productData[6]) // warrantyPeriod
                        );
                    } else if (productType.equals("Clothing")) {
                        // Handle Clothing size differently
                        String size = productData[5].toUpperCase(); // Convert to uppercase for consistency
                        product = new Clothing(
                                productData[1], // productId
                                productData[2], // productName
                                Integer.parseInt(productData[3]), // noItems
                                Double.parseDouble(productData[4]), // price
                                size, // size
                                productData[6] // color
                        );
                    } else {
                        System.err.println("Invalid product type: " + productType);
                        continue; // Skip to the next iteration if the product type is invalid
                    }

                    products.add(product);
                } else {
                    System.err.println("Invalid format in line: " + productLine);
                }
            }
            System.out.println("Products loaded successfully from ProductsList.txt");
        } catch (IOException e) {
            if (e.getMessage().contains("No such file or directory")) {
                // File doesn't exist, print a custom message
                System.out.println("No saved products found. Starting with a fresh list.");
            } else {
                // Other error, print the original message
                System.err.println("Error loading products: " + e.getMessage());
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
        }

        if (e.getSource() == loginButton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if (logininfo.containsKey(userID)) {
                if (logininfo.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful");
                    frame.dispose();

                    User user = null;
                    try {
                        user = new User(userID, password);
                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    ArrayList<Product> products = new ArrayList<>();
                    loadProducts(products);
                    WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();
                    try {
                        westminsterShoppingManager.startConsoleMenu(); // Pass the user's name to the startConsoleMenu() method
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //ShoppingCentre shoppingCentre = new ShoppingCentre(products, user);
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Recheck password");
                }

            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Username not found");
            }
        }

        if (e.getSource() == signUpButton) {
            frame.dispose();
            // Pass the existing logininfo to the SignUpPage constructor
            GUI.SignUpPage signUpPage = new GUI.SignUpPage();
        }
    }

    public static void main(String[] args) {
        // Load user data from a file
        HashMap<String, String> loginInfo = new HashMap<>();
        try (Scanner scanner = new Scanner(new File("user_data.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    loginInfo.put(username, password);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error loading user data: " + e.getMessage());
        }

        // Create a new Login object with the loaded user data
        new Login(loginInfo);
    }
    */
/*public static void main(String[] args) {
        // For testing the Login class

        HashMap<String, String> loginInfo = new HashMap<>();
        loginInfo.put("user1", "password1");
        loginInfo.put("user2", "password2");
        new Login(loginInfo);

    }*//*

}*/

package UserGUI;

import Manager.Clothing;
import Manager.Electronics;
import Manager.Product;
import Manager.User;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Login implements ActionListener {
    private JFrame frame = new JFrame("Login Page");
    private JLabel loginLabel = new JLabel();
    private JButton loginButton = new JButton("Login");
    private JButton resetButton = new JButton("Reset");
    private JButton signUpButton = new JButton("Sign Up");
    private JTextField userIDField = new JTextField();
    private JPasswordField userPasswordField = new JPasswordField();
    private JLabel userIDLabel = new JLabel("Username:");
    private JLabel userPasswordLabel = new JLabel("Password:");
    private JLabel messageLabel = new JLabel();
    private JLabel signUpLabel = new JLabel("If you don't have an account");
    private HashMap<String, String> logininfo = new HashMap<>();

    public Login(HashMap<String, String> loginInfoOriginal) {    // Constructor
        IdPsw idPsw = new IdPsw();
        idPsw.loadUsers();
        logininfo = idPsw.getLoginInfo();

        loginLabel.setBounds(50, 20, 100, 25);          // GUI components setup
        loginLabel.setFont(new Font(null, Font.BOLD, 15));
        loginLabel.setText("Login");

        userIDLabel.setBounds(60, 80, 75, 25);
        userPasswordLabel.setBounds(60, 130, 75, 25);

        messageLabel.setBounds(110, 320, 250, 25);
        messageLabel.setFont(new Font(null, Font.BOLD, 18));

        signUpLabel.setBounds(60, 260, 250, 35);

        userIDField.setBounds(135, 80, 200, 25);
        userPasswordField.setBounds(135, 130, 200, 25);

        loginButton.setBounds(135, 180, 95, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(240, 180, 95, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        signUpButton.setBounds(235, 265, 100, 25);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(this);

        frame.add(loginLabel);          // Adding components to the frame
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(signUpButton);
        frame.add(signUpLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       // Frame configuration
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void loadProducts(ArrayList<Product> products) {        // Method to load products from file
        try (Scanner scanner = new Scanner(new File("ProductsList.txt"))) {
            while (scanner.hasNextLine()) {
                String productLine = scanner.nextLine();  // Split the line into product attributes, trimming any leading/trailing whitespace
                String[] productData = productLine.split(",", -1); // Preserve trailing empty strings
                for (int i = 0; i < productData.length; i++) {
                    productData[i] = productData[i].trim(); // Trim each element
                }

                Product product;        // Create the appropriate Product object based on the product type
                if (productData.length >= 7) {
                    String productType = productData[0];
                    if (productType.equals("Electronics")) {
                        product = new Electronics(
                                productData[1],         // productId
                                productData[2],         // productName
                                Integer.parseInt(productData[3]),       // noItems
                                Double.parseDouble(productData[4]),      // price
                                productData[5],     // brand name
                                Integer.parseInt(productData[6])    // warrantyPeriod
                        );
                    } else if (productType.equals("Clothing")) {        // Handle Clothing size differently

                        String size = productData[5].toUpperCase(); // Convert to uppercase for consistency
                        product = new Clothing(
                                productData[1], // productId
                                productData[2], // productName
                                Integer.parseInt(productData[3]), // noItems
                                Double.parseDouble(productData[4]), // price
                                size, // size
                                productData[6] // color
                        );
                    } else {
                        System.err.println("Invalid product type: " + productType);
                        continue;       // Skip to the next iteration if the product type is invalid
                    }
                    products.add(product);
                } else {
                    System.err.println("Invalid format in line: " + productLine);
                }
            }
            System.out.println("Products loaded successfully from ProductsList.txt");
        } catch (IOException e) {
            if (e.getMessage().contains("No such file or directory")) {         // File doesn't exist, print a custom message

                System.out.println("No saved products found. Starting with a fresh list.");
            } else {        // Other error, print the original message
                System.err.println("Error loading products: " + e.getMessage());
            }
        }
    }


    @Override       // ActionListener implementation
    public void actionPerformed(ActionEvent e) {        // Handle action events for buttons
        if (e.getSource() == resetButton) {
            userIDField.setText("");        // Reset button pressed, clear user ID and password fields
            userPasswordField.setText("");
        }
        if (e.getSource() == loginButton) {     // Login button pressed
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());
            if (logininfo.containsKey(userID)) {        // User ID found in logininfo map
                if (logininfo.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.green);    // Password matches, login successful
                    messageLabel.setText("Login successful");
                    frame.dispose();        // Close the login frame
                    User user = null;
                    try {
                        user = new User(userID, password);      // Create a User object with the provided user ID and password
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    ArrayList<Product> products = new ArrayList<>();    // Load products and open the Shopping Centre with the user
                    loadProducts(products);
                    ShoppingCentre shoppingCentre = new ShoppingCentre(products, user);
                } else {        // Incorrect password
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Recheck password");
                }
            } else {    // User ID not found
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Username not found");
            }
        }
        if (e.getSource() == signUpButton) {        // Sign Up button pressed, open the SignUpPage
            frame.dispose();

            UserGUI.SignUpPage signUpPage = new UserGUI.SignUpPage();   // Pass the existing login-info to the SignUpPage constructor
        }
    }

    public static void main(String[] args) {          // Main method for testing the Login class
        HashMap<String, String> loginInfo = new HashMap<>();    // Test the Login class with sample login information
        loginInfo.put("user1", "password1");
        loginInfo.put("user2", "password2");
        new Login(loginInfo);
    }
}
