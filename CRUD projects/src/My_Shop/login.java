package My_Shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame implements ActionListener {

    private JLabel user, pass;
    private JTextField login, password;
    private JButton submit;

    public login() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));
        setResizable(false);
        setLocationRelativeTo(null);

        user = new JLabel("Username:");
        pass = new JLabel("Password:");
        login = new JTextField();
        password = new JPasswordField();
        submit = new JButton("Log in");
        submit.addActionListener(this);

        add(user);
        add(login);
        add(pass);
        add(password);
        add(new JLabel());
        add(submit);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = null;
        String pwd = null;

        if (e.getSource() == submit) {
             username = login.getText().trim();
             pwd = password.getText().trim();

            if (username.isEmpty() || pwd.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Username: " + username + "\nPassword: " + pwd);
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        new login();
    }
}
