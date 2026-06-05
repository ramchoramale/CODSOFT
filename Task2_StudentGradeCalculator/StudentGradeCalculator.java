import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentGradeCalculator extends JFrame implements ActionListener {

    JTextField sub1, sub2, sub3, sub4, sub5;
    JButton calculateButton;
    JLabel totalLabel, percentageLabel, gradeLabel;

    public StudentGradeCalculator() {

        setTitle("Student Grade Calculator");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(10, 2, 10, 10));

        add(new JLabel("Subject 1 Marks:"));
        sub1 = new JTextField();
        add(sub1);

        add(new JLabel("Subject 2 Marks:"));
        sub2 = new JTextField();
        add(sub2);

        add(new JLabel("Subject 3 Marks:"));
        sub3 = new JTextField();
        add(sub3);

        add(new JLabel("Subject 4 Marks:"));
        sub4 = new JTextField();
        add(sub4);

        add(new JLabel("Subject 5 Marks:"));
        sub5 = new JTextField();
        add(sub5);

        calculateButton = new JButton("Calculate Result");
        calculateButton.addActionListener(this);
        add(calculateButton);

        add(new JLabel(""));

        totalLabel = new JLabel("Total Marks: ");
        percentageLabel = new JLabel("Percentage: ");
        gradeLabel = new JLabel("Grade: ");

        add(totalLabel);
        add(percentageLabel);
        add(gradeLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            int m1 = Integer.parseInt(sub1.getText());
            int m2 = Integer.parseInt(sub2.getText());
            int m3 = Integer.parseInt(sub3.getText());
            int m4 = Integer.parseInt(sub4.getText());
            int m5 = Integer.parseInt(sub5.getText());

            if (m1 > 100 || m2 > 100 || m3 > 100 ||
                m4 > 100 || m5 > 100 ||
                m1 < 0 || m2 < 0 || m3 < 0 ||
                m4 < 0 || m5 < 0) {

                JOptionPane.showMessageDialog(this,
                        "Marks must be between 0 and 100");
                return;
            }

            int total = m1 + m2 + m3 + m4 + m5;
            double percentage = total / 5.0;

            String grade;

            if (percentage >= 90) {
                grade = "A+";
            } else if (percentage >= 80) {
                grade = "A";
            } else if (percentage >= 70) {
                grade = "B";
            } else if (percentage >= 60) {
                grade = "C";
            } else if (percentage >= 50) {
                grade = "D";
            } else {
                grade = "F";
            }

            totalLabel.setText("Total Marks: " + total + " / 500");
            percentageLabel.setText(
                    String.format("Percentage: %.2f%%", percentage));
            gradeLabel.setText("Grade: " + grade);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric marks.");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new StudentGradeCalculator();
        });
    }
}
