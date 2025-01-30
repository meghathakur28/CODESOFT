import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GradeCalculatorGUI {

    private JFrame frame;
    private JTextField mathField, scienceField, hindiField, englishField, csField, peField, ssField;
    private JLabel resultLabel;
    private JLabel sub;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GradeCalculatorGUI::new);
    }

    public GradeCalculatorGUI() {
        frame = new JFrame("Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1150, 1000);
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JLabel heading = new JLabel("Calculate Your Grades", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 32));
        heading.setForeground(Color.RED); 
        mainPanel.add(heading, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2, 5, 5));
        panel.setBackground(new Color(230, 230, 250));
    
  
        panel.add(new JLabel("Math: "));
        mathField = new JTextField();
        panel.add(mathField);
    
        panel.add(new JLabel("Science: "));
        scienceField = new JTextField();
        panel.add(scienceField);
    
        panel.add(new JLabel("Hindi: "));
        hindiField = new JTextField();
        panel.add(hindiField);
    
        panel.add(new JLabel("English: "));
        englishField = new JTextField();
        panel.add(englishField);
    
        panel.add(new JLabel("Computer Science: "));
        csField = new JTextField();
        panel.add(csField);
    
        panel.add(new JLabel("Physical Education: "));
        peField = new JTextField();
        panel.add(peField);
    
        panel.add(new JLabel("Social Science: "));
        ssField = new JTextField();
        panel.add(ssField);
    
        JButton calculateButton = new JButton("Calculate Result");
        panel.add(calculateButton);
    
        resultLabel = new JLabel();
        panel.add(resultLabel);
    
        sub = new JLabel();
        panel.add(sub);
    
        calculateButton.addActionListener(new CalculateResultListener());
    
        mainPanel.add(panel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    private class CalculateResultListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int math = getValidatedMarks(mathField.getText());
                int science = getValidatedMarks(scienceField.getText());
                int hindi = getValidatedMarks(hindiField.getText());
                int english = getValidatedMarks(englishField.getText());
                int cs = getValidatedMarks(csField.getText());
                int pe = getValidatedMarks(peField.getText());
                int ss = getValidatedMarks(ssField.getText());
                StringBuilder failMessage = new StringBuilder();
                int failedSubjects = 0;

                if (math < 33) {
                    failMessage.append("Math ");
                    failedSubjects++;
                    math = 0;
                }
                if (science < 33) {
                    failMessage.append("Science ");
                    failedSubjects++;
                    science = 0;
                }
                if (hindi < 33) {
                    failMessage.append("Hindi ");
                    failedSubjects++;
                    hindi = 0;
                }
                if (english < 33) {
                    failMessage.append("English ");
                    failedSubjects++;
                    english = 0;
                }
                if (cs < 33) {
                    failMessage.append("Computer Science ");
                    failedSubjects++;
                    cs = 0;
                }
                if (pe < 33) {
                    failMessage.append("Physical Education ");
                    failedSubjects++;
                    pe = 0;
                }
                if (ss < 33) {
                    failMessage.append("Social Science ");
                    failedSubjects++;
                    ss = 0;
                }
                int totalMarks = math + science + hindi + english + cs + pe + ss;
                double percentage = (totalMarks / 700.0) * 100;
                String grade = "";
                           if (percentage >= 89.51 && percentage <= 100) {
                                grade+="A+";
                            } else if (percentage >= 84.50 && percentage <= 89.50) {
                                grade+="A";
                            } else if (percentage >= 79.51 && percentage <= 84.50) {
                                grade+="B+";
                            } else if (percentage >= 74.51 && percentage <= 79.50) {
                                grade+="B";
                            } else if (percentage >= 64.51 && percentage <= 74.50) {
                                grade+= "C+";
                            } else if (percentage >= 44.51 && percentage <= 64.50) {
                                grade+="C";
                            } else if (percentage >= 33 && percentage <= 44.50) {
                                grade+= "D";
                            } else {
                                grade+="Fail...Study hard next time!";
                            }
                            if (failedSubjects > 0) {
                                resultLabel.setText("Total Marks: " + totalMarks + 
                        "  Percentage: " + String.format("%.2f", percentage) + "%" +
                        "  Grade: " + grade+" "+" Failed in: " + failedSubjects+" subjects");
                        return;
                            }
                resultLabel.setText("Total Marks: " + totalMarks + 
                        "  Percentage: " + String.format("%.2f", percentage) + "%" +
                        "  Grade: " + grade);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers for all subjects", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        private int getValidatedMarks(String input) {
            int marks = Integer.parseInt(input);
            if (marks < 0 || marks > 100) {
                throw new NumberFormatException("Invalid Marks");
            }
            return marks;
        }
    }
}
