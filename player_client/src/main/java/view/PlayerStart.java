package view;

import controllers.Server;
import models.Player;
import models.Quiz;

import java.util.Scanner;

public interface PlayerStart {

    /**
     * Gets the quiz and allows the player to play the quiz.
     *
     * @return A useful message, that will
     * determine the next event.
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
     * Checks if the player scored the highest score.
     *
     * @param player A player of the quiz
     * @param quiz A quiz
     * @param server A server
     * @return A useful message if it is. That will
     * determine the next event.
     */
    String checkForHighScore(Player player, Quiz quiz, Server server);

    /**
     * Validates if the closed quiz is empty.
     *
     * @return A useful message, that will
     * determine the next event.
     */
    String checkIfClosedQuizIsNull();

    /**
     * Takes an input from the player to choose a closed quiz
     * to view it's information.
     *
     * @param scanner A scanner for the user to type input.
     * @param message A message that will determine the next event.
     * @return A useful message, that will
     * determine the next event.
     */
    String selectClosedQuiz(Scanner scanner, String message);

    /**
     * Gets the users choice for what to do.
     *
     * @param userInput A user input that will determine the next event.
     * @return A useful message, that will
     * determine the next event.
     */
    String getStartChoice(String userInput);
}
