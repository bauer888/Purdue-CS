import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

/**
 * SearchController
 *
 * Serves the controller portion of the MVC paradigm, controls the overall function of the SearchTweet view.
 *
 * @author Jack Bauer, bauer88
 *
 * @version April 8, 2019
 *
 */

public class SearchController {

    private TwitterModel twitterModel;
    private SearchTweet searchTweet;

    /** Implement the SearchController constructor per the instructions in the handout
     * @param twitterModel TwitterModel object connecting the model and controller portions of the MVC paradigm.
     * @param searchTweet SearchTweet object connecting the view and controller portions of the MVC paradigm.
     */
    public SearchController(TwitterModel twitterModel, SearchTweet searchTweet) throws IllegalArgumentException {
        if (twitterModel == null || searchTweet == null) {
            throw new IllegalArgumentException("Arguments can not be null");
        } else {
            this.twitterModel = twitterModel;
            this.searchTweet = searchTweet;

            searchTweet.getSearchButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getSearchButtonSemantics();
                }
            });
        }
    }

    /**
     * Gets the output {@code String} for the specified product list.
     * @param tweetList the product list to be converted to an output {@code String}
     * @return the output {@code String} for the specified product list
     */
    private String getOutputString(List<? extends Tweet> tweetList) {
        StringBuilder stringBuilder = new StringBuilder();
        String outString;

        for (Tweet aTweet : tweetList) {
            stringBuilder.append(aTweet).append("\n\n");
        }
        outString = stringBuilder.toString();

        if (outString.length() > 0) {
            outString = stringBuilder.substring(0, stringBuilder.length() - 2);
        }
        return outString;
    }

    /** Searches our TweetList for a given phrase, returning a List containing all Tweets that apply
     * @param body String of text being searched for
     * @return list of applicable Tweets.
     */
    private List searchForString(String body) {
        List<Tweet> list = this.twitterModel.searchByText(body);
        return list;
    }

    /** A method to control specific GUI component actions. */
    private void getSearchButtonSemantics() {
        String empty = "";
        for (int i = 0; i < twitterModel.getTweetList().size(); i++) {
            Tweet thisOne = (Tweet) twitterModel.getTweetList().get(i);
            if ((thisOne.getBody()).contains(searchTweet.getSearchTermTextField().getText())) {
                empty += thisOne.getID() + "\n";
            }
        }
    }
}
