package UserGUI;

import Manager.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class ShoppingCentre implements ActionListener, ListSelectionListener {
    private User currentUser;
    private JButton checkOutBtn;
    private ArrayList<Product> listOfProducts;
    private JLabel selectCategory = new JLabel("Select Product Category ");
    private JLabel productDetails = new JLabel("Selected Product - Details");
    private JLabel productInfo;
    private JComboBox<String> categories;
    private JButton shoppingCart = new JButton("Shopping Cart");
    private JButton addToShoppingCart = new JButton("Add to Shopping Cart");
    private JTable productTable;
    private DefaultTableModel tableModel;
    private ShoppingCart cart;
    private int userPurchaseCount = 0;

    ShoppingCentre(ArrayList<Product> list, User user) {    // Constructor for the ShoppingCentre class

        this.listOfProducts = list;     // Initialize instance variables
        this.currentUser = user;
        cart = new ShoppingCart();

        JFrame jFrame = new JFrame("Westminster Shopping Centre");      // Create the main frame for the shopping center
        jFrame.setSize(610, 550);

        JPanel jPanel = new JPanel();       // Create a panel to hold the components
        jPanel.setLayout(null);

        selectCategory.setBounds(80, 20, 160, 25);      // Components related to category selection
        jPanel.add(selectCategory);

        shoppingCart.setBounds(430, 10, 150, 25);       // Button to view the shopping cart
        shoppingCart.addActionListener(this);
        jPanel.add(shoppingCart);

        addToShoppingCart.setBounds(200, 470, 170, 25); // Button to add the selected product to the shopping cart
        addToShoppingCart.addActionListener(this);
        jPanel.add(addToShoppingCart);

        categories = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});   // ComboBox for selecting product categories
        categories.setSelectedItem("All");
        categories.setBounds(240, 20, 160, 25);
        categories.addActionListener(this);
        jPanel.add(categories);

        productDetails.setFont(new Font("", Font.BOLD, 12));    // Label for displaying product details
        productDetails.setBounds(30, 320, 200, 25);
        jPanel.add(productDetails);

        productInfo = new JLabel("");   // Label for displaying additional product information
        productInfo.setBounds(30, 340, 200, 125);
        jPanel.add(productInfo);

        tableModel = new DefaultTableModel(new String[]{"Product ID", "Name", "Category", "Price(Rs.)", "Info"}, 0);    // Table and model for displaying products
        for (Product products : listOfProducts) {
            Object[] productSet = {products.getProductId(), products.getProductName(), products.getCategory(), products.getPrice(), products.getInfo()};
            tableModel.addRow(productSet);
        }

        productTable = new JTable(tableModel);  // Create the product table and set column preferences
        TableColumnModel columnModel = productTable.getColumnModel();
        columnModel.getColumn(4).setPreferredWidth(150);
        JScrollPane jScrollPane = new JScrollPane(productTable);
        jScrollPane.setBounds(25, 70, 550, 250);
        jPanel.add(jScrollPane);

        for (int i = 0; i < productTable.getColumnCount(); i++) {   // Apply custom cell renderer for columns except the last one
            if (i != 4) {
                productTable.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());
            }
        }

        productTable.getSelectionModel().addListSelectionListener(this);    // Add ListSelectionListener to respond to product selection

        jFrame.add(jPanel);     // Add panel to the main frame

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       // Set frame properties and make it visible
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }
    private void shoppingCartFrame() {      // Method to create and display the shopping cart frame
        JFrame frame = new JFrame("Shopping Cart"); // Create a new JFrame for the shopping cart
        frame.setSize(600, 450);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        DefaultTableModel model = new DefaultTableModel(    // Create a DefaultTableModel for the shopping cart table
                new String[]{"Product", "Quantity", "Price"}, 0);

        JScrollPane scrollPane = new JScrollPane(new JTable(model));    // Create a JScrollPane to display the shopping cart table
        scrollPane.setBounds(10, 10, 580, 200);
        panel.add(scrollPane);

        ArrayList<Product> products = cart.getCart();   // Get the products in the shopping cart
        model.setRowCount(0);
        double total = 0;
        boolean threeItems = false;
        boolean firstPurchase = false;
        int electronicsCount = 0;
        int clothingCount = 0;
        double discount = 0;

        for (Product product : products) {  // Iterate through the products in the shopping cart
            int quantity = cart.getQuantity(product); // In below, Create an array with product information, quantity, and price
            Object[] arr = {product.getProductId() + ", " + product.getProductName() + ", " + product.getInfo(), quantity, (quantity * product.getPrice())};
            model.addRow(arr);
            total += (quantity * product.getPrice());
            if (product.getCategory().equalsIgnoreCase("Electronics")) {    // Check if the product belongs to the Electronics or Clothing category
                electronicsCount += quantity;
            } else if (product.getCategory().equalsIgnoreCase("Clothing")) {
                clothingCount += quantity;
            }
            if (electronicsCount >= 3 || clothingCount >= 3) {  // Check if there are three or more items in the same category
                threeItems = true;
            }
            if (currentUser.getPurchaseCount() < 1) {   // Check if it's the user's first purchase
                firstPurchase = true;
            }
        }
        JLabel totalL = new JLabel("Total: Rs. " + String.format("%.2f", total));   // Display the total cost of the items in the shopping cart
        totalL.setBounds(400, 250, 200, 30);
        if (threeItems) {   // Apply a discount if there are three or more items in the same category
            discount = (total * 0.20);
            JLabel discountLbl20 = new JLabel("Three items in the same Category Discount (20%): -Rs. " + String.format("%.2f", discount));
            discountLbl20.setBounds(150, 280, 400, 25);
            panel.add(discountLbl20);
        }
        if (firstPurchase) {    // Apply a discount for the user's first purchase
            discount = (total * 0.10);
            JLabel discountLbl10 = new JLabel("First Purchase Discount (10%) : -Rs. " + String.format("%.2f", discount));
            discountLbl10.setBounds(250, 320, 400, 25);
            panel.add(discountLbl10);
        }
        JLabel finalL = new JLabel("Final Total: Rs. " + String.format("%.2f", (total - discount)));    // Display the final total after applying discounts
        finalL.setFont(new Font("", Font.BOLD, 12));
        finalL.setBounds(370, 350, 400, 25);

        checkOutBtn = new JButton("Confirm purchase");  // Button to confirm the purchase
        checkOutBtn.addActionListener(this);
        checkOutBtn.setBounds(430, 380, 100, 25);
        checkOutBtn.setFont(new Font("", Font.BOLD, 12));

        panel.add(totalL);  // Add components to the panel
        panel.add(finalL);
        panel.add(checkOutBtn);

        frame.add(panel);   // Add the panel to the frame

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    // Set frame properties and make it visible
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override // ActionListener implementation
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Shopping Cart")) { // Check if the "Shopping Cart" button is clicked
            shoppingCartFrame(); // Display the shopping cart frame
        }
        else if (e.getActionCommand().equalsIgnoreCase("Add to Shopping Cart")) { // Check if the "Add to Shopping Cart" button is clicked
            int idx = productTable.getSelectedRow();    // Check if a valid product is selected in the table
            if (idx != -1) {
                Product product = listOfProducts.get(idx);
                try {   // Prompt the user to enter the quantity
                    String quantityInput = JOptionPane.showInputDialog("Please Enter Quantity: ");
                    if (quantityInput != null) {
                        int quantity = Integer.parseInt(quantityInput);  // Check if the entered quantity is valid
                        if (quantity > 0 && quantity <= listOfProducts.get(idx).getNoItems()) { // Update product and shopping cart, show a success message
                            listOfProducts.get(idx).decreaseQuantity(quantity);
                            cart.addProductToCart(product, quantity);
                            JOptionPane.showMessageDialog(null, product.getProductName() + " Added To Cart!");
                        } else { // Show an error message for an invalid quantity
                            JOptionPane.showMessageDialog(null, "Invalid Quantity! Please enter a valid quantity.");
                        }
                    } else {
                        // User canceled the input dialog, handle accordingly
                    }
                } catch (NumberFormatException ex) {    // Show an error message for invalid input
                    JOptionPane.showMessageDialog(null, "Invalid Input! Please enter a valid integer quantity.");
                }
            }
        }
        else if (e.getSource() == checkOutBtn) {    // Check if the "Confirm purchase" button is clicked
            clearShoppingCart();    // Perform checkout actions
            incrementUserPurchaseCount();
            JOptionPane.showMessageDialog(null, "Checkout successful!\nYour purchases count has been increased by one.");
        }
        if (e.getActionCommand().equalsIgnoreCase("comboBoxChanged")) { // Check if the category selection in the combo box is changed
            String category = (String) categories.getSelectedItem();
            tableModel.setRowCount(0);    // Clear the existing rows
            for (Product product : listOfProducts) {    // Populate the table based on the selected category
                if (category.equalsIgnoreCase("All") || product.getCategory().equalsIgnoreCase(category)) {
                    Object[] arr = {product.getProductId(), product.getProductName(), product.getCategory(), product.getPrice()};
                    tableModel.addRow(arr);
                }
            }
        }
    }
    private void clearShoppingCart() {  // Method to clear the shopping cart
        cart.clearCart();
    }

    private void incrementUserPurchaseCount() { // Method to increment the user's purchase count
        currentUser.increasePurchaseCount();
    }

    @Override   // ValueChangeListener for the product table selection
    public void valueChanged(ListSelectionEvent e) {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            Product selectedProduct = listOfProducts.get(selectedRow);  // Retrieve selected product details and update the productInfo label
            String productDetails = generateProductDetails(selectedProduct);
            productInfo.setText(productDetails);
        }
    }
    private String generateProductDetails(Product product) {    // Method to generate details for a given product
        String category = product.getCategory();
        StringBuilder stringBuilder = new StringBuilder("<html>"
                + "<b>Product ID:</b> " + product.getProductId() + "<br/>"
                + "<b>Name:</b> " + product.getProductName() + "<br/>"
                + "<b>Category:</b> " + category + "<br/>"
                + "<b>Price:</b> £" + product.getPrice() + "<br/>");

        if (category.equalsIgnoreCase("Electronics")) {  // Check the category to append specific details
            Electronics electronics = (Electronics) product;
            stringBuilder.append("<b>Brand:</b> ").append(electronics.getBrand()).append("<br/>")
                    .append("<b>Warranty Period:</b> ")
                    .append(electronics.getWarrantyPeriod()).append("<br/>");
        } else if (category.equalsIgnoreCase("Clothing")) {
            Clothing c = (Clothing) product;
            stringBuilder.append("<b>Size:</b> ").append(c.getSize()).append("<br/>")
                    .append("<b>Colour:</b> ")
                    .append(c.getColor()).append("<br/>");
        }
        int addedToCart = product.getNoItems(); // Calculate available quantity
        int addedToCart1 = ShoppingCart.quantity;
        int availableItems = Math.max(0, addedToCart - addedToCart1);

        stringBuilder.append("<b>Items Available:</b> ").append(availableItems);    // Display available quantity information
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }

    private class CustomTableCellRenderer extends DefaultTableCellRenderer {    // Custom table cell renderer to apply special formatting to the cells
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column != 5) { // Check if the rowColor should be applied
                int availableItems = listOfProducts.get(row).getAvailableItems();
                Color rowColor = (availableItems < 3) ? Color.RED : Color.BLACK;
                component.setForeground(rowColor);
            }
            return component;
        }
    }
}






