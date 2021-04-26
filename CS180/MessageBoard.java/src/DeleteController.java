import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/**
 * DeleteController
 *
 * Serves the controller portion of the MVC paradigm, controls the overall function of the DeleteTweet view.
 *
 * @author Jack Bauer, bauer88
 *
 * @version April 8, 2019
 *
 */

public final class DeleteController {

    private TwitterModel twitterModel;
    private DeleteTweet deleteTweet;

    /** Implement the DeleteController constructor per the instructions in the handout
     * @param twitterModel TwitterModel object connecting the model and controller portions of the MVC paradigm.
     * @param deleteTweet DeleteTweet object connecting the view and controller portions of the MVC paradigm.
     */
    public DeleteController(TwitterModel twitterModel, DeleteTweet deleteTweet) throws IllegalArgumentException {
        if (twitterModel == null || deleteTweet == null) {
            throw new IllegalArgumentException("Arguments can not be null");
        } else {
            this.twitterModel = twitterModel;
            this.deleteTweet = deleteTweet;

            deleteTweet.getDeleteButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getDeleteButtonSemantics();
                }
            });

            deleteTweet.getClearButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getClearButtonSemantics();
                }
            });
        }
    }

    /** A method to control specific GUI component actions. */
    private void getDeleteButtonSemantics() {
        if (!this.twitterModel.contains(new Tweet(this.deleteTweet.getidTextField().getText(),
                this.twitterModel.searchByID(deleteTweet.getidTextField().getText()).toString()))) {
            JOptionPane.showMessageDialog(null, "A message with the given ID does not exist!",
                    "Tweet", JOptionPane.ERROR_MESSAGE);
        } else {
            this.twitterModel.remove(new Tweet(this.deleteTweet.getidTextField().getText(),
                    this.twitterModel.searchByID(deleteTweet.getidTextField().getText()).toString()));
            JOptionPane.showMessageDialog(null, "You have successfully deleted a Tweet!",
                    "Tweet", JOptionPane.INFORMATION_MESSAGE);
            deleteTweet.getidTextField().setText("");
            deleteTweet.getidTextField().requestFocus();
        }
    }

    /** A method to control specific GUI component actions. */
    private void getClearButtonSemantics() {
        deleteTweet.getidTextField().setText("");
    }
}
