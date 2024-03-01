package Manager;


public abstract class Product {             // Abstract class representing a generic product
    protected String productId;         // Common properties for all products
    protected String productName;
    protected int quantity;
    protected double price;


    public Product(String productId, String productName, int noItems, double price) {       // Constructor for initializing common properties
        this.productId = productId;
        this.productName = productName;
        this.quantity = noItems;
        this.price = price;
    }
    public int getAvailableItems() {            // Abstract method to get the available items
        return quantity;                    // Assuming quantity represents the available items
    }

    public String getProductId() {
        return productId;
    }           // Getters for common properties

    public String getProductName() {
        return productName;
    }

    public int getNoItems() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setProductId(String productId) {    // Setters for common properties
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setNoItems(int noItems) {
        this.quantity = noItems;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract String toString();          // Abstract methods to be implemented by subclasses

    public abstract String getCategory();

    public abstract String getInfo();

    public abstract void decreaseQuantity(int quantity);        // Abstract method to decrease the quantity (to be implemented by subclasses)
}

