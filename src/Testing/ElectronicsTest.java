package Testing;

import Manager.Electronics;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class ElectronicsTest {


    @Test
    public void toString_ShouldNotBeNull() { // Arrange is below
        Electronics electronics = new Electronics("1", "TestElectronics", 15, 100.0, "TestBrand", 12);
        assertNotNull(electronics.toString()); // Act and Assert
    }

    @Test
    public void getCategory_ShouldReturnElectronics() { // Arrange is below
        Electronics electronics = new Electronics("1", "TestElectronics", 15, 100.0, "TestBrand", 12);
        assertEquals("Electronics", electronics.getCategory()); // Act and Assert
    }


}
