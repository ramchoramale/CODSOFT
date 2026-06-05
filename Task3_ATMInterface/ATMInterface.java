import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}

public class ATMInterface extends JFrame implements ActionListener {

    private BankAccount account;

    private JLabel balanceLabel;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton checkBalanceButton;
    private JButton exitButton;

    public ATMInterface() {

        account = new BankAccount(5000);

        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5, 1, 10, 10));

        balanceLabel = new JLabel("Welcome to ATM", SwingConstants.CENTER);

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        checkBalanceButton = new JButton("Check Balance");
        exitButton = new JButton("Exit");

        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        checkBalanceButton.addActionListener(this);
        exitButton.addActionListener(this);

        add(balanceLabel);
        add(depositButton);
        add(withdrawButton);
        add(checkBalanceButton);
        add(exitButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == depositButton) {

            String input = JOptionPane.showInputDialog(
                    this,
                    "Enter amount to deposit:");

            try {
                double amount = Double.parseDouble(input);

                if (amount > 0) {
                    account.deposit(amount);
                    JOptionPane.showMessageDialog(
                            this,
                            "₹" + amount + " Deposited Successfully!");
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Enter a valid amount!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input!");
            }
        }

        else if (e.getSource() == withdrawButton) {

            String input = JOptionPane.showInputDialog(
                    this,
                    "Enter amount to withdraw:");

            try {
                double amount = Double.parseDouble(input);

                if (account.withdraw(amount)) {
                    JOptionPane.showMessageDialog(
                            this,
                            "₹" + amount + " Withdrawn Successfully!");
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Insufficient Balance!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input!");
            }
        }

        else if (e.getSource() == checkBalanceButton) {

            balanceLabel.setText(
                    "Current Balance: ₹" + account.getBalance());

            JOptionPane.showMessageDialog(
                    this,
                    "Available Balance: ₹" + account.getBalance());
        }

        else if (e.getSource() == exitButton) {

            JOptionPane.showMessageDialog(
                    this,
                    "Thank You For Using ATM!");

            System.exit(0);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ATMInterface();
        });
    }
}
