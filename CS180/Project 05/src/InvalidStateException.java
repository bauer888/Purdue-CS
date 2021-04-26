public class InvalidStateException extends Exception {
    public InvalidStateException() {
        super("Error: Not a valid state");
    }

    public InvalidStateException(String message) {
        super(message);
    }
}
