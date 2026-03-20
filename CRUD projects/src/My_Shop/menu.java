package My_Shop;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class menu extends JFrame implements ActionListener {
    private final JRadioButton odong, miswa, tinapa, beefloaf;
    private final JSpinner one, two, three, four;
    private final JButton confirm, cancel;

    public menu () {
        super("MY_SHOP");

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String pass = "";

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

        } catch (SQLException e) {
            e.getStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }


        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,2));

        odong = new JRadioButton("Odong");
        miswa = new JRadioButton("Miswa");
        tinapa = new JRadioButton("Tinapa");
        beefloaf = new JRadioButton("Beef Loaf");

        one = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        two = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        three = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        four = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        confirm = new JButton("Confirm");
        cancel = new JButton("Cancel");

        confirm.addActionListener(this);
        cancel.addActionListener(this);



        add(odong);
        add(one);
        add(miswa);
        add(two);
        add(tinapa);
        add(three);
        add(beefloaf);
        add(four);
        add(confirm);
        add(cancel);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirm) {
            JOptionPane.showMessageDialog(this, "Order confirmed!");
            dispose();
        } else if (e.getSource() == cancel) {
            dispose();
        }
    }


    public static void main(String[] args) {
        new menu();
    }
}

