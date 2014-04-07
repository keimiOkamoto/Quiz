package models;

import models.Player;
import models.PlayerFactory;
import models.Quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for the quiz server.
 */
public interface QuizServer {

    /**
     * Getter for a list of available quizzes.
     *
     * @return A list of available quizzes.
     */
    List<Quiz> getQuizzes();

    /**
     * Getter for a quiz.
     *
     * @param id ID of a quiz.
     * @return A quiz with the corresponding ID.
     */
    Quiz getQuiz(int id);

    /**
     * Checks if the score is the highest store.
     *
     *
     * @param quiz A quiz.
     * @param player A player of a quiz.
     * @return False if the score is not the highest.
     */
    boolean checkForHighScore(Quiz quiz, Player player);

    /**
     * Getter for a player factory.
     *
     * @return Player object.
     */
    PlayerFactory getPlayerFactory();

    /**
     * Get winner by the quiz id.
     *
     * @param quizId The id of the quiz.
     * @return The winner of the quiz.
     */
    Player getWinnerBy(int quizId);

    /**
     * Setter for setting a player as a winner.
     *
     * @param player A player of a quiz.
     */
    void setPlayerAsWinner(Player player, Quiz quiz);

}
