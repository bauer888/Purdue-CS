import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

/**
 * AddController
 *
 * Serves the controller portion of the MVC paradigm, controls the overall function of the AddTweet view.
 *
 * @author Jack Bauer, bauer88
 *
 * @version April 8, 2019
 *
 */

public final class AddController {

    private TwitterModel twitterModel;
    private AddTweet addTweet;

    /** Implement the AddController constructor per the instructions in the handout
     * @param twitterModel TwitterModel object connecting the model and controller portions of the MVC paradigm.
     * @param addTweet AddTweet object connecting the view and controller portions of the MVC paradigm.
     */
    public AddController(TwitterModel twitterModel, AddTweet addTweet) throws IllegalArgumentException {
        if (twitterModel == null || addTweet == null) {
            throw new IllegalArgumentException("Arguments can not be null");
        } else {
            this.twitterModel = twitterModel;
            this.addTweet = addTweet;

            addTweet.getTweetButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getAddButtonSemantics();
                }
            });

            addTweet.getClearButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getClearButtonSemantics();
                }
            });
        }
    }

    /** A method used to verify if a given string is strictly containing numeric values.
     * @param aString A string to verify
     * @return boolean
     */
    public static boolean isNumeric(String aString) {
        if (aString == null) {
            return false;
        } else if (aString.isEmpty()) {
            return false;
        } else if (aString.indexOf(".") != aString.lastIndexOf(".")) {
            return false;
        } else {
            int counter = 0;
            for ( char c : aString.toCharArray()) {
                if ( Character.isDigit(c)) {
                    counter++;
                }
            }
            return counter == aString.length();
        }
    }

    /** A method to control specific GUI component actions. */
    private void getAddButtonSemantics() {
        if (!isNumeric(this.addTweet.getidTextField().getText()) ||
                Integer.parseInt(this.addTweet.getidTextField().getText()) < 0) {
            JOptionPane.showMessageDialog(null, "The specified ID is not a valid number",
                    "Tweet", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!this.twitterModel.contains(new Tweet(addTweet.getidTextField().getText(),
                    addTweet.getBodyTextField().getText()))) {
                this.twitterModel.add(new Tweet(addTweet.getidTextField().getText(),
                        this.addTweet.getBodyTextField().getText()));
                JOptionPane.showMessageDialog(null, "Tweet was successfully added",
                        "Tweet", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Tweet could not be added",
                        "Tweet", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    /** A method to control specific GUI component actions. */
    public void getClearButtonSemantics() {
        addTweet.getidTextField().setText("");
        addTweet.getBodyTextField().setText("");
        addTweet.getidTextField().requestFocus();
    }
}
