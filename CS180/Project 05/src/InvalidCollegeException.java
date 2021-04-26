public class InvalidCollegeException extends Exception {
    public InvalidCollegeException() {
        super("Error: Not a valid college");
    }

    public InvalidCollegeException(String message) {
        super(message);
    }
}
