/**
 * IllegalQuizException class created. Exception for a quiz that does
 * not exist.
 */
public class IllegalQuizException extends Exception {
    /**
     * Exception with a message that notifies user
     * that a quiz does not exist.
     */
    public IllegalQuizException(String message) {
        super(message);
    }
}
