package lab6;

import javax.swing.JOptionPane;

public class Final_Calculator_JOptionPane {
    public static void main(String[] args) {
        /* Get Name */
        String name = JOptionPane.showInputDialog( "Enter your Name: ");

        /* Get USN */
        String current_usn;
        long usn;
        do {
            current_usn = JOptionPane.showInputDialog( "Enter your USN: ");
            if( current_usn.length() == 11 ) {
                try {
                    usn = Long.parseLong(current_usn);
                    break;
                } catch (Exception e) {
                    // Continue
                }
            }
            JOptionPane.showMessageDialog( null, "Invalid USN please try again " );
        } while ( true );

        /* Get input Grade from user */
        Float[] grades = new Float[3];
        String[] grading_period = { "prelim", "midterm", "final" };
        String cur_grade;
        for( int i = 0; i < 3; i++ ) {
            cur_grade = JOptionPane.showInputDialog( "Enter " + grading_period[i] + " : " );
            grades[i] = Float.parseFloat( cur_grade );
        }

        /* Calculate Average Grade */
        float average_grade = 0;
        for( int i = 0; i < 3; i++ ) {
            float prelims_midterms_percentage = 0.3F; // 30%
            float finals_percentage = 0.4F; // 40%
            boolean is_finals = i == 2;
            average_grade += grades[i] * ( ( is_finals ) ? finals_percentage : prelims_midterms_percentage );
        }

        /* Create String with Grade in Reverse Order */
        String stringify_grade = "";
        for( int i = 2; i >= 0; i-- ) {
            stringify_grade += ( grading_period[( i + 3 ) % 3] + "s : " + grades[i] + "\n" );
        }

        /* Display Name, USN, Grade and Average Grade */
        JOptionPane.showMessageDialog(
            null, // Default Dialog Box
            "Name: " + name + "\n" + // Display Name
            "USN: " + usn + "\n\n" + // Display USN
            stringify_grade + "\n" + // Display Grade
            "Your Average Grade: " + average_grade // Display Average Grade
        );
    }
}