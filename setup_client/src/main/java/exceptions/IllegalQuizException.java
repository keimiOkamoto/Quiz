package exceptions;

/**
 * IllegalQuizException class created. Exception for a quiz that does
 * not exist.
 */
public class IllegalQuizException extends Exception {
    /**
     * Exception with a helpful message.
     *
     * @param message A helpful message.
     */
    public IllegalQuizException(String message) {
        super(message);
    }
}
