package BookSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class view extends JFrame implements ActionListener {
    private JTable table;
    private Connection connection;
    private JButton back;

    public view() {
        super("VIEW BOOK");

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

        JLabel tableNameLabel = new JLabel("BOOK SHOP INFORMATION SYSTEM", SwingConstants.CENTER);
        tableNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"BookCode", "Title", "Type", "Price", "Quantity"});

        try {
            String sql = "SELECT * FROM book";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int BookCode = resultSet.getInt("BookCode");
                String Title = resultSet.getString("Title");
                String Type = resultSet.getString("Type");
                double Price = resultSet.getDouble("Price");
                int Quantity = resultSet.getInt("Quantity");
                model.addRow(new Object[]{BookCode, Title, Type, Price, Quantity});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to fetch records from the database.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(tableNameLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);

        back = new JButton("BACK");
        back.setFocusPainted(false);
        back.setBackground(Color.WHITE);
        back.setPreferredSize(new Dimension(80, 30));
        back.addActionListener(this);
        JPanel panel1 = new JPanel();
        panel1.add(back);
        add(panel1, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new menu();
            dispose();
        }
    }

    public static void main(String[] args) {
        new view();
    }
}
