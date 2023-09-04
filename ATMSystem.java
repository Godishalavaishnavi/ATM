import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String userId;
    private String userPin;
    private double balance;
    private List<String> transactionHistory;

    public User(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public void setBalance(double d) {
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("123456", "7890", 1000.0));
        // Add more users as needed

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPin = scanner.nextLine();

        User currentUser = null;
        for (User user : users) {
            if (user.getUserId().equals(userId) && user.getUserPin().equals(userPin)) {
                currentUser = user;
                break;
            }
        }

        if (currentUser != null) {
            System.out.println("Welcome, " + userId);

            boolean quit = false;
            while (!quit) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Transaction History");
                System.out.println("6. Quit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.println("Balance: $" + currentUser.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character
                        if (withdrawalAmount > 0 && withdrawalAmount <= currentUser.getBalance()) {
                            currentUser.setBalance(currentUser.getBalance() - withdrawalAmount);
                            currentUser.addTransaction("Withdrawal: $" + withdrawalAmount);
                            System.out.println("Withdrawal successful.." );
                        } else {
                            System.out.println("Invalid withdrawal amount.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character
                        if (depositAmount > 0) {
                            currentUser.setBalance(currentUser.getBalance() + depositAmount);
                            currentUser.addTransaction("Deposit: $" + depositAmount);
                            System.out.println("Deposit successful..");
                        } else {
                            System.out.println("Invalid deposit amount.");
                        }
                        break;
                    case 4:
                        // Implement transfer functionality
                        System.out.print("Enter recipient's User ID: ");
                        String recipientUserId = scanner.nextLine();

                        User recipientUser = findUserById(users, recipientUserId);

                        if (recipientUser != null) {
                            System.out.print("Enter transfer amount: $");
                            double transferAmount = scanner.nextDouble();
                            scanner.nextLine(); // Consume the newline character

                            if (transferAmount > 0 && transferAmount <= currentUser.getBalance()) {
                                currentUser.setBalance(currentUser.getBalance() - transferAmount);
                                recipientUser.setBalance(recipientUser.getBalance() + transferAmount);

                                currentUser.addTransaction("Transfer to " + recipientUserId + ": $" + transferAmount);
                                recipientUser.addTransaction("Transfer from " + currentUser.getUserId() + ": $" + transferAmount);

                                System.out.println("Transfer successful.");
                            } else {
                                System.out.println("Invalid transfer amount or insufficient balance.");
                            }
                        } else {
                            System.out.println("Recipient User ID not found.");
                        }
                      
                        break;
                    case 5:
                        System.out.println("Transaction History:");
                        for (String transaction : currentUser.getTransactionHistory()) {
                            System.out.println(transaction);
                        }
                        break;
                    case 6:
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid User ID or PIN. Access denied.");
        }
    }

    private static User findUserById(List<User> users, String recipientUserId) {
        return null;
    }
}
