import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class reportStudents extends JFrame {
    private JButton subject, back;

    public reportStudents() {
        super("REPORT – LIST OF ENROLLED STUDENTS");
        setLayout(null);
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("REPORT – LIST OF ENROLLED STUDENTS", SwingConstants.CENTER);
        title.setBounds(200, 10, 380, 30);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title);

        String[] columnNames = {"STUDENT ID", "STUDENT NAME", "ADDRESS", "COURSE", "YEAR"};
        String[][] data = new String[1000][5];
        int rowCount = 0;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB3", "root", "");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM EnrolledStudents");

            while (rs.next()) {
                data[rowCount][0] = rs.getString("StudentID");
                data[rowCount][1] = rs.getString("StudentName");
                data[rowCount][2] = rs.getString("Address");
                data[rowCount][3] = rs.getString("Course");
                data[rowCount][4] = String.valueOf(rs.getInt("Year"));
                rowCount++;
            }

            String[][] finalData = new String[rowCount][5];
            for (int i = 0; i < rowCount; i++) {
                System.arraycopy(data[i], 0, finalData[i], 0, 5);
            }

            JTable table = new JTable(finalData, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(20, 40, 740, 200);
            add(scrollPane);

            JLabel totalLabel = new JLabel("TOTAL # OF ENROLLED STUDENTS: " + rowCount);
            totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
            totalLabel.setBounds(20, 260, 300, 30);
            add(totalLabel);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }

        subject = createRoundedButton("SUBJECT");
        subject.setBounds(655, 300, 120, 40);
        add(subject);

        back = createRoundedButton("BACK");
        back.setBounds(655, 250, 120, 40);
        add(back);

        subject.addActionListener(e -> {
            new reportSubjects();
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
        new reportStudents();
    }
}
