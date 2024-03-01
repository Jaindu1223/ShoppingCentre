package Testing;

import Manager.Clothing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClothingTest {

    private Clothing clothing;

    @BeforeEach
    void setUp() {  // You can provide sample data for the Clothing constructor
        clothing = new Clothing("2", "T-Shirt", 10, 19.99, "M", "Blue");
    }

    @Test
    void getSize_ShouldReturnCorrectValue() {
        String size = clothing.getSize();  // Act
        assertEquals("M", size);  // Assert
    }

    @Test
    void getColor_ShouldReturnCorrectValue() {
        String color = clothing.getColor();   // Act
        assertEquals("Blue", color);  // Assert
    }

    @Test
    void setSize_ShouldSetSizeCorrectly() {
        clothing.setSize("L");  // Act
        assertEquals("L", clothing.getSize());  // Assert
    }

    @Test
    void setColor_ShouldSetColorCorrectly() {
        clothing.setColor("Red");  // Act
        assertEquals("Red", clothing.getColor());   // Assert
    }

    @Test
    void getCategory_ShouldReturnCorrectCategory() {
        String category = clothing.getCategory();  // Act
        assertEquals("Clothing", category);  // Assert
    }

    @Test
    void getInfo_ShouldReturnCorrectInfo() {
        String info = clothing.getInfo(); // Act
        assertEquals("BlueM", info); // Assert
    }
}
