package Manager;

import java.util.ArrayList;


public class ShoppingCart {             // Represents a shopping cart that holds a collection of products
    public static int quantity;         // Static variable to track the total quantity (shared among all instances)
    private ArrayList<Product> cart;        // List to store products in the shopping cart

    public ShoppingCart() {
        this.cart = new ArrayList<>();
    }       // Constructor to initialize the shopping cart

    public void addProductToCart(Product product, int quantity) {       // Add a product to the shopping cart with a specified quantity
        boolean found = false;
        for (Product cartProduct : cart) {              // Check if the product is already in the cart
            if (cartProduct.equals(product)) {

                cartProduct.setNoItems(cartProduct.getNoItems() + quantity);        // If found, update the quantity
                found = true;
                break;
            }
        }
        if (!found) {           // If the product is not already in the cart, add it
            product.setNoItems(quantity);
            cart.add(product);
        }
        System.out.println("Product added to the shopping cart.");
        System.out.println(product.getProductName() + " added to the cart.");
    }

    public void removeProductFromCart(Product product) {             // Remove a product from the shopping cart
        if (cart.remove(product)) {
            System.out.println(product.getProductName() + " removed from the cart.");
        } else {
            System.out.println("Product not found in the cart.");
        }
    }

    public int getQuantity(Product product) {       // Get the quantity of a specific product in the cart
        for (Product cartProduct : cart) {
            if (cartProduct.equals(product)) {
                return cartProduct.getNoItems();
            }
        }
        return 0;
    }

    public double calculateTotalCost() {            // Calculate the total cost of all products in the cart
        double totalCost = 0.0;
        for (Product product : cart) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    public void viewCart() {            // Display the contents of the shopping cart
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Products in your cart:");
            for (Product product : cart) {
                System.out.println(product.getProductName() + " - $" + product.getPrice());
            }
            System.out.println("Total Cost: $" + calculateTotalCost());
        }
    }

    public ArrayList<Product> getCart() {
        return new ArrayList<>(cart);
    }       // Get a copy of the current cart

    public void clearCart() {
        cart.clear();
    }           // Clear all products from the cart
}
