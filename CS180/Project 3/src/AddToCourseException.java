/**
 * AddToCourseException
 *
 * Exception thrown for invalid add operations involving the Course class.
 *
 * @author You
 *
 * @version date of completion
 *
 */

public class AddToCourseException extends Exception {
    /**
     * Constructs an AddToCourseException with the default message.
     *
=     */
    public AddToCourseException() {
        super("Can't add this course");
    }

    /**
     * Constructs an AddToCourseException with the provided message.
     *
     * @param message Message to be provided to the user in case of exception
     */
    public AddToCourseException(String message) {
        super(message);
    }
}
