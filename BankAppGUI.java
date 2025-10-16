import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BankAppGUI extends JFrame implements ActionListener {
    private final BankAccount account = new BankAccount("Abhinav", "TN123456789", 5000.0);
    private JTextField amountField;
    private JTextArea infoArea;
    private JButton depositBtn, withdrawBtn, balanceBtn, infoBtn;

    public BankAppGUI() {
        setTitle("🏦 Bank Account Manager");
        setSize(460, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.decode("#F5F5F5")); // Light gray background

        // Top Panel: Input
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.decode("#F5F5F5"));
        inputPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        inputPanel.add(new JLabel("Amount (₹):"));
        amountField = new JTextField(12);
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputPanel.add(amountField);
        add(inputPanel, BorderLayout.NORTH);

        // Center Panel: Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        buttonPanel.setBackground(Color.decode("#F5F5F5"));

        depositBtn = createStyledButton("💰 Deposit", "Add money to your account");
        withdrawBtn = createStyledButton("💸 Withdraw", "Take money from your account");
        balanceBtn = createStyledButton("📊 Check Balance", "View current balance");
        infoBtn = createStyledButton("ℹ️ Account Info", "View account holder details");

        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(balanceBtn);
        buttonPanel.add(infoBtn);
        add(buttonPanel, BorderLayout.CENTER);

        // Bottom Panel: Info Display
        infoArea = new JTextArea("Welcome, Abhinav!");
        infoArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setBackground(Color.decode("#EAEAEA")); // Slightly darker gray
        infoArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(infoArea, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JButton createStyledButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setToolTipText(tooltip);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect: darker gray
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#D3D3D3"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY);
            }
        });

        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String input = amountField.getText().trim();
        double amount = 0.0;

        if (!action.contains("Info") && !action.contains("Balance")) {
            try {
                if (input.isEmpty()) {
                    showError("❌ Please enter an amount.");
                    return;
                }
                amount = Double.parseDouble(input);
                amountField.setBackground(Color.WHITE);
            } catch (NumberFormatException ex) {
                amountField.setBackground(Color.decode("#F8D7DA")); // Light red
                showError("❌ Invalid amount entered.");
                return;
            }
        }

        switch (action) {
            case "💰 Deposit":
                account.deposit(amount);
                showMessage("✅ Deposited ₹" + amount);
                break;
            case "💸 Withdraw":
                if (account.withdraw(amount)) {
                    showMessage("✅ Withdrawn ₹" + amount);
                } else {
                    showError("❌ Insufficient balance.");
                }
                break;
            case "📊 Check Balance":
                showMessage("💰 Balance: ₹" + account.getBalance());
                break;
            case "ℹ️ Account Info":
                showMessage(account.getAccountInfo());
                break;
        }

        amountField.setText("");
    }

    private void showMessage(String message) {
        infoArea.setForeground(Color.decode("#155724")); // Deep green
        infoArea.setText(message);
    }

    private void showError(String message) {
        infoArea.setForeground(Color.decode("#721C24")); // Deep red
        infoArea.setText(message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankAppGUI::new);
    }
}