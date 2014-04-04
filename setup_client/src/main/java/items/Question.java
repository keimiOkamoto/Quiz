package items;

public interface Question {
    /**
     * Adds an answer to the question.
     *
     * @param answer an answer to the question.
     */
    void add(Answer answer);

    /**
     * Checks for duplicate answer
     *
     * @param answer an answer for a question
     * @return false if invalid
     */
    boolean valid(String answer);

}
