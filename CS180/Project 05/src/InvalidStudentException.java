public class InvalidStudentException extends Exception {
    public InvalidStudentException() {
        super("Error: Not a valid student");
    }

    public InvalidStudentException(String message) {
        super(message);
    }
}
