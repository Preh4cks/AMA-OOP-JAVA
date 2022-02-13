package lab2;

import java.util.Scanner;

public class lab2 {
    /*
        First Input should be integer like: 9
        Next input should be array like: 1,2,3,4,5,6,7,8,9
        Note: Do not use any spaces nor square brackets to properly get results
    */

    public static void main(String[] args) {
        int len, left, right, tmp;
        Scanner scan = new Scanner(System.in);
        System.out.print("How many Elements you want to submit in an Array?: ");


        try {
            len = scan.nextInt();
            int[] arr = new int[len];
            System.out.print("Insert array: ");
            scan.nextLine();
            String txt = scan.nextLine();
            // Ask for array values
            String[] asd = txt.split(",", -1);

            for(int i = 0; i < len; i++) {
                arr[i] = Integer.parseInt(asd[i]);
            }

            for (int j = 1; j < len - 1; j += 2) {
                // Sets our left and right variables
                left = arr[j - 1];
                right = arr[j + 1];

                // Checks if current index is greater than left
                if (arr[j] < left) {
                    tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }

                // Checks if current index is greater than right
                if (arr[j] < right) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }

            // Print out
            System.out.print("Array with every second element is greater than its left and right elements: ");
            for (int k = 0; k < len; k++) {
                System.out.print(arr[k] + " ");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] incorrect input.\nSystem ends...");
        }
    }
}
