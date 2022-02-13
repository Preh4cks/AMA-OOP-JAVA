package lab1;

import java.util.Scanner;

public class lab1 {
    public static void main(String[] args) {
	    String name;
        int answer;

        do {
            Scanner scan = new Scanner(System.in);
            System.out.print("Please insert you Name: ");
            name = scan.nextLine();

            System.out.println("Hello Mr. " + name);
            System.out.print("Would you like to change your name? insert 1 for yes and 2 for no: ");
            try {
                answer = scan.nextInt();

                if (answer == 2) {
                    System.out.println("Bye, " + name);
                    break;
                } else if (answer != 1) {
                    System.out.println("[ERROR] wrong input.\nPlease select between 1 and 2 only.\nSystem ends...");
                    break;
                }
            } catch (Exception e) {
                System.out.println("[ERROR] you've inserted [STRING] literal please try again.\nSystem ends...");
            }
        } while (true);
    }

}
