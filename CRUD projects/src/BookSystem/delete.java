package BookSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class delete extends JFrame implements ActionListener {
    private JTextField codeField, titleField, typeField, priceField, quantityField;
    private JButton search, delete, cancel;
    private Connection connection;

    public delete() {
        super("DELETE BOOK");

        String url = "jdbc:mysql://localhost:3306/db2";
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

        JLabel header = new JLabel("BOOKSHOP INFORMATION SYSTEM", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setOpaque(true);
        header.setBackground(new Color(61, 105, 138));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 40));
        add(header, BorderLayout.NORTH);

        codeField = new JTextField(10);
        titleField = new JTextField(10);
        typeField = new JTextField(10);
        priceField = new JTextField(10);
        quantityField = new JTextField(10);

        titleField.setEditable(false);
        typeField.setEditable(false);
        priceField.setEditable(false);
        quantityField.setEditable(false);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("BOOK CODE:"));
        panel.add(codeField);
        panel.add(new JLabel("BOOK TITLE:"));
        panel.add(titleField);
        panel.add(new JLabel("BOOK TYPE:"));
        panel.add(typeField);
        panel.add(new JLabel("BOOK PRICE:"));
        panel.add(priceField);
        panel.add(new JLabel("QUANTITY:"));
        panel.add(quantityField);

        search = new JButton("SEARCH");
        delete = new JButton("DELETE");
        cancel = new JButton("CANCEL");

        search.setBackground(Color.WHITE);
        delete.setBackground(Color.WHITE);
        cancel.setBackground(Color.WHITE);

        search.setFocusPainted(false);
        delete.setFocusPainted(false);
        cancel.setFocusPainted(false);

        search.addActionListener(this);
        delete.addActionListener(this);
        cancel.addActionListener(this);

        delete.setEnabled(false);

        panel.add(search);
        panel.add(delete);

        add(panel, BorderLayout.CENTER);
        add(cancel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String codeText = codeField.getText().trim();
            if (codeText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Book Code to search.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int code = Integer.parseInt(codeText);
                String sql = "SELECT * FROM book WHERE BookCode = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, code);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    titleField.setText(rs.getString("Title"));
                    typeField.setText(rs.getString("Type"));
                    priceField.setText(String.valueOf(rs.getDouble("Price")));
                    quantityField.setText(String.valueOf(rs.getInt("Quantity")));
                    delete.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "No book found with the specified code.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    delete.setEnabled(false);
                    titleField.setText("");
                    typeField.setText("");
                    priceField.setText("");
                    quantityField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for Book Code.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to search in the database.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == delete) {
            int code = Integer.parseInt(codeField.getText().trim());
            try {
                String sql = "DELETE FROM book WHERE BookCode = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, code);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Book deleted successfully.");
                    new menu();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No book found with the specified code.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to BookSystem.delete book from the database.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancel) {
            new menu();
            dispose();
        }
    }

    public static void main(String[] args) {
        new delete();
    }
}
