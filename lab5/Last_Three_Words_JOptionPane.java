package lab5;

import javax.swing.JOptionPane;

public class Last_Three_Words_JOptionPane {
    public static void main(String[] args) {
        /* Creates String array of size 3 */
        String[] words = new String[3];

        for(int i = 0; i < 3; i++) {
            /* JOptionPane.showInputDialog displays input dialog and waits for users input */
            words[i] = JOptionPane.showInputDialog( "Enter word " + ( i + 1 ) + ": " );
        }

        /* JOptionPane.showMessageDialog asks for parentComponent and a message that displays dialog message */
        JOptionPane.showMessageDialog( null, words[0] + " " + words[1] + " " + words[2] );
    }
}