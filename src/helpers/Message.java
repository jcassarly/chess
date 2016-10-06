package helpers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jared Cassarly
 */
public class Message {
    /**
     * Display a JOptionPane with an error message to the user
     * @param message the message to give the user
     */
    public static void errorMessage(String message) {
        JFrame frame = new JFrame("Error");
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Display a JOptionPane with an error message to the user
     * @param message the message to give the user
     * @param type the type of message the user should receive (e.g. a "Help" message)
     */
    public static void infoMessage(String message, String type) {
        JFrame frame = new JFrame(type);
        JOptionPane.showMessageDialog(frame, message, type, JOptionPane.INFORMATION_MESSAGE);
    }
}
