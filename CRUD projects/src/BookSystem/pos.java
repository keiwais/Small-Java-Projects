package BookSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class pos extends JFrame implements ActionListener {
    private JTextField codeField, titleField, typeField, priceField, quantityField, totalField, cashField, changeField;
    private JButton search, compute, computeChange, purchase, cancel;
    private Connection connection;
    private int availableStock = 0;

    public pos() {
        super("POS");

        String url = "jdbc:mysql://localhost:3306/db2";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        setLayout(null);

        JLabel header = new JLabel("BOOKSHOP INFORMATION SYSTEM", SwingConstants.CENTER);
        header.setBounds(50, 10, 450, 30);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setOpaque(true);
        header.setBackground(new Color(61, 105, 138));
        header.setForeground(Color.WHITE);
        add(header);

        JLabel code = new JLabel("BOOK CODE:");
        code.setBounds(50, 60, 100, 25);
        add(code);
        codeField = new JTextField();
        codeField.setBounds(160, 60, 200, 25);
        add(codeField);
        search = new JButton("SEARCH");
        search.setBounds(370, 60, 100, 25);
        search.setBackground(Color.WHITE);
        search.setFocusPainted(false);
        search.addActionListener(this);
        add(search);

        JLabel title = new JLabel("BOOK TITLE:");
        title.setBounds(50, 100, 100, 25);
        add(title);
        titleField = new JTextField();
        titleField.setBounds(160, 100, 310, 25);
        titleField.setEditable(false);
        add(titleField);

        JLabel type = new JLabel("BOOK TYPE:");
        type.setBounds(50, 140, 100, 25);
        add(type);
        typeField = new JTextField();
        typeField.setBounds(160, 140, 310, 25);
        typeField.setEditable(false);
        add(typeField);

        JLabel price = new JLabel("BOOK PRICE:");
        price.setBounds(50, 180, 100, 25);
        add(price);
        priceField = new JTextField();
        priceField.setBounds(160, 180, 310, 25);
        priceField.setEditable(false);
        add(priceField);

        JLabel quantity = new JLabel("QUANTITY:");
        quantity.setBounds(50, 220, 100, 25);
        add(quantity);
        quantityField = new JTextField();
        quantityField.setBounds(160, 220, 200, 25);
        add(quantityField);
        compute = new JButton("COMPUTE");
        compute.setBounds(370, 220, 100, 25);
        compute.setBackground(Color.WHITE);
        compute.setFocusPainted(false);
        compute.addActionListener(this);
        add(compute);

        JLabel total = new JLabel("TOTAL AMOUNT:");
        total.setBounds(50, 260, 100, 25);
        add(total);
        totalField = new JTextField();
        totalField.setBounds(160, 260, 310, 25);
        totalField.setEditable(false);
        add(totalField);

        JLabel cash = new JLabel("CASH:");
        cash.setBounds(50, 300, 100, 25);
        add(cash);
        cashField = new JTextField();
        cashField.setBounds(160, 300, 200, 25);
        add(cashField);

        computeChange = new JButton("COMPUTE");
        computeChange.setBounds(370, 300, 100, 25);
        computeChange.setBackground(Color.WHITE);
        computeChange.setFocusPainted(false);
        computeChange.addActionListener(this);
        add(computeChange);

        JLabel change = new JLabel("CHANGE:");
        change.setBounds(50, 340, 100, 25);
        add(change);
        changeField = new JTextField();
        changeField.setBounds(160, 340, 310, 25);
        changeField.setEditable(false);
        add(changeField);

        purchase = new JButton("PURCHASE");
        purchase.setBounds(160, 390, 120, 30);
        purchase.setBackground(Color.WHITE);
        purchase.setFocusPainted(false);
        purchase.addActionListener(this);
        add(purchase);

        cancel = new JButton("CANCEL");
        cancel.setBounds(290, 390, 120, 30);
        cancel.setBackground(Color.WHITE);
        cancel.setFocusable(true);
        cancel.setFocusPainted(true);
        cancel.addActionListener(this);
        add(cancel);

        setSize(550, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            try {
                String sql = "SELECT * FROM book WHERE BookCode = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(codeField.getText()));
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    titleField.setText(rs.getString("Title"));
                    typeField.setText(rs.getString("Type"));
                    priceField.setText(String.valueOf(rs.getDouble("Price")));
                    availableStock = rs.getInt("Quantity");
                } else {
                    JOptionPane.showMessageDialog(this, "Book not found.", "ERROR 404 NOT FOUND", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter a Book Code to search.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == compute) {
            try {
                if (priceField.getText().isEmpty() || quantityField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Something went wrong! Enter valid input!", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int quantity = Integer.parseInt(quantityField.getText());
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity must be positive and greater than zero.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (quantity > availableStock) {
                    JOptionPane.showMessageDialog(this, "Not enough stock available.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double price = Double.parseDouble(priceField.getText());
                if (price <= 0) {
                    JOptionPane.showMessageDialog(this, "Price must be positive and greater than zero.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                totalField.setText(String.valueOf(price * quantity));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Something went wrong! Enter valid input!", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == computeChange) {
            try {
                double total = Double.parseDouble(totalField.getText());
                double cash = Double.parseDouble(cashField.getText());
                if (cash <= 0) {
                    JOptionPane.showMessageDialog(this, "Cash must be positive and greater than zero.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (cash < total) {
                    JOptionPane.showMessageDialog(this, "Insufficient cash.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                changeField.setText(String.valueOf(cash - total));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Something went wrong! Enter valid input!", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == purchase) {
            if (codeField.getText().isEmpty() || titleField.getText().isEmpty() || typeField.getText().isEmpty() ||
                    priceField.getText().isEmpty() || quantityField.getText().isEmpty() ||
                    totalField.getText().isEmpty() || cashField.getText().isEmpty() || changeField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled in!", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int qtyPurchased = Integer.parseInt(quantityField.getText());
                if (qtyPurchased <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity must be positive and greater than zero.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (qtyPurchased > availableStock) {
                    JOptionPane.showMessageDialog(this, "Not enough stock available.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double price = Double.parseDouble(priceField.getText());
                if (price <= 0) {
                    JOptionPane.showMessageDialog(this, "Invalid Price! Must be positive and greater than zero.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double cash = Double.parseDouble(cashField.getText());
                if (cash <= 0) {
                    JOptionPane.showMessageDialog(this, "Cash must be positive and greater than zero.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double totalSales = Double.parseDouble(totalField.getText());

                String getSalesSQL = "SELECT Sales FROM book WHERE BookCode = ?";
                PreparedStatement psGetSales = connection.prepareStatement(getSalesSQL);
                psGetSales.setInt(1, Integer.parseInt(codeField.getText()));
                ResultSet rsSales = psGetSales.executeQuery();
                if (rsSales.next()) {
                    double currentSales = rsSales.getDouble("Sales");

                    double newSales = currentSales + totalSales;

                    String updateSQL = "UPDATE book SET Quantity = Quantity - ?, Sales = ? WHERE BookCode = ?";
                    PreparedStatement psUpdate = connection.prepareStatement(updateSQL);
                    psUpdate.setInt(1, qtyPurchased);
                    psUpdate.setDouble(2, newSales);
                    psUpdate.setInt(3, Integer.parseInt(codeField.getText()));

                    int rowsAffected = psUpdate.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Purchase complete!");

                        new menu();
                        dispose();
                        availableStock -= qtyPurchased;
                    } else {
                        JOptionPane.showMessageDialog(this, "Purchase failed!", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ERROR 404 " + ex.getMessage());
            }
        } else if (e.getSource() == cancel) {
            new menu();
            dispose();
        }
    }

    public static void main(String[] args) {
        new pos();
    }
}
