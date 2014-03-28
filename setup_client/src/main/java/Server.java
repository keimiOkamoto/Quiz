public interface Server {
    /**
     * creates a quiz
     * @param title Title of a quiz
     * @return a quiz
     */
    Quiz createQuiz(String title);
}
