package views;

/**
 * A class in charge of use input
 */
public interface UserInput {

    String type();

    boolean selectOption(String message);

    void enterTitle(String title);
}
