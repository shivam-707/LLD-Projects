package exceptions;

/**
 * Exception thrown when an order operation is invalid or fails validation.
 * Examples: null user, empty items list, invalid total.
 */
public class InvalidOrderException extends Exception {
    public InvalidOrderException(String message) {
        super(message);
    }

    public InvalidOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
