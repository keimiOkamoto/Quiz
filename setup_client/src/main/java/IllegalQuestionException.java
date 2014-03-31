/**
 * IllegalQuestionException class created. Exception for a question that does
 * not exist.
 */
public class IllegalQuestionException extends Exception {
    /**
     * Exception with a message that notifies user
     * that a question does not exist.
     */
    public IllegalQuestionException () {
        super("Question doesn't exist. There must be a question to have an answer!");
    }
}
