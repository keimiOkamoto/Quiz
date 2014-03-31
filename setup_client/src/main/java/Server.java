public interface Server {
    /**
     * creates a quiz.
     *
     * @param title Title of a quiz
     * @return a quiz
     */
    Quiz createQuiz(String title);

    /**
     * Creates a question
     *
     * @param questionString
     * @return
     */
    Question createQuestion(String questionString);

    /**
     * Creates answers
     *
     * @param answer String
     * @return Answer object
     */
    Answer createAnswer(String answer);
}
