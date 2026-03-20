package BookSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class update extends JFrame implements ActionListener {
    private JTextField BookCodeField, titleField, typeField, priceField, quantityField;
    private JButton search, save, cancel;
    private Connection connection;
    private Statement statement;

    public update() {
        super("BOOK UPDATE");

        String url = "jdbc:mysql://localhost:3306/db2";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "ERROR 404" , JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        JLabel label = new JLabel("BOOKSHOP INFORMATION SYSTEM", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setOpaque(true);
        label.setBackground(new Color(61, 105, 138));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(100, 40));

        search = new JButton("SEARCH");
        search.setBackground(Color.WHITE);
        search.setFocusPainted(false);
        search.addActionListener(this);

        header.add(label, BorderLayout.CENTER);
        header.add(search, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        BookCodeField = new JTextField(10);
        titleField = new JTextField(10);
        typeField = new JTextField(10);
        priceField = new JTextField(10);
        quantityField = new JTextField(10);

        titleField.setEnabled(false);
        typeField.setEnabled(false);
        priceField.setEnabled(false);
        quantityField.setEnabled(false);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("BOOK CODE:"));
        panel.add(BookCodeField);
        panel.add(new JLabel("BOOK TITLE:"));
        panel.add(titleField);
        panel.add(new JLabel("BOOK TYPE:"));
        panel.add(typeField);
        panel.add(new JLabel("BOOK PRICE:"));
        panel.add(priceField);
        panel.add(new JLabel("QUANTITY:"));
        panel.add(quantityField);


        save = new JButton("SAVE");
        cancel = new JButton("CANCEL");

        save.setBackground(Color.WHITE);
        cancel.setBackground(Color.WHITE);
        save.setFocusPainted(false);
        cancel.setFocusPainted(false);

        save.addActionListener(this);
        cancel.addActionListener(this);

        save.setEnabled(false);

        panel.add(save);
        panel.add(cancel);

        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String codeText = BookCodeField.getText().trim();
            if (codeText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Book Code to search.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int code = Integer.parseInt(codeText);
                statement = connection.createStatement();
                String sql = "SELECT * FROM book WHERE BookCode = " + code;
                ResultSet rs = statement.executeQuery(sql);

                if (rs.next()) {
                    titleField.setText(rs.getString("Title"));
                    typeField.setText(rs.getString("Type"));
                    priceField.setText(String.valueOf(rs.getDouble("Price")));
                    quantityField.setText(String.valueOf(rs.getInt("Quantity")));

                    titleField.setEnabled(true);
                    typeField.setEnabled(true);
                    priceField.setEnabled(true);
                    quantityField.setEnabled(true);
                    save.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Book not found. Try again.", "ERROR 404 NOT FOUND", JOptionPane.ERROR_MESSAGE);

                    titleField.setEnabled(false);
                    typeField.setEnabled(false);
                    priceField.setEnabled(false);
                    quantityField.setEnabled(false);
                    save.setEnabled(false);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error during search.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for Book Code.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == save) {
            String codeText = BookCodeField.getText().trim();
            String title = titleField.getText().trim();
            String type = typeField.getText().trim();
            String priceText = priceField.getText().trim();
            String quantityText = quantityField.getText().trim();

            if (title.isEmpty() || type.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled in!", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int code = Integer.parseInt(codeText);
                double price = Double.parseDouble(priceText);
                int quantity = Integer.parseInt(quantityText);

                if (price <= 0) {
                    JOptionPane.showMessageDialog(this, "Price must be a positive number and greater than zero.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (quantity < 0) {
                    JOptionPane.showMessageDialog(this, "Quantity cannot be negative.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                statement = connection.createStatement();
                String sql = "UPDATE book SET Title = '" + title + "', Type = '" + type + "', Price = " + price +
                        ", Quantity = " + quantity + " WHERE BookCode = " + code;
                int rowsAffected = statement.executeUpdate(sql);

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Book updated successfully.");
                    new menu();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to BookSystem.update book. Book not found.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid data.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancel) {
            new menu();
            dispose();
        }
    }

    public static void main(String[] args) {
        new update();
    }
}
