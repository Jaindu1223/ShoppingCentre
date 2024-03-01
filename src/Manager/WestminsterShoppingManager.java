package Manager;
import UserGUI.Login;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class WestminsterShoppingManager {

    ArrayList<Product> products;        // ArrayList to store the products
    Scanner scanner = new Scanner(System.in);  //getting inputs

    @Test
    public void startConsoleMenu() throws IOException {
        int choice;
        do {        // Displaying the main menu
            System.out.println("Westminster Shopping Manager Menu:");
            System.out.println("1. Manager");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {     // Validate user input for menu choice
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {       // Handling user choices
                case 1:          // Invoking manager menu
                    StockUpdate.main();
                    break;
                case 2:     // Opening User GUI
                    new Login(new HashMap<>());
                    break;
                case 3:     //quit
                    System.out.println("Exiting Westminster Shopping Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 3);
    }


    /*private void loadProducts() {       // Loading products (method stub, implementation needed)
        // Implement loading logic
    }*/

    public WestminsterShoppingManager() {           // Constructor to initialize the ArrayList
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {       // Method to add a product to the list

        if (products.size() < 50) {         // Check if the product limit has been reached
            products.add(product);
        } else {
            System.out.println("Product limit has reached...");
        }
    }

    public void removeProduct(String productId) {           // Method to remove a product from the list based on productId
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();

            if (product.getProductId().equals(productId)) {         // Check if the product matches the given productId
                iterator.remove();
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public ArrayList<Product> getAllProducts() {            // Method to get a copy of all products in the list
        return new ArrayList<>(this.products);
    }

    public void saveToFile() {      // Method to save products to a file (ProductsList.txt)
        try (FileWriter writer = new FileWriter("ProductsList.txt")) {
            for (Product product : products) {
                writer.write(product.toString() + "\n");  // Write each product's details to the file
            }
            System.out.println("Products saved successfully to ProductsList.txt");
        } catch (IOException e) {
            System.err.println("Error saving products: " + e.getMessage());
        }
    }

    public void loadFromFile() {            // Method to load products from a file (ProductsList.txt)
        try (Scanner scanner = new Scanner(new File("ProductsList.txt"))) {
            while (scanner.hasNextLine()) {
                String productLine = scanner.nextLine();
                String[] productData = productLine.split(",", -1); // Preserve trailing empty strings
                for (int i = 0; i < productData.length; i++) {
                    productData[i] = productData[i].trim();
                }

                if (productData.length >= 7) {
                    String productType = productData[0];


                    if (productType.equals("Electronics")) {        // Handling different product types (Electronics, Clothing)
                        addProduct(new Electronics(
                                productData[1], productData[2], Integer.parseInt(productData[3]),
                                Double.parseDouble(productData[4]), productData[5], Integer.parseInt(productData[6])
                        ));
                    } else if (productType.equals("Clothing")) {
                        String size = productData[5].toUpperCase();     // Convert to uppercase for consistency
                        addProduct(new Clothing(
                                productData[1], productData[2], Integer.parseInt(productData[3]),
                                Double.parseDouble(productData[4]), size, productData[6]
                        ));
                    } else {
                        System.err.println("Invalid product type: " + productType);
                    }
                } else {
                    System.err.println("Invalid format in line: " + productLine);
                }
            }
            System.out.println("Products loaded successfully from ProductsList.txt");
        } catch (IOException e) {
            if (e.getMessage().contains("No such file or directory")) {
                System.out.println("No saved products found. Starting with a fresh list.");
            } else {
                System.err.println("Error loading products: " + e.getMessage());
            }
        }
    }
}

