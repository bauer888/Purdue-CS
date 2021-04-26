/**
 * Tweet
 *
 * Serves the view portion of the MVC paradigm, controls the overall look (front end) of the AddTweet tab.
 *
 * @author Dylan Martin
 *
 * @version April 8, 2019
 *
 */

public class Tweet {

    private String id;
    private String body;

    /**
     * A default constructor
     */
    public Tweet() {
        this.id = null;
        this.body = null;
    }

    /** Constructor passing a Tweet object as a parameter
     * @param message Tweet object
     */
    public Tweet(Tweet message) {
        this.id = message.id;
        this.body = message.body;
    }

    /**
     * Constructor taking in ID and body strings as arguments
     * Throws an IllegalArgumentException if an ID is negative or a body is over 100 characters in length.
     * @param id String: A Tweet's ID.
     * @param body String: A Tweet's message.
     *
     * */
    public Tweet(String id, String body) throws IllegalArgumentException {
        if (Integer.parseInt(id) < 0) {
            throw new IllegalArgumentException("ID cannot be negative!");
        } else if (body.length() > 100) {
            throw new IllegalArgumentException("Body must not exceed 100 characters!");
        } else {
            this.id = id;
            this.body = body;
        }
    }

    /** Public Accessor methods for the private fields in Tweet */
    public String getID() { return this.id; }

    public String getBody() { return this.body; }

    /** Public Mutator methods for the private fields in Tweet */

    /** Sets the ID of a Tweet object
     * @param iD
     */
    public void setID(String iD) { this.id = iD; }


    /** Sets the body of a Tweet object
     * @param body
     */
    public void setBody(String body) { this.body = body; }
}
