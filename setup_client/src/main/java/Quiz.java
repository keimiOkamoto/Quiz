public interface Quiz {
    /**
     * Getter for the title of a quiz
     *
     * @return Title of a quiz
     */
    String getTitle();

    /**
     * Adds a question to the quiz
     *
     * @param question a question
     */
    void addQuestion(Question question);

}
