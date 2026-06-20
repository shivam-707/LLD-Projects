package exceptions;

/**
 * Exception thrown when a restaurant operation fails.
 * Examples: restaurant not found, invalid location search.
 */
public class RestaurantNotFoundException extends Exception {
    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
