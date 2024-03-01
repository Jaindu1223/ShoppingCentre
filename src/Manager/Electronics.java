package Manager;
public class Electronics extends Product {                  // Electronics class is a subclass of Product

    private String brand;               // Additional properties specific to Electronics class
    private int warrantyPeriod;

    public Electronics(String productId, String productName, int noItems, double price, String brand, int warrantyPeriod) {             // Constructor for Electronics class

        super(productId, productName, noItems, price);              // Invoke the constructor of the parent class Product


        this.brand = brand;                     // Initialize properties related to this subclass Electronics
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {                  // Getters and setters for brand and warrantyPeriod properties
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {                      // Override toString method to provide a string representation of Electronics
        return String.format("Electronics, %s, %s, %d, %.2f, %s, %d", productId, productName, quantity, price, brand, warrantyPeriod);
    }

    @Override                       // Override getCategory method to specify the category of the product
    public String getCategory() {
        return "Electronics";
    }

    @Override               // Override getInfo method to provide additional information specific to Electronics
    public String getInfo() {
        return String.format("%s %d Months", brand, warrantyPeriod);
    }

    @Override               // Override decreaseQuantity method to provide additional information specific to products
    public void decreaseQuantity(int quantity) {

    }
}
