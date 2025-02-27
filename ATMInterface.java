import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}


class ATMGUI {
    private BankAccount account;
    private JFrame frame;
    private JTextField amountField;
    private JLabel balanceLabel;

    public ATMGUI(BankAccount account) {
        this.account = account;
        frame = new JFrame("ATM Interface");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 248, 255)); 

        JLabel headingLabel = new JLabel("ATM MACHINE", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 40));
        headingLabel.setForeground(new Color(25, 25, 112));
        frame.add(headingLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBackground(new Color(230, 230, 250)); 

        balanceLabel = new JLabel("Balance: $" + account.getBalance(), SwingConstants.CENTER);
        balanceLabel.setForeground(new Color(25, 25, 112)); 
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(balanceLabel);

        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 18));
        amountField.setHorizontalAlignment(JTextField.CENTER);
        panel.add(amountField);

        JButton depositButton = createStyledButton("Deposit", new Color(144, 238, 144)); 
        JButton withdrawButton = createStyledButton("Withdraw", new Color(255, 182, 193)); 
        JButton checkBalanceButton = createStyledButton("Check Balance", new Color(173, 216, 230)); 
        JButton clearButton = createStyledButton("Clear", new Color(255, 239, 213)); 
        JButton exitButton = createStyledButton("Exit", new Color(211, 211, 211)); 

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processTransaction("deposit");
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processTransaction("withdraw");
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBalance();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                amountField.setText("");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(checkBalanceButton);
        panel.add(clearButton);
        panel.add(exitButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        return button;
    }

    private void processTransaction(String type) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (type.equals("deposit")) {
                account.deposit(amount);
                JOptionPane.showMessageDialog(frame, "Deposited: $" + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (type.equals("withdraw")) {
                if (amount > account.getBalance()) {
                    JOptionPane.showMessageDialog(frame, "Insufficient funds!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    account.withdraw(amount);
                    JOptionPane.showMessageDialog(frame, "Withdrawn: $" + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            updateBalance();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid amount.", "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: $" + account.getBalance());
    }
}


public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000); 
        new ATMGUI(userAccount);
    }
}