package Testing;

import Manager.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        user = new User("testUser", "testPassword");  // You can provide sample data for the User constructor
    }

    @Test
    void getUsername_ShouldReturnCorrectValue() {

        String username = user.getUsername(); // Act
        assertEquals("testUser", username);  // Assert
    }

    @Test
    void getPassword_ShouldReturnCorrectValue() {

        String password = user.getPassword(); // Act
        assertEquals("testPassword", password);  // Assert
    }

    @Test
    void getPurchaseCount_ShouldReturnCorrectValue() {

        int purchaseCount = user.getPurchaseCount(); // Act
        assertEquals(0, purchaseCount); // Assert
    }

    @Test
    void setUsername_ShouldSetUsernameCorrectly() {
        user.setUsername("newTestUser"); // Act
        assertEquals("newTestUser", user.getUsername()); // Assert
    }

    @Test
    void setPassword_ShouldSetPasswordCorrectly() {
        user.setPassword("newTestPassword"); // Act
        assertEquals("newTestPassword", user.getPassword()); // Assert
    }

    @Test
    void setPurchaseCount_ShouldSetPurchaseCountCorrectly() {
        user.setPurchaseCount(10);  // Act
        assertEquals(10, user.getPurchaseCount());  // Assert
    }

    @Test
    void increasePurchaseCount_ShouldIncreasePurchaseCountByOne() {
        user.increasePurchaseCount(); // Act
        assertEquals(1, user.getPurchaseCount()); // Assert
    }
}
