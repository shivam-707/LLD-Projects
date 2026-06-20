package exceptions;

/**
 * Exception thrown when a payment operation fails.
 * Examples: invalid card, insufficient funds, payment gateway error.
 */
public class PaymentFailedException extends Exception {
    public PaymentFailedException(String message) {
        super(message);
    }

    public PaymentFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
