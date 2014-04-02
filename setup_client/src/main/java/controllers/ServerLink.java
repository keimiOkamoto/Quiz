package controllers;

/**
 * This class links the setup client to the server.
 */
public interface ServerLink {

    /**
     * @return an ItemsFactory
     */
    ItemsFactory getItemsFactory();

    /**
     * Checks for a valid quiz name.
     *
     * @param title A quiz title.
     * @return false if the title already exist.
     */
    boolean titleIsValid(String title);

    /**
     * Ends a quiz by quoting ID
     *
     * @param id ID of a quiz.
     */
    void endQuiz(int id);

    /**
     * Checks for a valid quiz ID.
     *
     * @param id ID of a quiz.
     * @return false if the ID does not exist.
     */
    boolean iDIsValid(int id);
}
