package controllers;

import models.Player;
import models.Quiz;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface Server {

    /**
     * Getter for available quizzes.
     *
     * @return An ArrayList of Quizzes.
     */
    List<Quiz> getQuizzes();

    /**
     * Getter for a quiz.
     *
     * @param id A id for the quiz.
     * @return A quiz with the corresponding ID.
     */
    Quiz getQuiz(int id);

    /**
     * Creates a player.
     *
     * @param name The name of a player.
     * @param country The country of a player.
     * @param age The age of the player.
     * @return A player that has been created.
     */
    Player createPlayer(String name, String country, int age) throws RemoteException;

    /**
     * Getter for the winner of the a quiz.
     *
     * @return Winner of a quiz.
     * @param quizId
     */
    Player getWinnerBy(int quizId);

    /**
     * Sets the player as the winner.
     *
     * @param player The name of a player.
     * @param quiz The quiz the player was making.
     * @param score The score of the player.
     */
    void setPlayerAsWinner(Player player, Quiz quiz, int score) throws RemoteException;

    /**
     * Checks for a if the quiz is the high score;
     *
     * @param player A quiz player.
     * @param quiz A quiz.
     * @return false if the quiz is not the highest.
     */
    public boolean checkForHighScore(Quiz quiz, Player player) throws RemoteException;
}
