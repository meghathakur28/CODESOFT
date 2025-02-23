import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGame2 {
    private int targetNumber;
    private int attemptsLeft = 5;
    private int score = 0;
    private Random random = new Random();
    
    private JFrame frame;
    private JTextField guessField;
    private JLabel titleLabel, messageLabel, attemptsLabel, scoreLabel;
    private JButton guessButton, restartButton;
    private JLabel backgroundLabel;

    public NumberGame2() {
        targetNumber = random.nextInt(100) + 1;
        
        frame = new JFrame("Number Guessing Game");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\DELL\\Downloads\\wall-wallpaper-concrete-colored-painted-textured-concept.jpg");
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());
        frame.setContentPane(backgroundLabel);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        titleLabel = new JLabel("NUMBER GUESSING GAME", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 22));
        titleLabel.setForeground(new Color(255, 253, 208));
        
        messageLabel = new JLabel("Guess a number between 1 and 100", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        messageLabel.setForeground(Color.WHITE);
        
        attemptsLabel = new JLabel("Attempts left: " + attemptsLeft);
        attemptsLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        attemptsLabel.setForeground(Color.WHITE);
        
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        scoreLabel.setForeground(Color.WHITE);
        
        guessField = new JTextField(12);
        guessField.setFont(new Font("Serif", Font.PLAIN, 16));
        
        guessButton = new JButton("Submit");
        restartButton = new JButton("Restart");
        
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
        
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        
        backgroundLabel.add(titleLabel, gbc);
        backgroundLabel.add(messageLabel, gbc);
        backgroundLabel.add(guessField, gbc);
        backgroundLabel.add(guessButton, gbc);
        backgroundLabel.add(attemptsLabel, gbc);
        backgroundLabel.add(scoreLabel, gbc);
        backgroundLabel.add(restartButton, gbc);
        
        frame.setVisible(true);
    }
    
    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            if (guess == targetNumber) {
                messageLabel.setText("Correct! You win!");
                score += 10;
                guessButton.setEnabled(false);
            } else if (guess > targetNumber) {
                messageLabel.setText("Too high! Try again.");
            } else {
                messageLabel.setText("Too low! Try again.");
            }
            
            attemptsLeft--;
            attemptsLabel.setText("Attempts left: " + attemptsLeft);
            scoreLabel.setText("Score: " + score);
            
            if (attemptsLeft == 0) {
                messageLabel.setText("Out of attempts! Number was " + targetNumber);
                guessButton.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number!");
        }
    }
    
    private void resetGame() {
        targetNumber = random.nextInt(100) + 1;
        attemptsLeft = 5;
        messageLabel.setText("Guess a number between 1 and 100");
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        guessButton.setEnabled(true);
    }
    
    public static void main(String[] args) {
        new NumberGame2();
    }
}