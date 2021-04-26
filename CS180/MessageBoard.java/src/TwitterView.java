import javax.swing.*;

/**
 * TwitterView
 *
 * Serves the view portion of the MVC paradigm, controls the overall look (front end) of the entire message board.
 * Combines the four 'tabs' (AddTweet, EditTweet ...) together into one cohesive GUI.
 *
 * @author Dylan Martin
 *
 * @version April 8, 2019
 *
 */

public final class TwitterView {

    private JPanel mainPanel;

    private AddTweet addTweet;
    private DeleteTweet deleteTweet;
    private EditTweet editTweet;
    private SearchTweet searchTweet;

    private JTabbedPane mainTabbedPane;

    /** Default constructor for TwitterView*/
    public TwitterView() {
        this.addTweet = new AddTweet();
        this.editTweet = new EditTweet();
        this.deleteTweet = new DeleteTweet();
        this.searchTweet = new SearchTweet();

        /** Put all the "add, edit, delete, search" tabs to one main Twitter message board Feed 'menu' */

        this.mainTabbedPane.add("Add", this.addTweet.getMainPanel());
        this.mainTabbedPane.add("Edit", this.editTweet.getMainPanel());
        this.mainTabbedPane.add("Delete", this.deleteTweet.getMainPanel());
        this.mainTabbedPane.add("Search", this.searchTweet.getMainPanel());
    }

    /** Standard accessor methods for the private variables in TwitterView.java */

    /** Get the JTabbedPane component
     *
     * @return mainTabbedPane
     */
    public JTabbedPane getMainTabbedPane() { return this.mainTabbedPane; }

    /** Get the AddTweet component
     *
     * @return addTweet
     */
    public AddTweet getAddTweet() {
        return this.addTweet;
    }

    /** Get the EditTweet component
     *
     * @return editTweet
     */
    public EditTweet getEditTweet() {
        return this.editTweet;
    }

    /** Get the DeleteTweet component
     *
     * @return deleteTweet
     */
    public DeleteTweet getDeleteTweet() {
        return this.deleteTweet;
    }

    /** Get the SearchTweet component
     *
     * @return searchTweet
     */
    public SearchTweet getSearchTweet() {
        return this.searchTweet;
    }

    /** Get the JPanel component
     *
     * @return mainPanel
     */
    public JPanel getMainPanel() {
        return this.mainPanel;
    }

}
