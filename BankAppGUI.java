import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    public BankAccount(String accountHolder, String accountNumber, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountInfo() {
        return "<html><b>Account Holder:</b> " + accountHolder +
               "<br><b>Account Number:</b> " + accountNumber +
               "<br><b>Balance:</b> ₹" + balance + "</html>";
    }
}

public class BankAppGUI extends JFrame implements ActionListener {
    private BankAccount account;
    private JTextField amountField;
    private JLabel infoLabel;
    private JButton depositBtn, withdrawBtn, balanceBtn, infoBtn;

    public BankAppGUI() {
        account = new BankAccount("Abhinav", "TN123456789", 5000.0);

        setTitle("🏦 Bank Account Manager");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel for input
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Amount (₹):"));
        amountField = new JTextField(10);
        topPanel.add(amountField);
        add(topPanel, BorderLayout.NORTH);

        // Center panel for buttons
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        depositBtn = new JButton("Deposit");
        withdrawBtn = new JButton("Withdraw");
        balanceBtn = new JButton("Check Balance");
        infoBtn = new JButton("Account Info");

        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        balanceBtn.addActionListener(this);
        infoBtn.addActionListener(this);

        centerPanel.add(depositBtn);
        centerPanel.add(withdrawBtn);
        centerPanel.add(balanceBtn);
        centerPanel.add(infoBtn);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel for output
        infoLabel = new JLabel("Welcome, Abhinav!", SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(infoLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String input = amountField.getText();
        double amount = 0;

        try {
            if (!input.isEmpty()) amount = Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            infoLabel.setText("❌ Invalid amount entered.");
            return;
        }

        switch (command) {
            case "Deposit":
                account.deposit(amount);
                infoLabel.setText("✅ Deposited ₹" + amount);
                break;
            case "Withdraw":
                if (account.withdraw(amount)) {
                    infoLabel.setText("✅ Withdrawn ₹" + amount);
                } else {
                    infoLabel.setText("❌ Insufficient balance.");
                }
                break;
            case "Check Balance":
                infoLabel.setText("💰 Balance: ₹" + account.getBalance());
                break;
            case "Account Info":
                infoLabel.setText(account.getAccountInfo());
                break;
        }

        amountField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankAppGUI());
    }
}