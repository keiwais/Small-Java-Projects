import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class student extends JFrame implements ActionListener {

    private JLabel id, name, address, course, year;
    private JTextField idText, nameText, addressText, courseText, yearText;
    private JButton search, save, cancel, back, delete;
    private Connection connection;
    private Statement statement;

    public student() {
        super("STUDENT");
        String url = "jdbc:mysql://localhost:3306/DB3";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        setSize(650, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("ENROLLMENT INFORMATION SYSTEM", SwingConstants.CENTER);
        title.setBounds(150, 10, 380, 30);
        title.setForeground(new Color(15, 36, 62, 255));
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title);

        id = new JLabel("STUDENT ID:");
        id.setBounds(20, 55, 140, 25);
        id.setForeground(new Color(15, 36, 62, 255));
        id.setFont(new Font("Arial", Font.BOLD, 16));
        add(id);

        idText = new RoundedTextField(20);
        idText.setBounds(165, 55, 200, 30);
        add(idText);

        search = new RoundedButton("SEARCH");
        search.setBounds(385, 50, 120, 40);
        search.setFocusable(false);
        search.setForeground(Color.WHITE);
        search.setBackground(new Color(61, 105, 138));
        search.setFont(new Font("Arial", Font.BOLD, 14));
        add(search);

        name = new JLabel("STUDENT NAME:");
        name.setBounds(20, 95, 140, 25);
        name.setForeground(new Color(15, 36, 62, 255));
        name.setFont(new Font("Arial", Font.BOLD, 16));
        add(name);

        nameText = new RoundedTextField(20);
        nameText.setBounds(165, 95, 200, 30);
        add(nameText);

        address = new JLabel("ADDRESS:");
        address.setBounds(20, 135, 140, 25);
        address.setForeground(new Color(15, 36, 62, 255));
        address.setFont(new Font("Arial", Font.BOLD, 16));
        add(address);

        addressText = new RoundedTextField(20);
        addressText.setBounds(165, 135, 200, 30);
        add(addressText);

        course = new JLabel("COURSE:");
        course.setBounds(20, 175, 140, 25);
        course.setForeground(new Color(15, 36, 62, 255));
        course.setFont(new Font("Arial", Font.BOLD, 16));
        add(course);

        courseText = new RoundedTextField(20);
        courseText.setBounds(165, 175, 200, 30);
        add(courseText);

        year = new JLabel("YEAR:");
        year.setBounds(20, 215, 140, 25);
        year.setForeground(new Color(15, 36, 62, 255));
        year.setFont(new Font("Arial", Font.BOLD, 16));
        add(year);

        yearText = new RoundedTextField(20);
        yearText.setBounds(165, 215, 200, 30);
        add(yearText);

        back = new RoundedButton("BACK");
        back.setBounds(400, 246, 110, 40);
        back.setFocusable(false);
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(61, 105, 138));
        back.setFont(new Font("Arial", Font.BOLD, 14));
        add(back);

        delete = new RoundedButton("DELETE");
        delete.setBounds(515, 246, 110, 40);
        delete.setFocusable(false);
        delete.setForeground(null);
        delete.setBackground(null);
        delete.setFont(null);
        add(delete);

        save = new RoundedButton("SAVE");
        save.setBounds(150, 266, 100, 40);
        save.setFocusable(false);
        save.setForeground(null);
        save.setBackground(null);
        save.setFont(null);
        add(save);

        cancel = new RoundedButton("CANCEL");
        cancel.setBounds(260, 266, 100, 40);
        cancel.setFocusable(false);
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(new Color(61, 105, 138));
        cancel.setFont(new Font("Arial", Font.BOLD, 14));
        add(cancel);

        idText.setEnabled(true);
        nameText.setEnabled(false);
        addressText.setEnabled(false);
        courseText.setEnabled(false);
        yearText.setEnabled(false);

        search.addActionListener(this);
        back.addActionListener(this);
        delete.addActionListener(this);
        save.addActionListener(this);
        cancel.addActionListener(this);

        save.setEnabled(false);
        delete.setEnabled(false);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String id = idText.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Student ID to search.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                String sql = "SELECT * FROM EnrolledStudents WHERE StudentID = '" + id + "'";
                ResultSet rs = statement.executeQuery(sql);

                if (rs.next()) {
                    idText.setText(rs.getString("StudentID"));
                    nameText.setText(rs.getString("StudentName"));
                    addressText.setText(rs.getString("Address"));
                    courseText.setText(rs.getString("Course"));
                    yearText.setText(String.valueOf(rs.getInt("Year")));

                    nameText.setEnabled(true);
                    addressText.setEnabled(true);
                    courseText.setEnabled(true);
                    yearText.setEnabled(true);
                    save.setEnabled(true);
                    save.setForeground(Color.WHITE);
                    save.setBackground(new Color(61, 105, 138));
                    save.setFont(new Font("Arial", Font.BOLD, 14));
                    delete.setEnabled(true);
                    delete.setForeground(Color.WHITE);
                    delete.setBackground(new Color(61, 105, 138));
                    delete.setFont(new Font("Arial", Font.BOLD, 14));
                } else {
                    JOptionPane.showMessageDialog(this, "Student ID not found. Try again.", "ERROR 404 NOT FOUND", JOptionPane.ERROR_MESSAGE);

                    idText.setText("");
                    nameText.setEnabled(false);
                    addressText.setEnabled(false);
                    courseText.setEnabled(false);
                    yearText.setEnabled(false);
                    save.setEnabled(false);
                    save.setForeground(null);
                    save.setBackground(null);
                    save.setFont(null);
                    delete.setEnabled(false);
                    delete.setForeground(null);
                    delete.setBackground(null);
                    delete.setFont(null);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error during search.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                idText.setText("");
                nameText.setText("");
                addressText.setText("");
                courseText.setText("");
                yearText.setText("");
                nameText.setEnabled(false);
                addressText.setEnabled(false);
                courseText.setEnabled(false);
                yearText.setEnabled(false);
                save.setEnabled(false);
                save.setForeground(null);
                save.setBackground(null);
                save.setFont(null);
                delete.setEnabled(false);
                delete.setForeground(null);
                delete.setBackground(null);
                delete.setFont(null);
            }
        }

        if (e.getSource() == save) {
            String id = idText.getText().trim();
            String name = nameText.getText().trim();
            String address = addressText.getText().trim();
            String course = courseText.getText().trim();
            String year = yearText.getText().trim();

            if (id.isEmpty() || name.isEmpty() || address.isEmpty() || course.isEmpty() || year.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill out all fields before saving.", "INPUT ERROR 404", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                String sql = "UPDATE EnrolledStudents SET StudentName = '" + name +
                        "', Address = '" + address +
                        "', Course = '" + course +
                        "', Year = " + Integer.parseInt(year) +
                        " WHERE StudentID = '" + id + "'";
                int rowsUpdated = statement.executeUpdate(sql);
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Student record saved successfully.");
                    idText.setText("");
                    nameText.setText("");
                    addressText.setText("");
                    courseText.setText("");
                    yearText.setText("");
                    nameText.setEnabled(false);
                    addressText.setEnabled(false);
                    courseText.setEnabled(false);
                    yearText.setEnabled(false);
                    save.setEnabled(false);
                    save.setForeground(null);
                    save.setBackground(null);
                    save.setFont(null);
                    delete.setEnabled(false);
                    delete.setForeground(null);
                    delete.setBackground(null);
                    delete.setFont(null);
                } else {
                    JOptionPane.showMessageDialog(this, "Save failed. Student ID may not exist.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    idText.setText("");
                    nameText.setText("");
                    addressText.setText("");
                    courseText.setText("");
                    yearText.setText("");
                    nameText.setEnabled(false);
                    addressText.setEnabled(false);
                    courseText.setEnabled(false);
                    yearText.setEnabled(false);
                    save.setEnabled(false);
                    save.setForeground(null);
                    save.setBackground(null);
                    save.setFont(null);
                    delete.setEnabled(false);
                    delete.setForeground(null);
                    delete.setBackground(null);
                    delete.setFont(null);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error during saving.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                idText.setText("");
                nameText.setText("");
                addressText.setText("");
                courseText.setText("");
                yearText.setText("");
                nameText.setEnabled(false);
                addressText.setEnabled(false);
                courseText.setEnabled(false);
                yearText.setEnabled(false);
                save.setEnabled(false);
                save.setForeground(null);
                save.setBackground(null);
                save.setFont(null);
                delete.setEnabled(false);
                delete.setForeground(null);
                delete.setBackground(null);
                delete.setFont(null);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Year must be a valid number.", "INPUT ERROR 404", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == delete) {
            try {
                String sql = "DELETE FROM EnrolledStudents WHERE StudentID = '" + idText.getText().trim() + "'";
                statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Student record deleted successfully.");

                idText.setText("");
                nameText.setText("");
                addressText.setText("");
                courseText.setText("");
                yearText.setText("");
                nameText.setEnabled(false);
                addressText.setEnabled(false);
                courseText.setEnabled(false);
                yearText.setEnabled(false);
                save.setEnabled(false);
                save.setForeground(null);
                save.setBackground(null);
                save.setFont(null);
                delete.setEnabled(false);
                delete.setForeground(null);
                delete.setBackground(null);
                delete.setFont(null);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error during delete.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == cancel) {
            idText.setText("");
            nameText.setText("");
            addressText.setText("");
            courseText.setText("");
            yearText.setText("");
            nameText.setEnabled(false);
            addressText.setEnabled(false);
            courseText.setEnabled(false);
            yearText.setEnabled(false);
            save.setEnabled(false);
            save.setForeground(null);
            save.setBackground(null);
            save.setFont(null);
            delete.setEnabled(false);
            delete.setForeground(null);
            delete.setBackground(null);
            delete.setFont(null);
        }

        if (e.getSource() == back) {
            new enrollMenu();
            dispose();
        }
    }

    class RoundedButton extends JButton {
        public RoundedButton(String label) {
            super(label);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setOpaque(false);
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 14));
            setMargin(new Insets(0, 0, 0, 0));
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);

        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground().darker());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            g2.dispose();
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

    public static void main(String[] args) {
        new student();
    }
}
