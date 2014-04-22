package view;

import controllers.Server;
import models.Player;
import models.Quiz;

import java.util.Scanner;

public interface PlayerStart {

    /**
     * Gets the quiz and allows the player to play the quiz.
     *
     * @return the message that will lead to the next event.
     */
    Quiz getQuiz();

    /**
     * Method that sets a player up.
     *
     * @param scanner A scanner for the user to type in
     * @param player An empty player object.
     * @return A player with the name, age and country assigned by the user.
     */
    Player makePlayer(Scanner scanner, Player player);

    /**
     * Sets the quiz index.
     *
     * @param numberIndex a number for the index number of the quiz.
     */
    void setQuizNumber(int numberIndex);

    /**
     *
     *
     * @param player A player of the quiz
     * @param quiz A quiz
     * @param server A server
     * @returnA useful message if it is. That will
     * determine the next event.
     */
    String checkForHighScore(Player player, Quiz quiz, Server server);

    /**
     * Validates if the closed quiz is empty.
     *
     * @return A useful message if it is. That will
     * determine the next event.
     */
    String checkIfClosedQuizIsNull();

    /**
     *
     *
     * @param scanner
     * @param message
     * @return
     */
    String selectClosedQuiz(Scanner scanner, String message);

    String getStartChoice(String userInput);

}
