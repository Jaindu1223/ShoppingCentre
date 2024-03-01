package Testing;

import Manager.Clothing;
import Manager.Electronics;
import Manager.Product;
import Manager.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    private ShoppingCart shoppingCart;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        shoppingCart = new ShoppingCart();
        product1 = new Electronics("1", "TestElectronics1", 10, 100.0, "TestBrand1", 12);
        product2 = new Clothing("2", "TestClothing1", 5, 50.0, "M", "Blue");
    }

    @Test
    void addProductToCart_ShouldWorkAsExpected() {
        shoppingCart.addProductToCart(product1, 3); // Act
        shoppingCart.addProductToCart(product2, 2);

        assertEquals(3, shoppingCart.getQuantity(product1));  // Assert
        assertEquals(2, shoppingCart.getQuantity(product2));
        assertEquals(2, shoppingCart.getCart().size());
    }

    @Test
    void removeProductFromCart_ShouldWorkAsExpected() {
        shoppingCart.addProductToCart(product1, 3);  // Arrange
        shoppingCart.addProductToCart(product2, 2);

        shoppingCart.removeProductFromCart(product1);  // Act

        assertEquals(0, shoppingCart.getQuantity(product1)); // Assert
        assertEquals(1, shoppingCart.getCart().size());
    }

    @Test
    void viewCart_ShouldWorkAsExpected() {
        shoppingCart.addProductToCart(product1, 3); // Arrange
        shoppingCart.addProductToCart(product2, 2);

        shoppingCart.viewCart();  // Act
    }

    @Test
    void clearCart_ShouldWorkAsExpected() {
        shoppingCart.addProductToCart(product1, 3);  // Arrange
        shoppingCart.addProductToCart(product2, 2);

        shoppingCart.clearCart(); // Act

        assertEquals(0, shoppingCart.getCart().size());  // Assert
    }
}
