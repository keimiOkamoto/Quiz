package controllers;

import java.util.ArrayList;

public interface Server {

    /**
     * Getter for available quizzes.
     *
     * @return An ArrayList of Quizzes.
     */
    ArrayList<Quiz> getQuizzes();

    /**
     * Getter for a quiz.
     *
     * @param id A id for the quiz.
     * @return A quiz with the corresponding ID.
     */
    Quiz getQuiz(int id);

    /**
     * Checks if the score is the highest store.
     *
     * @param player
     * @return False if the score is not the highest.
     */
    boolean checkForHighScore(Player player);

    /**
     * Creates a player.
     *
     * @param name The name of a player.
     * @param country The country of a player.
     * @param age The age of the player.
     */
    void createPlayer(String name, String country, int age);

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
     * @param player
     */
    void setPlayerAsWinner(Player player);
}
