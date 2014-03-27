public interface QuizMaker {
    /**
     * Creates a quiz and returns a unique id for
     * the quiz.
     *
     * @param title a title for the quiz
     * @return a unique id
     */
    int createQuiz(String title);

    String getTitle();
}
