package Testing;

import Manager.Clothing;
import Manager.Electronics;
import Manager.Product;
import Manager.WestminsterShoppingManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WestminsterShoppingManagerTest {

    private WestminsterShoppingManager shoppingManager;

    @BeforeEach
    void setUp() {
        shoppingManager = new WestminsterShoppingManager();
    }

    @Test
    void addProduct_ShouldAddProductToList() {
        Product product = new Electronics("1", "Laptop", 10, 999.99, "Dell", 12); // Arrange

        shoppingManager.addProduct(product);  // Act
        ArrayList<Product> products = shoppingManager.getAllProducts();

        assertEquals(1, products.size()); // Assert
        assertEquals(product, products.get(0));
    }

    @Test
    void addProduct_ShouldNotAddProductIfLimitReached() {
        for (int i = 0; i < 51; i++) { // Arrange
            Product product = new Electronics("2" + i, "Product" + i, 10, 99.99, "Brand" + i, 12);
            shoppingManager.addProduct(product);
        }

        Product additionalProduct = new Electronics("E060", "AdditionalProduct", 10, 99.99, "Brand", 12);
        shoppingManager.addProduct(additionalProduct); // Act

        assertEquals(50, shoppingManager.getAllProducts().size()); // Assert
    }

    @Test
    void removeProduct_ShouldRemoveProductFromList() {
        Product product1 = new Electronics("1", "Laptop", 10, 999.99, "Dell", 12);  // Arrange
        Product product2 = new Clothing("2", "Shirt", 20, 29.99, "M", "Blue");

        shoppingManager.addProduct(product1); // Act
        shoppingManager.addProduct(product2);
        shoppingManager.removeProduct("1");

        ArrayList<Product> products = shoppingManager.getAllProducts(); // Assert
        assertEquals(1, products.size());
        assertEquals(product2, products.get(0));
    }



    @Test
    void loadFromFile_ShouldLoadProductsFromFile() throws IOException {
        Files.write(new File("ProductsList.txt").toPath(), List.of(  // Arrange
                "Electronics,1,Laptop,10,999.99,Dell,12",
                "Clothing,2,Shirt,20,29.99,M,Blue"
        ));
        shoppingManager.loadFromFile(); // Act
        ArrayList<Product> products = shoppingManager.getAllProducts(); // Assert
        assertEquals(2, products.size());
        assertTrue(products.get(0) instanceof Electronics);
        assertTrue(products.get(1) instanceof Clothing);
        assertEquals("1", products.get(0).getProductId());
        assertEquals("2", products.get(1).getProductId());
    }
}
