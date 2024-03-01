/*
package UserGUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class SignUpPage implements ActionListener {

    private JFrame jFrame = new JFrame("SignUp Page");
    private JLabel signUpLabel = new JLabel();
    private JLabel informationLabel = new JLabel("Set up your username and password");
    private JLabel enterUsernameLabel = new JLabel("Username : ");
    private JLabel enterPasswordLabel = new JLabel("Password : ");
    private JLabel reEnterPasswordLabel = new JLabel("Re-enter Password : ");
    private JTextField enterUsernameField = new JTextField();
    private JPasswordField enterPasswordField = new JPasswordField();
    private JPasswordField reEnterPasswordField = new JPasswordField();
    private JButton confirmButton = new JButton("Confirm");
    private JLabel successMessage = new JLabel();
    private IdPsw userInformation;

    SignUpPage() {
        userInformation = new IdPsw();

        signUpLabel.setBounds(50, 20, 100, 25);
        signUpLabel.setFont(new Font(null, Font.BOLD, 15));
        signUpLabel.setText("SignUp");

        informationLabel.setBounds(50, 60, 250, 25);

        enterUsernameLabel.setBounds(50, 110, 130, 25);
        enterPasswordLabel.setBounds(50, 160, 130, 25);
        reEnterPasswordLabel.setBounds(50, 210, 130, 25);

        enterUsernameField.setBounds(180, 110, 170, 25);
        enterPasswordField.setBounds(180, 160, 170, 25);
        reEnterPasswordField.setBounds(180, 210, 170, 25);

        confirmButton.setBounds(245, 275, 100, 25);
        confirmButton.setFocusable(false);
        confirmButton.addActionListener(this);

        successMessage.setBounds(50, 300, 300, 25);

        jFrame.add(signUpLabel);
        jFrame.add(informationLabel);
        jFrame.add(enterUsernameLabel);
        jFrame.add(enterPasswordLabel);
        jFrame.add(reEnterPasswordLabel);
        jFrame.add(enterUsernameField);
        jFrame.add(enterPasswordField);
        jFrame.add(reEnterPasswordField);
        jFrame.add(confirmButton);
        jFrame.add(successMessage);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(420, 420);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            String username = enterUsernameField.getText();
            String password = String.valueOf(enterPasswordField.getPassword());
            String reEnterPassword = String.valueOf(reEnterPasswordField.getPassword());

            if (!password.equals(reEnterPassword)) {
                successMessage.setForeground(Color.red);
                successMessage.setText("Passwords do not match");
            } else if (userInformation.userExists(username)) {
                successMessage.setForeground(Color.red);
                successMessage.setText("Username already exists. Try again");
            } else {
                userInformation.addUser(username, password);
                try {
                    userInformation.saveUsers();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                successMessage.setForeground(Color.black);
                successMessage.setText("You have signed up successfully!");

                // Open the login page after successful signup
                openLoginPage();

                jFrame.dispose(); // Close the signup page
            }
        }
    }

    private void openLoginPage() {
        SwingUtilities.invokeLater(() -> {
            HashMap<String, String> loginInfo = userInformation.getLoginInfo();
            new Login(loginInfo);
        });
    }

}*/

package UserGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import UserGUI.IdPsw;
import UserGUI.Login;

public class SignUpPage implements ActionListener {
    private JFrame jFrame = new JFrame("SignUp Page");
    private JLabel signUpLabel = new JLabel();
    private JLabel informationLabel = new JLabel("Set up your username and password");
    private JLabel enterUsernameLabel = new JLabel("Username : ");
    private JLabel enterPasswordLabel = new JLabel("Password : ");
    private JLabel reEnterPasswordLabel = new JLabel("Re-enter Password : ");
    private JTextField enterUsernameField = new JTextField();
    private JPasswordField enterPasswordField = new JPasswordField();
    private JPasswordField reEnterPasswordField = new JPasswordField();
    private JButton confirmButton = new JButton("Confirm");
    private JLabel successMessage = new JLabel();
    private IdPsw userInformation;

    public SignUpPage() {   // Constructor for SignUpPage
        userInformation = new IdPsw();     // Initialize the IdPsw object to manage user information

        signUpLabel.setBounds(50, 20, 100, 25);  // Set up GUI components
        signUpLabel.setFont(new Font(null, Font.BOLD, 15));
        signUpLabel.setText("SignUp");

        informationLabel.setBounds(50, 60, 250, 25);

        enterUsernameLabel.setBounds(50, 110, 130, 25);
        enterPasswordLabel.setBounds(50, 160, 130, 25);
        reEnterPasswordLabel.setBounds(50, 210, 130, 25);

        enterUsernameField.setBounds(180, 110, 170, 25);
        enterPasswordField.setBounds(180, 160, 170, 25);
        reEnterPasswordField.setBounds(180, 210, 170, 25);

        confirmButton.setBounds(245, 275, 100, 25);
        confirmButton.setFocusable(false);
        confirmButton.addActionListener(this);

        successMessage.setBounds(50, 300, 300, 25);

        jFrame.add(signUpLabel);   // Add components to the JFrame
        jFrame.add(informationLabel);
        jFrame.add(enterUsernameLabel);
        jFrame.add(enterPasswordLabel);
        jFrame.add(reEnterPasswordLabel);
        jFrame.add(enterUsernameField);
        jFrame.add(enterPasswordField);
        jFrame.add(reEnterPasswordField);
        jFrame.add(confirmButton);
        jFrame.add(successMessage);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Set JFrame properties
        jFrame.setSize(420, 420);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    @Override   // ActionListener implementation for button click
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) { // Handle button click event for the Confirm button
            String username = enterUsernameField.getText(); // Get user input from the text fields
            String password = String.valueOf(enterPasswordField.getPassword());
            String reEnterPassword = String.valueOf(reEnterPasswordField.getPassword());

            if (!password.equals(reEnterPassword)) {  // Check if passwords match
                successMessage.setForeground(Color.red);
                successMessage.setText("Passwords do not match");
            } else if (userInformation.userExists(username)) { // Check if the username already exists

                successMessage.setForeground(Color.red);
                successMessage.setText("Username already exists. Try again");
            } else {
                userInformation.addUser(username, password);    // Add the user, save information, and display success message
                try {
                    userInformation.saveUsers();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                successMessage.setForeground(Color.black);
                successMessage.setText("You have signed up successfully!");

                openLoginPage();    // Open the login page after successful signup
                jFrame.dispose();  // Close the signup page
            }
        }
    }

    private void openLoginPage() {  // Open the login page
        SwingUtilities.invokeLater(() -> {

            HashMap<String, String> loginInfo = userInformation.getLoginInfo(); // Get login information and open the Login page
            new Login(loginInfo);
        });
    }
}

