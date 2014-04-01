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
     * Checks for a vaild quiz name.
     *
     * @param title A quiz title.
     * @return false if the title already exist.
     */
    boolean titleIsValid(String title);
}
