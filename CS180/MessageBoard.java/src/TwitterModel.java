import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TwitterModel
 *
 * Serves the model portion of the MVC paradigm, and is responsable for modelling the 'behind the scenes'
 * structure of the Message Board.
 *
 * @author Dylan Martin
 *
 * @version April 8, 2019
 *
 */

public final class TwitterModel {

    private List<Tweet> tweetList;

    /** Creates a new TwitterModel object */
    public TwitterModel() {
        this.tweetList = new ArrayList<>();
    }

    /** A search for whether the TwitterModel contains a specified tweet
     * @param aTweet Tweet object
     * @return boolean
     */
    public boolean contains(Tweet aTweet) throws IllegalArgumentException {
        if (aTweet == null) throw new IllegalArgumentException(("aTweet argument is null"));
        else {
            for (Tweet tweet : this.tweetList) {
                if (tweet.getID() == null ? aTweet.getID() == null :
                        tweet.getID().equalsIgnoreCase(aTweet.getID())) {
                    return true;
                }
            }
            return false;
        }
    }

    /** Add a Tweet to the message board. If the tweet already exists, it will not be added.
     * @param aTweet Tweet object
     * @return boolean
     */
    public boolean add(Tweet aTweet) throws IllegalArgumentException {

        if (aTweet == null) {
            throw new IllegalArgumentException("aTweet argument is null!");
        } else if (this.contains(aTweet)) {
            return false;
        } else {
            this.tweetList.add(aTweet);
            return true;
        }
    }

    /** Remove a Tweet from the message board.
     * If the tweet does not exist, it will not remove any Tweets
     * @param aTweet Tweet object
     * @return tweetList
     */
    public boolean remove(Tweet aTweet) throws IllegalArgumentException {
        return this.tweetList.remove(aTweet);
    }


    /** Searches for a Tweet with a specific ID in the message board.
     * @param id numeric string representing a Tweet's ID
     * @return Optional<Tweet>
     */
    public Optional<Tweet> searchByID(String id) {
        return this.tweetList.stream()
                .filter(tweet -> (tweet.getID() == null ? id == null :
                        tweet.getID().equalsIgnoreCase(id)))
                .findAny();
    }

    /** Searches for a Tweet based on text (@param phrase) within the body
     * @param phrase text being searched for
     * @return List of applicable tweets
     */
    public List<Tweet> searchByText(String phrase) {
        return this.tweetList.stream()
                .filter(tweet -> (tweet.getBody() == null ? phrase == null :
                        tweet.getBody().contains(phrase)))
                .collect(Collectors.toList());
    }

    /** Returns the tweetList array
     * @return tweetList
     */
    public List getTweetList() {
        return this.tweetList;
    }
}
