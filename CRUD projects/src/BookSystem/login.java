package BookSystem;// password is password ^-^

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login extends JFrame implements ActionListener {
    private JTextField userText;
    private JPasswordField passText;
    private JButton login, cancel;

    public login() {
        super("BOOKSHOP LOG IN FORM");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("BOOKSHOP INFORMATION SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel user = new JLabel("ENTER USERNAME:");
        userText = new JTextField(15);
        JLabel pass = new JLabel("ENTER PASSWORD:");
        passText = new JPasswordField(15);
        login = new JButton("Log in");
        login.setFocusPainted(false);
        cancel = new JButton("Cancel");
        cancel.setFocusPainted(false);

        login.addActionListener(this);
        cancel.addActionListener(this);

        panel.add(user);
        panel.add(userText);
        panel.add(pass);
        panel.add(passText);
        panel.add(login);
        panel.add(cancel);

        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String password = new String(passText.getPassword());
            if (password.equals("password")) {
                new menu();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect password. Try again.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancel) {
            JOptionPane.showMessageDialog(this, "Exiting Program...", "TERMINATING...", JOptionPane.PLAIN_MESSAGE);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new login();
    }
}
