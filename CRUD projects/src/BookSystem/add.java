package BookSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class add extends JFrame implements ActionListener {
    private JTextField BookCodeField, TitleField, TypeField, PriceField, QuantityField;
    private JButton add, cancel;
    private Connection connection;

    public add() {
        super("ADD BOOK");

        String url = "jdbc:mysql://localhost:3306/DB2";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        setLayout(new BorderLayout());

        JLabel title = new JLabel("BOOKSHOP INFORMATION SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setOpaque(true);
        title.setBackground(new Color(61, 105, 138));
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(100, 40));
        add(title, BorderLayout.NORTH);

        BookCodeField = new JTextField(10);
        TitleField = new JTextField(10);
        TypeField = new JTextField(10);
        PriceField = new JTextField(10);
        QuantityField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("BOOK CODE:"));
        panel.add(BookCodeField);
        panel.add(new JLabel("BOOK TITLE:"));
        panel.add(TitleField);
        panel.add(new JLabel("BOOK TYPE:"));
        panel.add(TypeField);
        panel.add(new JLabel("BOOK PRICE:"));
        panel.add(PriceField);
        panel.add(new JLabel("QUANTITY:"));
        panel.add(QuantityField);

        add = new JButton("ADD");
        cancel = new JButton("CANCEL");
        add.setBackground(Color.WHITE);
        cancel.setBackground(Color.WHITE);
        add.addActionListener(this);
        cancel.addActionListener(this);
        add.setFocusPainted(false);
        cancel.setFocusPainted(false);

        panel.add(add);
        panel.add(cancel);

        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String codeText = BookCodeField.getText().trim();
            String title = TitleField.getText().trim();
            String type = TypeField.getText().trim();
            String priceText = PriceField.getText().trim();
            String quantityText = QuantityField.getText().trim();

            if (codeText.isEmpty() || title.isEmpty() || type.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled in!", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int bookCode = Integer.parseInt(codeText);
                if (bookCode <= 0) {
                    JOptionPane.showMessageDialog(this, "Book code must be a positive number.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double price = Double.parseDouble(priceText);
                if (price <= 0) {
                    JOptionPane.showMessageDialog(this, "Price must be a positive number and greater than zero.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int quantity = Integer.parseInt(quantityText);
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity cannot be negative and less than one.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (bookExists(bookCode)) {
                    JOptionPane.showMessageDialog(this, "Book with this code already exists!", "DUPLICATE ENTRY", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "INSERT INTO Book (BookCode, Title, Type, Price, Quantity) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, bookCode);
                ps.setString(2, title);
                ps.setString(3, type);
                ps.setDouble(4, price);
                ps.setInt(5, quantity);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "New book added!", "ADDED SUCCESSFULLY", JOptionPane.PLAIN_MESSAGE);

                new menu();
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to insert data into the database.", "DATABASE ERROR 404", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancel) {
            new menu();
            dispose();
        }
    }

    private boolean bookExists(int bookCode) {
        try {
            String checkSql = "SELECT * FROM Book WHERE BookCode = ?";
            PreparedStatement ps = connection.prepareStatement(checkSql);
            ps.setInt(1, bookCode);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Something went wrong. Try again later.", "DATABASE ERROR 404", JOptionPane.ERROR_MESSAGE);
            return true;
        }
    }

    public static void main(String[] args) {
        new add();
    }
}
