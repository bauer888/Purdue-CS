import javax.swing.*;

/**
 * EditTweet
 *
 * Serves the view portion of the MVC paradigm, controls the overall look (front end) of the EditTweet tab.
 *
 * @author Dylan Martin
 *
 * @version April 8, 2019
 *
 */

public class EditTweet {

    private JPanel mainPanel;

    private JTextField bodyTextField;
    private JButton clearButton;
    private JButton editButton;
    private JTextField idTextField;

    /** Empty constructor for EditTweet.java. You are not required to change this. */
    public EditTweet() {
    }

    /** Public accessor methods for the private variables in EditTweet.java */

    public JTextField getidTextField() { return this.idTextField; }

    public JTextField getBodyTextField() {
        return bodyTextField;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public JButton getClearButton() { return this.clearButton; }
}
