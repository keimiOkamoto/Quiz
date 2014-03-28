public class IllegalQuizException extends Exception {
    public IllegalQuizException() {
        super("Quiz does not exist. Please create a quiz and try again.");
    }
}
