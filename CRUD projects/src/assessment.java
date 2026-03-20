import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class assessment extends JFrame implements ActionListener {

    private JComboBox<String> studentCombo, subjectCombo;
    private RoundedButton add, cancel, back, print;
    private JTextArea subjectList;
    private JLabel totalTuition;

    private Connection connection;
    private Statement statement;

    private double Tuition = 0;
    private int subjectCount = 0;
    private String[] enrolledSubjects = new String[100];

    public assessment() {
        super("ASSESSMENT");
        setLayout(null);
        setSize(650, 475);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB3", "root", "");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "ERROR 404", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        JLabel title = new JLabel("ENROLLMENT INFORMATION SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(15, 36, 62));
        title.setBounds(130, 10, 400, 30);
        add(title);

        JLabel studentLabel = new JLabel("STUDENT NAME:");
        studentLabel.setBounds(30, 60, 150, 25);
        studentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        studentLabel.setForeground(new Color(15, 36, 62));
        add(studentLabel);

        studentCombo = new JComboBox<>(getStudents());
        studentCombo.setBounds(170, 60, 200, 25);
        studentCombo.setBackground(Color.WHITE);
        add(studentCombo);

        JLabel subjectLabel = new JLabel("SUBJECT CODE:");
        subjectLabel.setBounds(30, 100, 150, 25);
        subjectLabel.setFont(new Font("Arial", Font.BOLD, 16));
        subjectLabel.setForeground(new Color(15, 36, 62));
        add(subjectLabel);

        subjectCombo = new JComboBox<>(getSubjects());
        subjectCombo.setBounds(170, 100, 200, 25);
        subjectCombo.setBackground(Color.WHITE);
        add(subjectCombo);

        add = new RoundedButton("ADD SUBJECT", 15);
        add.setBounds(380, 100, 150, 25);
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Arial", Font.BOLD, 14));
        add.addActionListener(this);
        add(add);

        JLabel listLabel = new JLabel("LIST OF SUBJECTS ENROLLED:");
        listLabel.setBounds(30, 140, 300, 25);
        listLabel.setFont(new Font("Arial", Font.BOLD, 16));
        listLabel.setForeground(new Color(15, 36, 62));
        add(listLabel);

        subjectList = new JTextArea();
        subjectList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(subjectList);
        scrollPane.setBounds(30, 170, 570, 140);
        add(scrollPane);

        JLabel tuitionLabel = new JLabel("TOTAL TUITION:");
        tuitionLabel.setBounds(30, 320, 150, 25);
        tuitionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        tuitionLabel.setForeground(new Color(15, 36, 62));
        add(tuitionLabel);

        totalTuition = new JLabel("₱0.00");
        totalTuition.setBounds(160, 320, 200, 25);
        totalTuition.setFont(new Font("Arial", Font.BOLD, 16));
        add(totalTuition);

        cancel = new RoundedButton("CANCEL", 15);
        cancel.setBounds(120, 370, 100, 40);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Arial", Font.BOLD, 16));
        cancel.addActionListener(this);
        add(cancel);

        back = new RoundedButton("BACK", 15);
        back.setBounds(250, 370, 100, 40);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        print = new RoundedButton("PRINT", 15);
        print.setBounds(380, 370, 100, 40);
        print.setForeground(Color.WHITE);
        print.setFont(new Font("Arial", Font.BOLD, 16));
        print.addActionListener(this);
        add(print);

        setVisible(true);
    }

    private String[] getStudents() {
        try {
            ResultSet rs = statement.executeQuery("SELECT StudentName FROM EnrolledStudents");
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            String[] students = new String[size];
            int index = 0;
            while (rs.next()) {
                students[index++] = rs.getString("StudentName");
            }
            rs.close();
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    private String[] getSubjects() {
        try {
            ResultSet rs = statement.executeQuery("SELECT SubjectCode FROM EnrolledSubjects");
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            String[] subjects = new String[size];
            int index = 0;
            while (rs.next()) {
                subjects[index++] = rs.getString("SubjectCode");
            }
            rs.close();
            return subjects;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String subjectCode = (String) subjectCombo.getSelectedItem();
            boolean exists = false;
            for (int i = 0; i < subjectCount; i++) {
                if (enrolledSubjects[i].equals(subjectCode)) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                JOptionPane.showMessageDialog(this, "Subject already added.");
                return;
            }
            try {
                String query = "SELECT * FROM EnrolledSubjects WHERE SubjectCode = '" + subjectCode + "'";
                ResultSet rs = statement.executeQuery(query);
                if (rs.next()) {
                    int units = rs.getInt("Units");
                    double tuition = units * 200.0;
                    Tuition += tuition;
                    enrolledSubjects[subjectCount++] = subjectCode;
                    subjectList.append("Subject " + subjectCount + ": " + subjectCode + " - " + rs.getString("Description") + " (" + units + " Units)\n");
                    totalTuition.setText("₱" + String.format("%.2f", Tuition));
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == cancel) {
            subjectList.setText("");
            totalTuition.setText("₱0.00");
            Tuition = 0;
            subjectCount = 0;
            enrolledSubjects = new String[100];
            studentCombo.setSelectedIndex(0);
            subjectCombo.setSelectedIndex(0);
        } else if (e.getSource() == back) {
            new enrollMenu();
            dispose();
        } else if (e.getSource() == print) {
            String selectedStudent = (String) studentCombo.getSelectedItem();
            try {
                String query = "SELECT * FROM EnrolledStudents WHERE StudentName = '" + selectedStudent + "'";
                ResultSet rs = statement.executeQuery(query);
                if (rs.next()) {
                    String studentId = rs.getString("StudentID");
                    String course = rs.getString("Course");
                    String year = rs.getString("Year");

                    for (int i = 0; i < subjectCount; i++) {
                        String updateSQL = "UPDATE EnrolledSubjects SET Total = Total + 1 WHERE SubjectCode = '" + enrolledSubjects[i] + "'";
                        statement.executeUpdate(updateSQL);
                    }

                    String[] subjectsToPass = new String[subjectCount];
                    System.arraycopy(enrolledSubjects, 0, subjectsToPass, 0, subjectCount);

                    new ReceiptPage(studentId, selectedStudent, course, year, subjectsToPass, Tuition);
                } else {
                    JOptionPane.showMessageDialog(this, "Student details not found.", "ERROR 404 NOT FOUND", JOptionPane.ERROR_MESSAGE);
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            dispose();
        }
    }

    class ReceiptPage extends JFrame {
        public ReceiptPage(String id, String name, String course, String year, String[] subjects, double total) {
            setTitle("OFFICIAL RECEIPT");
            setSize(415, 450);
            setLocationRelativeTo(null);
            setLayout(null);

            JTextArea receipt = new JTextArea();
            receipt.setEditable(false);
            receipt.setFont(new Font("Arial", Font.BOLD, 14));
            receipt.setBounds(15, 15, 370, 320);

            receipt.append("ENROLLMENT INFORMATION SYSTEM\n");
            receipt.append("OFFICIAL RECEIPT\n\n");
            receipt.append("Student ID: " + id + "\n");
            receipt.append("Student Name: " + name + "\n");
            receipt.append("Course: " + course + "      Year: " + year + "\n\n");
            receipt.append("Subjects Enrolled:\n");
            for (int i = 0; i < subjects.length; i++) {
                receipt.append("Subject " + (i + 1) + ": " + subjects[i] + "\n");
            }
            receipt.append("\nTOTAL ASSESSMENT: ₱" + String.format("%.2f", total));

            add(receipt);

            RoundedButton back = new RoundedButton("BACK", 15);
            back.setForeground(Color.WHITE);
            back.setFont(new Font("Arial", Font.BOLD, 16));
            back.setBounds(285, 350, 100, 40);
            back.addActionListener(e -> {
                new enrollMenu();
                dispose();
            });
            add(back);

            setVisible(true);
            setResizable(false);
        }
    }

    class RoundedButton extends JButton {
        private int radius;

        public RoundedButton(String label, int radius) {
            super(label);
            this.radius = radius;
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setOpaque(false);
            setForeground(Color.WHITE);
            setBackground(new Color(61, 105, 138));
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            g2.dispose();

            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {

        new assessment();
    }
}
