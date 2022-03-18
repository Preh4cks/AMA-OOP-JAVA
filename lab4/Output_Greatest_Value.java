package lab4;

public class Output_Greatest_Value {
    public static void main(String[] args) {
        /* Output greatest value */
        int number1 = 100;
        int number2 = 23;
        int number3 = 50;
        int highest_number = (number1 > number2 && number1 > number3 ? number1 : (number2 > number3 ? number2 : number3));

        System.out.println("The highest number is = " + highest_number);
    }
}
