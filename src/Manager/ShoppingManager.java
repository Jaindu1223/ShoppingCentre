package Manager;

import java.io.IOException;
import java.util.ArrayList;

public interface ShoppingManager {

    public void addProduct(Product product);

    public void removeProduct(String productId);

    public ArrayList<Product> getAllProducts();

    public void saveToFile();

    public default void loadFromFile() {

    }
}
