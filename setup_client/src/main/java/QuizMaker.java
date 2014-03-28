public interface QuizMaker {
    /**
     * Creates a quiz and returns a unique id for
     * the quiz.
     *
     * @param title a title for the quiz
     * @return a unique id
     */
    int createQuiz(String title);

    /**
     * Getter for a title of a quiz
     *
     * @return a title of a quiz
     */
    String getTitle();

    /**
     * Adds a question to a qiz
     *
     * @param question for a quiz
     */
    void addQuestion(Question question) throws IllegalQuizException;
}
