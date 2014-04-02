package items;

/**
 * Interface for the quiz server.
 */
public interface QuizServer {

    /**
     * Gets a ItemsFactory object.
     *
     * @return An ItemsFactory.
     */
    ItemsFactory getItemsFactory();

    /**
     * Checks if the title already exists.
     *
     * @param title A String title.
     * @return false if the same title already exists.
     */
    boolean titleIsValid(String title);

    /**
     * Ends quiz by given ID.
     *
     * @param id The ID ofa quiz.
     */
    void endQuiz(int id);

    /**
     * Checks if the ID exists.
     *
     * @param id An ID of a Quiz.
     * @return false if the ID doesn't exist.
     */
    boolean iDIsValid(int id);
}
