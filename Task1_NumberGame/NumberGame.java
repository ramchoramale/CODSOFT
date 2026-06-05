import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGameGUI extends JFrame implements ActionListener {

    private JTextField guessField;
    private JButton guessButton;
    private JButton newRoundButton;
    private JLabel messageLabel;
    private JLabel scoreLabel;
    private JLabel attemptsLabel;

    private int randomNumber;
    private int attemptsLeft;
    private int totalScore;
    private int roundsWon;

    public NumberGameGUI() {

        setTitle("Number Guessing Game");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(6, 1, 10, 10));

        JLabel title = new JLabel("Guess a Number (1 - 100)", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        guessField = new JTextField();
        guessButton = new JButton("Submit Guess");
        newRoundButton = new JButton("New Round");

        messageLabel = new JLabel("Enter your guess", SwingConstants.CENTER);
        attemptsLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel = new JLabel("", SwingConstants.CENTER);

        add(title);
        add(guessField);
        add(guessButton);
        add(messageLabel);
        add(attemptsLabel);
        add(scoreLabel);

        guessButton.addActionListener(this);
        newRoundButton.addActionListener(this);

        startNewRound();

        setVisible(true);
    }

    private void startNewRound() {
        randomNumber = new Random().nextInt(100) + 1;
        attemptsLeft = 3;

        messageLabel.setText("New Round Started!");
        attemptsLabel.setText("Attempts Left: " + attemptsLeft);
        scoreLabel.setText(
                "Rounds Won: " + roundsWon +
                " | Total Score: " + totalScore);

        guessField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            int guess = Integer.parseInt(guessField.getText());

            if (guess < 1 || guess > 100) {
                messageLabel.setText("Enter a number between 1 and 100");
                return;
            }

            attemptsLeft--;

            if (guess == randomNumber) {

                roundsWon++;

                int roundScore = (attemptsLeft + 1) * 10;
                totalScore += roundScore;

                scoreLabel.setText(
                        "Rounds Won: " + roundsWon +
                        " | Total Score: " + totalScore);

                int option = JOptionPane.showConfirmDialog(
                        this,
                        "Correct!\nScore Earned: " + roundScore +
                        "\nPlay Again?",
                        "You Won!",
                        JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    startNewRound();
                } else {
                    System.exit(0);
                }

            } else if (guess < randomNumber) {
                messageLabel.setText("Too Low!");
            } else {
                messageLabel.setText("Too High!");
            }

            attemptsLabel.setText("Attempts Left: " + attemptsLeft);

            if (attemptsLeft == 0 && guess != randomNumber) {

                int option = JOptionPane.showConfirmDialog(
                        this,
                        "Game Over!\nCorrect Number: " + randomNumber +
                        "\nPlay Again?",
                        "Lost",
                        JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    startNewRound();
                } else {
                    System.exit(0);
                }
            }

            guessField.setText("");

        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new NumberGameGUI();
        });
    }
}
