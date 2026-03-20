package BookSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class inventory extends JFrame implements ActionListener {
    private JTable table;
    private Connection connection;
    private JButton mainMenu, cancel;

    public inventory() {
        super("INVENTORY");

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

        JLabel heading = new JLabel("BOOKSHOP INFORMATION SYSTEM INVENTORY", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        heading.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"BOOK CODE", "BOOK TITLE", "BOOK TYPE", "BOOK PRICE", "QUANTITY", "SALES"});

        try {
            String sql = "SELECT * FROM book";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int code = rs.getInt("BookCode");
                String title = rs.getString("Title");
                String type = rs.getString("Type");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                double sales = rs.getInt("Sales");
                model.addRow(new Object[]{code, title, type, price, quantity, sales});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to fetch records.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(heading, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainMenu = new JButton("MAIN MENU");
        mainMenu.setFocusPainted(false);
        cancel = new JButton("CANCEL");
        cancel.setFocusPainted(false);
        mainMenu.addActionListener(this);
        cancel.addActionListener(this);

        mainMenu.setBackground(Color.WHITE);
        cancel.setBackground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(mainMenu);
        buttonPanel.add(cancel);

        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenu) {
            new login();
            dispose();
        } else if (e.getSource() == cancel) {
            new menu();
            dispose();
        }
    }

    public static void main(String[] args) {
        new inventory();
    }
}
