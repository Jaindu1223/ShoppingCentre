package Testing;

import Manager.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ProductTest {

    @Test
     void gettersAndSetters_ShouldWorkAsExpected() {

        Product product = new MockProduct("P001", "TestProduct", 10, 50.0); // Arrange

        assertEquals("P001", product.getProductId()); // Act and Assert
        assertEquals("TestProduct", product.getProductName());
        assertEquals(10, product.getNoItems());
        assertEquals(50.0, product.getPrice());

        product.setProductId("P002");   // Change values using setters
        product.setProductName("UpdatedProduct");
        product.setNoItems(20);
        product.setPrice(75);

        assertEquals("P002", product.getProductId());  // Verify changes
        assertEquals("UpdatedProduct", product.getProductName());
        assertEquals(20, product.getNoItems());
        assertEquals(75.0, product.getPrice());
    }

    @Test
    void toString_ShouldNotBeNull() {
        Product product = new MockProduct("P001", "TestProduct", 10, 50.0); // Arrange
        assertNotNull(product.toString());  // Act and Assert
    }


    private static class MockProduct extends Product { // MockProduct class to test abstract Product class
        public MockProduct(String productId, String productName, int noItems, double price) {
            super(productId, productName, noItems, price);
        }

        @Override
        public String getCategory() {
            return "MockCategory";
        }

        @Override
        public String getInfo() {
            return "MockInfo";
        }

        @Override
        public void decreaseQuantity(int quantity) {
        }

        @Override
        public String toString() {
            return "MockProduct{" +
                    "productId='" + productId + '\'' +
                    ", productName='" + productName + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    '}';
        }
    }
}
