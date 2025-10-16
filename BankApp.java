import java.util.Scanner;

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
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposited: ₹" + amount);
        } else {
            System.out.println("❌ Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Withdrawn: ₹" + amount);
        } else {
            System.out.println("❌ Insufficient balance or invalid amount.");
        }
    }

    public void checkBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }

    public void displayAccountInfo() {
        System.out.println("👤 Account Holder: " + accountHolder);
        System.out.println("🔢 Account Number: " + accountNumber);
        System.out.println("💰 Balance: ₹" + balance);
    }
}

public class BankApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a sample account
        BankAccount account = new BankAccount("Abhinav", "TN123456789", 5000.0);

        System.out.println("🏦 Welcome to Bank Account Management System");

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Account Info");
            System.out.println("5. Exit");
            System.out.print("👉 Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    account.displayAccountInfo();
                    break;
                case 5:
                    System.out.println("👋 Thank you for using the system!");
                    scanner.close();
                    return;
                default:
                    System.out.println("⚠️ Invalid choice. Please try again.");
            }
        }
    }
}