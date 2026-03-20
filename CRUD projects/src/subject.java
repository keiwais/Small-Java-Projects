import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class subject extends JFrame implements ActionListener {

    private JLabel subjectCodeLabel, descriptionLabel, scheduleLabel, teacherLabel, unitsLabel;
    private JTextField subjectCodeText, descriptionText, scheduleText, teacherText, unitsText;
    private JButton search, save, cancel, back, delete;
    private Connection connection;
    private Statement statement;

    public subject() {
        super("SUBJECT");
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

        subjectCodeLabel = new JLabel("SUBJECT CODE:");
        subjectCodeLabel.setBounds(20, 55, 140, 25);
        subjectCodeLabel.setForeground(new Color(15, 36, 62, 255));
        subjectCodeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(subjectCodeLabel);

        subjectCodeText = new RoundedTextField(20);
        subjectCodeText.setBounds(165, 55, 200, 30);
        add(subjectCodeText);

        search = new RoundedButton("SEARCH");
        search.setBounds(385, 50, 120, 40);
        search.setFocusable(false);
        search.setForeground(Color.WHITE);
        search.setBackground(new Color(61, 105, 138));
        search.setFont(new Font("Arial", Font.BOLD, 14));
        add(search);

        descriptionLabel = new JLabel("DESCRIPTION:");
        descriptionLabel.setBounds(20, 95, 140, 25);
        descriptionLabel.setForeground(new Color(15, 36, 62, 255));
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(descriptionLabel);

        descriptionText = new RoundedTextField(20);
        descriptionText.setBounds(165, 95, 200, 30);
        add(descriptionText);

        scheduleLabel = new JLabel("SCHEDULE:");
        scheduleLabel.setBounds(20, 135, 140, 25);
        scheduleLabel.setForeground(new Color(15, 36, 62, 255));
        scheduleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(scheduleLabel);

        scheduleText = new RoundedTextField(20);
        scheduleText.setBounds(165, 135, 200, 30);
        add(scheduleText);

        teacherLabel = new JLabel("TEACHER:");
        teacherLabel.setBounds(20, 175, 140, 25);
        teacherLabel.setForeground(new Color(15, 36, 62, 255));
        teacherLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(teacherLabel);

        teacherText = new RoundedTextField(20);
        teacherText.setBounds(165, 175, 200, 30);
        add(teacherText);

        unitsLabel = new JLabel("UNITS:");
        unitsLabel.setBounds(20, 215, 140, 25);
        unitsLabel.setForeground(new Color(15, 36, 62, 255));
        unitsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(unitsLabel);

        unitsText = new RoundedTextField(20);
        unitsText.setBounds(165, 215, 200, 30);
        add(unitsText);

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

        JLabel label = new JLabel("**1 UNIT = 200.00");
        label.setForeground(new Color(15, 36, 62, 255));
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(490, 295, 380, 30);
        add(label);

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

        subjectCodeText.setEnabled(true);
        descriptionText.setEnabled(false);
        scheduleText.setEnabled(false);
        teacherText.setEnabled(false);
        unitsText.setEnabled(false);

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
            String code = subjectCodeText.getText().trim();
            if (code.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Subject Code to search.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                String sql = "SELECT * FROM EnrolledSubjects WHERE SubjectCode = '" + code + "'";
                ResultSet rs = statement.executeQuery(sql);

                if (rs.next()) {
                    subjectCodeText.setText(rs.getString("SubjectCode"));
                    descriptionText.setText(rs.getString("Description"));
                    scheduleText.setText(rs.getString("Schedule"));
                    teacherText.setText(rs.getString("Teacher"));
                    unitsText.setText(String.valueOf(rs.getInt("Units")));

                    descriptionText.setEnabled(true);
                    scheduleText.setEnabled(true);
                    teacherText.setEnabled(true);
                    unitsText.setEnabled(true);
                    save.setEnabled(true);
                    save.setForeground(Color.WHITE);
                    save.setBackground(new Color(61, 105, 138));
                    save.setFont(new Font("Arial", Font.BOLD, 14));
                    delete.setEnabled(true);
                    delete.setForeground(Color.WHITE);
                    delete.setBackground(new Color(61, 105, 138));
                    delete.setFont(new Font("Arial", Font.BOLD, 14));
                } else {
                    JOptionPane.showMessageDialog(this, "Subject Code not found. Try again.", "ERROR 404 NOT FOUND", JOptionPane.ERROR_MESSAGE);

                    subjectCodeText.setText("");
                    descriptionText.setEnabled(false);
                    scheduleText.setEnabled(false);
                    teacherText.setEnabled(false);
                    unitsText.setEnabled(false);
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
                subjectCodeText.setText("");
                descriptionText.setEnabled(false);
                scheduleText.setEnabled(false);
                teacherText.setEnabled(false);
                unitsText.setEnabled(false);
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
            String code = subjectCodeText.getText().trim();
            String description = descriptionText.getText().trim();
            String schedule = scheduleText.getText().trim();
            String teacher = teacherText.getText().trim();
            String units = unitsText.getText().trim();

            if (code.isEmpty() || description.isEmpty() || schedule.isEmpty() || teacher.isEmpty() || units.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill out all fields before saving.", "INPUT ERROR 404", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                String sql = "UPDATE EnrolledSubjects SET Description = '" + description +
                        "', Schedule = '" + schedule +
                        "', Teacher = '" + teacher +
                        "', Units = " + Integer.parseInt(units) +
                        " WHERE SubjectCode = '" + code + "'";
                int rowsUpdated = statement.executeUpdate(sql);
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Subject record saved successfully.");
                    subjectCodeText.setText("");
                    descriptionText.setText("");
                    scheduleText.setText("");
                    teacherText.setText("");
                    unitsText.setText("");
                    descriptionText.setEnabled(false);
                    scheduleText.setEnabled(false);
                    teacherText.setEnabled(false);
                    unitsText.setEnabled(false);
                    save.setEnabled(false);
                    save.setForeground(null);
                    save.setBackground(null);
                    save.setFont(null);
                    delete.setEnabled(false);
                    delete.setForeground(null);
                    delete.setBackground(null);
                    delete.setFont(null);
                } else {
                    JOptionPane.showMessageDialog(this, "Save failed. Subject Code may not exist.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    subjectCodeText.setText("");
                    descriptionText.setText("");
                    scheduleText.setText("");
                    teacherText.setText("");
                    unitsText.setText("");
                    descriptionText.setEnabled(false);
                    scheduleText.setEnabled(false);
                    teacherText.setEnabled(false);
                    unitsText.setEnabled(false);
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
                subjectCodeText.setText("");
                descriptionText.setText("");
                scheduleText.setText("");
                teacherText.setText("");
                unitsText.setText("");
                descriptionText.setEnabled(false);
                scheduleText.setEnabled(false);
                teacherText.setEnabled(false);
                unitsText.setEnabled(false);
                save.setEnabled(false);
                save.setForeground(null);
                save.setBackground(null);
                save.setFont(null);
                delete.setEnabled(false);
                delete.setForeground(null);
                delete.setBackground(null);
                delete.setFont(null);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Units must be a valid number.", "INPUT ERROR 404", JOptionPane.WARNING_MESSAGE);
                subjectCodeText.setText("");
                descriptionText.setText("");
                scheduleText.setText("");
                teacherText.setText("");
                unitsText.setText("");
                descriptionText.setEnabled(false);
                scheduleText.setEnabled(false);
                teacherText.setEnabled(false);
                unitsText.setEnabled(false);
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

        if (e.getSource() == delete) {
            try {
                String sql = "DELETE FROM EnrolledSubjects WHERE SubjectCode = '" + subjectCodeText.getText().trim() + "'";
                statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Subject record deleted successfully.");

                subjectCodeText.setText("");
                descriptionText.setText("");
                scheduleText.setText("");
                teacherText.setText("");
                unitsText.setText("");
                descriptionText.setEnabled(false);
                scheduleText.setEnabled(false);
                teacherText.setEnabled(false);
                unitsText.setEnabled(false);
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
            subjectCodeText.setText("");
            descriptionText.setText("");
            scheduleText.setText("");
            teacherText.setText("");
            unitsText.setText("");
            descriptionText.setEnabled(false);
            scheduleText.setEnabled(false);
            teacherText.setEnabled(false);
            unitsText.setEnabled(false);
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
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground().darker());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
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
        new subject();
    }
}
