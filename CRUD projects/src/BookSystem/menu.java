package BookSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class menu extends JFrame implements ActionListener {
    private JButton add, view, update, delete, pos, inventory;

    public menu() {
        super("MAIN MENU");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("BOOKSHOP INFORMATION SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBounds(50, 20, 300, 30);
        add(title);

        add = new JButton("Add");
        view = new JButton("View");
        update = new JButton("Update");
        delete = new JButton("Delete");
        pos = new JButton("Point of Sale");
        inventory = new JButton("Inventory");

        add.setBounds(125, 70, 150, 50); // hay kakapoy mag mano mano T-T
        view.setBounds(125, 130, 150, 50);
        update.setBounds(125, 190, 150, 50);
        delete.setBounds(125, 250, 150, 50);
        pos.setBounds(125, 310, 150, 50);
        inventory.setBounds(125, 370, 150, 50);

        add(add);
        add.setFocusPainted(false);
        add.setBackground(Color.WHITE);
        add(view);
        view.setFocusPainted(false);
        view.setBackground(Color.WHITE);
        add(update);
        update.setFocusPainted(false);
        update.setBackground(Color.WHITE);
        add(delete);
        delete.setFocusPainted(false);
        delete.setBackground(Color.WHITE);
        add(pos);
        pos.setFocusPainted(false);
        pos.setBackground(Color.WHITE);
        add(inventory);
        inventory.setFocusPainted(false);
        inventory.setBackground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(61, 105, 138));
        title.setForeground(Color.WHITE);
        setBackground(new Color(61, 105, 138));


        add.addActionListener(this);
        view.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);
        pos.addActionListener(this);
        inventory.addActionListener(this);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            new add();
            dispose();
        } else if (e.getSource() == view) {
            new view();
            dispose();
        } else if (e.getSource() == update) {
            new update();
           dispose();
        } else if (e.getSource() == delete) {
            new delete();
           dispose();
        } else if (e.getSource() == pos) {
            new pos();
           dispose();
        } else if (e.getSource() == inventory) {
            new inventory();
            dispose();
        }
    }


    public static void main(String[] args) {
        new menu();
    }
}
