public interface QuizMaker {
    /**
     * Creates a quiz and returns a unique id for
     * the quiz.
     *
     * @param title a title for the quiz
     * @return a unique id
     */
    int createQuiz(String title) throws IllegalArgumentException;

    /**
     * Getter for a title of a quiz
     *
     * @return a title of a quiz
     */
    String getTitle();

    /**
     * Adds a question to a quiz.
     *
     * @param question for a quiz
     * @throws IllegalQuizException if quiz does not exist.
     * @throws IllegalArgumentException if question is null or empty.
     */
    void addQuestion(String question) throws IllegalQuizException, IllegalArgumentException;

    void addAnswer(String answer);


}
