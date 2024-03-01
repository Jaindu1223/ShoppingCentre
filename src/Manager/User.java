package Manager;

import java.io.IOException;

public class User {

    private String username;        // Instance variables
    private String password;
    private int purchaseCount;


    // Constructors are below
    public User(String username, String password) throws IOException, ClassNotFoundException { // Constructor for creating a new user with an initial purchase count of 0
        this.username = username;
        this.password = password;
        this.purchaseCount = 0;
    }

    public User(String username, String password, int purchaseCount) {  // Constructor for creating a user with a specified purchase count
        this.username = username;
        this.password = password;
        this.purchaseCount = purchaseCount;
    }

    public String getUsername() {
        return username;
    }   // Getters

    public String getPassword() {
        return password;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setUsername(String username) {
        this.username = username;
    }       // Setters

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public void increasePurchaseCount() {
        this.purchaseCount++;
    }       // Increase purchase count by 1
}
