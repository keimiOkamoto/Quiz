package models;

/**
 * Interface for the quiz server.
 */
public interface QuizServer {

    /**
     * Checks if the title already exists.
     *
     * @param title A String title.
     * @return false if the same title already exists.
     */
    boolean titleIsValid(String title);

    /**
     * Checks if the ID exists.
     *
     * @param id An ID of a Quiz.
     * @return false if the ID doesn't exist.
     */
    boolean iDIsValid(int id);

    /**
     * Ends quiz by given ID.
     *
     * @param id The ID ofa quiz.
     */
    void endQuiz(int id);

    /**
     * Saves a quiz to the server.
     *
     * @param quiz A quiz.
     */
    void save(Quiz quiz);

    /**
     * Gets a ItemsFactory object.
     *
     * @return An ItemsFactory.
     */
    ItemsFactory getItemsFactory();
}
