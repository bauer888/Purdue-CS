/**
 * DropFromCourseException
 *
 * Exception thrown for invalid drop operations involving the Course class.
 *
 * @author You
 *
 * @version date of completion
 *
 */

public class DropFromCourseException extends Exception {
    /**
     * Constructs a DropFromCourseException with the default message.
     */
    public DropFromCourseException() {
        super("Can't drop this course");
    }

    /**
     * Constructs a DropFromCourseException with the provided message.
     *
     * @param message Message to be provided to the user in case of exception
     */
    public DropFromCourseException(String message) {
        super(message);
    }
}
