/**
 * IllegalQuestionException class created. Exception for a question that does
 * not exist.
 */
public class IllegalQuestionException extends Exception {

    /**
     * Constructor for IllegalQuestionException
     *
     * @param message A message fot the user
     */
    public IllegalQuestionException(String message) {
        super(message);
    }
}
