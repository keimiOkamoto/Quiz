package items;

/**
 * A container class that contains and validates Quizs.
 */
public interface QuizContainer {
    /**
     * Checks if a quiz with the same title exists.
     *
     * @param title A title of a Quiz.
     * @return False if the same name exists.
     */
    boolean hasValid(String title);

    /**
     * Checks if a quiz with the specified ID exists.
     *
     * @param id ID of a quiz.
     * @return False if the quiz does not exist.
     */
    boolean hasValid(int id);

    /**
     * Finds the Quiz with the corresponding ID.
     *
     * @param id ID of a quiz.
     */
    void closeQuizWith(int id);

    /**
     * Saves a quiz to a container.
     *
     * @param quiz A quiz object.
     */
    void save(Quiz quiz);

    Quiz getQuizBy(int id);
}
