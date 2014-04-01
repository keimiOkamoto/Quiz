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

    /**
     * Checks if the same quiz title already exists.
     *
     * @param title Title of a quiz
     * @return false if quiz with the same tile already exists.
     */
    boolean valid(String title);

    /**
     * Closes a quiz with the corresponding id.
     *
     * @param id
     */
    void closeQuiz(int id);
}
