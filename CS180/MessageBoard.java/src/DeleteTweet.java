import javax.swing.*;

/**
 * DeleteTweet
 *
 * Serves the view portion of the MVC paradigm, controls the overall look (front end) of the DeleteTweet tab.
 *
 * @author Dylan Martin
 *
 * @version April 8, 2019
 *
 */

public class DeleteTweet {
    private JTextField idTextField;
    private JButton deleteButton;
    private JPanel mainPanel;
    private JButton clearButton;

    /** Empty constructor for DeleteTweet.java. You are not required to change this. */
    public DeleteTweet() {
    }

    /** Public accessor methods for the private variables in DeleteTweet.java */

    public JTextField getidTextField() {
        return this.idTextField;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public JButton getClearButton() {
        return this.clearButton;
    }




}
