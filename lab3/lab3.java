package lab3;

import java.util.Scanner;

public class lab3 {
    public static void main(String[] args) {
        int chances = 5, pin = 123456, tried_pin;
        Scanner scan = new Scanner(System.in);

        try {
            do {
                System.out.println("You have [" + chances + "] chances left");
                System.out.print("Enter your PIN Number: ");
                tried_pin = scan.nextInt();
                chances--;

                if (tried_pin == pin) {
                    System.out.println("Granted Access");
                    System.out.println("System ends...");
                    break;
                } else if (chances == 0) {
                    System.out.println("System Authentication Failed!");
                    System.out.println("Calling 911...");
                    System.out.println("Dialing...");
                    System.out.println("System ends...");
                    break;
                }
                System.out.println("Access Denied");
                System.out.println("Incorrect PIN number");
            } while (true);
        } catch (Exception e) {
            System.out.println("[ERROR] You inserted [STRING] instead of [INT]\nSystem ends...");
        }
    }
}
