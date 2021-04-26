import javax.swing.*;
import java.awt.*;

/**
 * A Driver class for a personal Message board application.
 *
 * <P>CS18000 -- Spring 2019 -- Complex GUIs -- Homework 12</P>
 *
 * @author Dylan Martin, Marina Wadea, Logan Kulinski
 * @version April 8, 2019
 */

public final class MessageBoardRunner {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Personal Message Board ");

            TwitterModel twitterModel = new TwitterModel();
            TwitterView twitterView = new TwitterView();

            new AddController(twitterModel, twitterView.getAddTweet());
            new EditController(twitterModel, twitterView.getEditTweet());
            new DeleteController(twitterModel, twitterView.getDeleteTweet());
            new SearchController(twitterModel, twitterView.getSearchTweet());

            /** Frame settings for the GUI */
            frame.setContentPane(twitterView.getMainPanel());
            frame.getContentPane().setPreferredSize(new Dimension(400, 400));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Do NOT use EXIT ON CLOSE
            frame.setLayout(null);
            frame.setVisible(true);



        });
    }
}
