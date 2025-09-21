import java.util.Scanner;

class Account {
    int accountNumber;
    String accountHolderName;
    double balance;
    String email;
    String phoneNumber;

    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            return "Money deposited successfully. New Balance: " + balance;
        } else {
            return "Amount must be positive";
        }
    }

    public String withdraw(double amount) {
        if (amount <= 0) {
            return "Amount must be positive";
        }
        if (balance >= amount) {
            balance = balance - amount;
            return "Money withdrawn successfully. New Balance: " + balance;
        } else {
            return "Insufficient balance";
        }
    }

    public String displayAccountDetails() {
        return "Account Number: " + accountNumber + "\n"
                + "Account Holder Name: " + accountHolderName + "\n"
                + "Balance: " + balance + "\n"
                + "Email: " + email + "\n"
                + "Phone Number: " + phoneNumber;
    }

    public String updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        return "Contact details updated successfully";
    }
}

public class UserInterface {
    Account[] accounts = new Account[100];
    int count = 0;
    Scanner scanner = new Scanner(System.in);

    public String createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            amount = 0.0;
        }
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        int accountNumber = 1001 + count;
        accounts[count] = new Account(accountNumber, name, amount, email, phone);
        count++;
        return "Account created successfully with Account Number: " + accountNumber;
    }

    public String performDeposit() {
        System.out.print("Enter account number: ");
        int accNo;
        try {
            accNo = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return "Invalid account number";
        }
        System.out.print("Enter amount to deposit: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            return "Invalid amount";
        }
        for (int i = 0; i < count; i++) {
            if (accounts[i].accountNumber == accNo) {
                return accounts[i].deposit(amount);
            }
        }
        return "Account not found";
    }

    public String performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNo;
        try {
            accNo = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return "Invalid account number";
        }
        System.out.print("Enter amount to withdraw: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            return "Invalid amount";
        }
        for (int i = 0; i < count; i++) {
            if (accounts[i].accountNumber == accNo) {
                return accounts[i].withdraw(amount);
            }
        }
        return "Account not found";
    }

    public String showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNo;
        try {
            accNo = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return "Invalid account number";
        }
        for (int i = 0; i < count; i++) {
            if (accounts[i].accountNumber == accNo) {
                return accounts[i].displayAccountDetails();
            }
        }
        return "Account not found";
    }

    public String updateContact() {
        System.out.print("Enter account number: ");
        int accNo;
        try {
            accNo = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return "Invalid account number";
        }
        for (int i = 0; i < count; i++) {
            if (accounts[i].accountNumber == accNo) {
                System.out.print("Enter new email address: ");
                String email = scanner.nextLine();
                System.out.print("Enter new phone number: ");
                String phone = scanner.nextLine();
                return accounts[i].updateContactDetails(email, phone);
            }
        }
        return "Account not found";
    }

    public int mainMenu() {
        while (true) {
            System.out.println("\nWelcome to Vidit Bank - KR Mangalam University Branch");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice");
                continue;
            }
            if (choice == 1) System.out.println(createAccount());
            else if (choice == 2) System.out.println(performDeposit());
            else if (choice == 3) System.out.println(performWithdrawal());
            else if (choice == 4) System.out.println(showAccountDetails());
            else if (choice == 5) System.out.println(updateContact());
            else if (choice == 6) {
                System.out.println("Thank you for banking with Vidit Bank");
                return 0;
            } else System.out.println("Invalid choice");
        }
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.mainMenu();
    }
}
