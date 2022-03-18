package lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Last_Three_Words_BufferedReader {
    public static void main(String[] args) {
        /* BufferedReader Allows Java program to Read data line
        *  InputStreamReader Reads bytes from users input and convert to specific charset */
        BufferedReader dataIn = new BufferedReader( new InputStreamReader( System.in ) );

        /* Creates String array of size 3 */
        String[] words = new String[3];

        try {
            for( int i = 0; i < 3; i++ ) {
                System.out.print( "Enter word " + ( i + 1 ) + ": " );
                /* readLine Waits for users input*/
                words[i] = dataIn.readLine();
            }
        } catch ( IOException e ) {
            System.out.println( "Something went wrong\nPlease Try Again\nSystem Ends ..." ); // Print Error Message
            System.exit( 1 ); // Error
        }

        System.out.println(); // Spacing
        /* Print out 3 inserted words */
        System.out.println( words[0] + " " + words[1] + " " + words[2] );
    }
}
