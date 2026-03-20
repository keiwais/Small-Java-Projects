import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class enrollMenu extends JFrame implements ActionListener {

    private JButton student, subject, assessment, report;

    public enrollMenu() {
        super("ENROLLMENT INFORMATION SYSTEM MAIN MENU");
        setSize(690, 165);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("ENROLLMENT INFORMATION SYSTEM", SwingConstants.CENTER);
        title.setBounds(130, 10, 400, 30);
        title.setForeground(new Color(15, 36, 62, 255));
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title);

        student = new RoundedButton("STUDENT");
        student.setBounds(30, 60, 150, 40);
        student.setFocusable(false);
        student.setForeground(Color.WHITE);
        student.setBackground(new Color(61, 105, 138));
        student.setFont(new Font("Arial", Font.BOLD, 14));
        add(student);

        subject = new RoundedButton("SUBJECT");
        subject.setBounds(185, 60, 150, 40);
        subject.setFocusable(false);
        subject.setForeground(Color.WHITE);
        subject.setBackground(new Color(61, 105, 138));
        subject.setFont(new Font("Arial", Font.BOLD, 14));
        add(subject);

        assessment = new RoundedButton("ASSESSMENT");
        assessment.setBounds(340, 60, 150, 40);
        assessment.setFocusable(false);
        assessment.setForeground(Color.WHITE);
        assessment.setBackground(new Color(61, 105, 138));
        assessment.setFont(new Font("Arial", Font.BOLD, 14));
        add(assessment);

        report = new RoundedButton("REPORT");
        report.setBounds(495, 60, 150, 40);
        report.setFocusable(false);
        report.setForeground(Color.WHITE);
        report.setBackground(new Color(61, 105, 138));
        report.setFont(new Font("Arial", Font.BOLD, 14));
        add(report);

        student.addActionListener(this);
        subject.addActionListener(this);
        assessment.addActionListener(this);
        report.addActionListener(this);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == student) {
            new student();
            dispose();
        } else if (e.getSource() == subject) {
            new subject();
            dispose();
        } else if (e.getSource() == assessment) {
            new assessment();
            dispose();
        } else if (e.getSource() == report) {
            new reportStudents();
            dispose();
        }
    }

    public static void main(String[] args) {
        new enrollMenu();
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
            g2.dispose();
            super.paintComponent(g);
        }

    }
}