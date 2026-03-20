import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class reportSubjects extends JFrame {
    private JButton student, back;

    public reportSubjects() {
        super("REPORT – LIST OF ENROLLED SUBJECTS");
        setLayout(null);
        setSize(900, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("REPORT – LIST OF ENROLLED SUBJECTS", SwingConstants.CENTER);
        title.setBounds(200, 10, 500, 30);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title);

        String[] columnNames = {"SUBJECT CODE", "DESCRIPTION", "SCHEDULE", "TEACHER", "UNITS", "TOTAL"};
        String[][] data = new String[1000][6];
        int rowCount = 0;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB3", "root", "");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM EnrolledSubjects");

            while (rs.next()) {
                data[rowCount][0] = rs.getString("SubjectCode");
                data[rowCount][1] = rs.getString("Description");
                data[rowCount][2] = rs.getString("Schedule");
                data[rowCount][3] = rs.getString("Teacher");
                data[rowCount][4] = String.valueOf(rs.getInt("Units"));
                data[rowCount][5] = String.valueOf(rs.getInt("Total"));
                rowCount++;
            }

            String[][] finalData = new String[rowCount][6];
            for (int i = 0; i < rowCount; i++) {
                System.arraycopy(data[i], 0, finalData[i], 0, 6);
            }

            JTable table = new JTable(finalData, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(20, 50, 840, 250);
            add(scrollPane);

            JLabel totalLabel = new JLabel("TOTAL # OF SUBJECT ENTRIES: " + rowCount);
            totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
            totalLabel.setBounds(20, 310, 400, 30);
            add(totalLabel);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }

        student = createRoundedButton("STUDENTS");
        student.setBounds(740, 350, 120, 40);
        add(student);

        back = createRoundedButton("BACK");
        back.setBounds(610, 350, 120, 40);
        add(back);

        student.addActionListener(e -> {
            new reportStudents();
            dispose();
        });

        back.addActionListener(e -> {
            new enrollMenu();
            dispose();
        });

        setResizable(false);
        setVisible(true);
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(61, 105, 138));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        return button;
    }

    public static void main(String[] args) {
        new reportSubjects();
    }
}
