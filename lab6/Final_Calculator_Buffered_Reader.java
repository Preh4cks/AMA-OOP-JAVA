package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Final_Calculator_Buffered_Reader {
    public static void main(String[] args) {
        /* BufferedReader Allows Java program to Read data line
         *  InputStreamReader Reads bytes from users input and convert to specific charset */
        BufferedReader dataIn = new BufferedReader( new InputStreamReader( System.in ) );
        String name = "";
        String cur_usn;
        String cur_grade;
        String[] grade_string = { "prelim", "midterm", "final" };
        float average_grade = 0;
        long usn = 0;

        /* Creates float array of size 3 */
        float[] grades = new float[3];

        try {
            System.out.print( "Enter Your Name: " );
            name = dataIn.readLine();

            do {
                System.out.print( "Enter you USN[11]: " );
                cur_usn = dataIn.readLine();

                try {
                    if( cur_usn.length() == 11 ) {
                        usn = Long.parseLong( cur_usn );
                        break;
                    }
                } catch ( Exception e ) {
                    System.out.println( "Invalid Input Please Try Again!\n" );
                }

                System.out.println( "Invalid USN Please Try Again\n" );
            } while( true );

            System.out.println();
            for( int i = 0; i < 3; i++ ) {
                System.out.print( "Enter " + grade_string[i] + " grade : " );
                /* readLine Waits for users input*/
                cur_grade = dataIn.readLine();
                try {
                    grades[i] = Float.parseFloat( cur_grade );
                } catch ( Exception e ) {
                    System.out.println( "Invalid Input Please Try Again!\n" );
                }
            }

            for( int i = 0; i < 3; i++ ) {
                average_grade += grades[i] * ( (i == 2) ? 0.4 : 0.3 );
            }

        } catch ( IOException e ) {
            System.out.println( "Something went wrong\nPlease Try Again\nSystem Ends ..." ); // Print Error Message
            System.exit( 1 ); // Error
        }
        System.out.println( "\n------------------------\n" );
        System.out.println( "Name: " + name );
        System.out.println( "USN: " + usn + "\n" );

        for ( int i = 2; i >= 0; i-- ) {
            System.out.println( grade_string[(i + 3) % 3] + "s : " + grades[i] );
        }

        System.out.println( "\nYour Average Grade: " + average_grade );
    }
}