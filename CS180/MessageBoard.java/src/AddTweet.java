import javax.swing.*;

/**
 * AddTweet
 *
 * Serves the view portion of the MVC paradigm, controls the overall look (front end) of the AddTweet tab.
 *
 * @author Dylan Martin
 *
 * @version April 8, 2019
 *
 */

public final class AddTweet {

    private JPanel mainPanel;
    private JTextField idTextField;
    private JButton clearButton;
    private JButton tweetButton;
    private JTextField bodyTextField;

    /** Empty constructor for AddTweet.java. You are not required to change this. */
    public AddTweet() {
    }

    /** Public accessor methods for the private variables in AddTweet.java */

    public JTextField getidTextField() {
        return this.idTextField;
    }

    public JButton getClearButton() {
        return this.clearButton;
    }

    public JButton getTweetButton() {
        return this.tweetButton;
    }

    public JTextField getBodyTextField() {
        return this.bodyTextField;
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }


}
