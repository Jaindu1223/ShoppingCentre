package Manager;

public class Clothing extends Product{     // Clothing class is a subclass of Product
    private String size;            // Additional properties specific to Clothing class
    private String color;

    public Clothing(String productId, String productName, int noItems, double price, String size, String color) {    // Constructor for Clothing class
        super(productId, productName, noItems, price);          // Invoke the constructor of the parent class Product

        this.size = size;           // Initialize properties related to this subclass Clothing
        this.color = color;
    }
    public String getSize() {
        return size;
    }       // Getters and setters for size and color properties
    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {                  // Override toString method to provide a string representation of Clothing
        return String.format("Clothing, %s, %s, %d, %.2f, %s, %s", productId, productName, quantity, price, size, color);
    }
    @Override                      // Override getCategory method to specify the category of the product
    public String getCategory() {
        return "Clothing";
    }

    @Override           // Override getInfo method to provide additional information specific to Clothing
    public String getInfo() {
        return color + size;
    }

    @Override           // Override decreaseQuantity method to provide additional information specific to products
    public void decreaseQuantity(int quantity) {

    }
}
