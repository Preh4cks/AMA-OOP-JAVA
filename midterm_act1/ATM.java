package midterm_act1;
import java.util.Scanner;

class User {
    private int balance = 0;

    public User(int balance) {
        this.balance = balance;
    }

    /* Docu: withdraw_amount(int amount)
    *  "Subtract amount to current balance and returns the current users balance"
    *  Takes 1 argument of amount
    *  Author: Arjhay Frias 02/25/2022 */
    public int withdraw_amount(int amount) {
        if (amount > this.balance) {
            System.out.println("[error] Unable to process withdrawal");
            System.out.println("[error] Insufficient balance!");
        } else if (amount <= 0) {
            System.out.println("[Error] Unable to withdraw negative number!");
        } else {
            this.balance -= amount;
        }
        return this.balance;
    }

    /* Docu: deposit_amount(int amount)
     *  "Add amount to current balance and returns the current users balance"
     *  Takes 1 argument of amount
     *  Author: Arjhay Frias 02/25/2022 */
    public int deposit_amount(int amount) {
        if (amount <= 0) {
            System.out.println("[Error] Unable to deposit negative number!");
        } else {
            this.balance += amount;
        }
        return this.balance;
    }
}

public class ATM {
    public static void main(String[] args) {
        // Set user's balance
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome!");
        int users_balance = 0;

        // Validate's User Input [User's Balance]
        while (users_balance <= 0 || users_balance != (int)users_balance ) {
            try {
                System.out.print("Enter your balance: ");
                String str_users_balance = scan.nextLine();
                users_balance = Integer.parseInt(str_users_balance);
                if (users_balance <= 0) {
                    System.out.println("[Error] Unable to set your balance with negative value.");
                }
            } catch (Exception e) {
                System.out.println("[Error] Invalid Input please try again.");
                System.out.println("------------------------------------------------------");
            }
        }

        // Create User
        User arjhay = new User(users_balance);
        System.out.println("------------------------------------------------------");

        // Select option
        System.out.println("1. Withdraw Amount");
        System.out.println("2. Deposit Amount");
        System.out.println("3. Exit");
        int option = 0;

        // Validate's User Input [Option]
        while (true) {
            try {
                // Get User's Input
                System.out.print("Select an option: ");
                String str_option = scan.nextLine();
                option = Integer.parseInt(str_option);
                System.out.println("------------------------------------------------------");

                // Initialize functions
                switch (option) {
                    case 1:
                        withdraw(arjhay, scan);
                        break;
                    case 2:
                        deposit(arjhay, scan);
                        break;
                    case 3:
                        exit();
                        break;
                    default:
                        System.out.println("[Error] Invalid option please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("[Error] Invalid Input please try again.");
                System.out.println("------------------------------------------------------");
            }
        }
    }

    /* Docu: withdraw(User user_name, Scanner scan)
     *  "Get users input of amount and call withdraw_amount() method from User class
     *  Also Display's the users current balance"
     *  Takes 2 arguments of User class and Scanner class
     *  Author: Arjhay Frias 02/25/2022 */
    public static void withdraw(User user_name, Scanner scan) {
        System.out.println("How much amount you want to withdraw from your account?");
        int amount = 0;

        // Validate's User Input [Amount]
        while (amount == 0 || amount != (int)amount ) {
            try {
                System.out.print("Amount: ");
                String str_amount = scan.nextLine();
                amount = Integer.parseInt(str_amount);
                System.out.println("Current balance: " + user_name.withdraw_amount(amount));
            } catch (Exception e) {
                System.out.println("[Error] Invalid Input please try again.");
                System.out.println("------------------------------------------------------");
            }
        }
    }

    /* Docu: deposit(User user_name, Scanner scan)
     *  "Get users input of amount and call deposit_amount() method from User class
     *  Also Display's the users current balance"
     *  Takes 2 arguments of User class and Scanner class
     *  Author: Arjhay Frias 02/25/2022 */
    public static void deposit(User user_name, Scanner scan) {
        System.out.println("How much amount you want to deposit in your account?");
        int amount = 0;

        // Validate's User Input [Amount]
        while (amount == 0 || amount != (int)amount ) {
            try {
                System.out.print("Amount: ");
                String str_amount = scan.nextLine();
                amount = Integer.parseInt(str_amount);
                System.out.println("Current balance: " + user_name.deposit_amount(amount));
            } catch (Exception e) {
                System.out.println("[Error] Invalid Input please try again.");
                System.out.println("------------------------------------------------------");
            }
        }
    }

    /* Docu: exit()
     *  "Terminates the program"
     *  Takes 0 arguments
     *  Author: Arjhay Frias 02/25/2022 */
    public static void exit() {
        System.out.println("Thank you for using the system!");
        System.exit(0);
    }
}