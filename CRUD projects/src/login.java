import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login extends JFrame implements ActionListener {

    private JTextField userText;
    private JPasswordField passText;
    private JButton login, cancel;

    public login() {
        super("ENROLLMENT LOG IN FORM");
        setSize(400, 245);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("ENROLLMENT INFORMATION SYSTEM", SwingConstants.CENTER);
        title.setBounds(25, 10, 350, 25);
        title.setForeground(new Color(15, 36, 62, 255));
        title.setFont(new Font("Arial", Font.BOLD, 17));
        add(title);

        JLabel username = new JLabel("USERNAME:");
        username.setBounds(50, 50, 200, 25);
        username.setForeground(new Color(15, 36, 62, 255));
        username.setFont(new Font("Arial", Font.BOLD, 16));
        add(username);

        userText = new RoundedTextField(20);
        userText.setBounds(150, 50, 200, 25);
        add(userText);

        JLabel pass = new JLabel("PASSWORD:");
        pass.setBounds(50, 90, 200, 25);
        pass.setForeground(new Color(15, 36, 62, 255));
        pass.setFont(new Font("Arial", Font.BOLD, 16));
        add(pass);

        passText = new RoundedPasswordField(20);
        passText.setBounds(150, 90, 200, 25);
        add(passText);

        login = new RoundedButton("LOGIN");
        login.setBounds(85, 140, 100, 40);
        login.setFocusable(false);
        login.setForeground(Color.WHITE);
        login.setBackground(new Color(61, 105, 138));
        login.setFont(new Font("Arial", Font.BOLD, 14));
        add(login);

        cancel = new RoundedButton("CANCEL");
        cancel.setBounds(205, 140, 100, 40);
        cancel.setFocusable(false);
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(new Color(61, 105, 138));
        cancel.setFont(new Font("Arial", Font.BOLD, 14));
        add(cancel);

        login.addActionListener(this);
        cancel.addActionListener(this);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String username = userText.getText();
            String password = new String(passText.getPassword());
            if (username.equals("username") && password.equals("password")) {
                new enrollMenu();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect credentials. Please Try again.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                userText.setText("");
                passText.setText("");
            }
        } else if (e.getSource() == cancel) {
            userText.setText("");
            passText.setText("");
        }
    }


    class RoundedButton extends JButton {
        public RoundedButton(String label) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            FontMetrics fm = g2.getFontMetrics();
            Rectangle r = getBounds();
            String text = getText();
            int x = (r.width - fm.stringWidth(text)) / 2;
            int y = (r.height + fm.getAscent() - fm.getDescent()) / 2;
            g2.setColor(getForeground());
            g2.setFont(getFont());
            g2.drawString(text, x, y);
            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
        }
    }

    class RoundedTextField extends JTextField {
        public RoundedTextField(int size) {
            super(size);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
            g2.dispose();
        }
    }

    class RoundedPasswordField extends JPasswordField {
        public RoundedPasswordField(int size) {
            super(size);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
            g2.dispose();
        }
    }


    public static void main(String[] args) {
        new login();
    }

}
