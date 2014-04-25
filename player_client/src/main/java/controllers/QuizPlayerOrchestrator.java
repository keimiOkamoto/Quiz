package controllers;

import exceptions.IllegalGameException;
import exceptions.IllegalQuizException;
import models.Player;
import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;

public interface QuizPlayerOrchestrator {

    /**
     * Gets a list of available quizzes.
     *
     * @return A list of available quizzes.
     * @throws IllegalGameException Throws illegal games exception if no games exit.
     */
    List<Quiz> getQuizzes() throws IllegalGameException;

    /**
     * Gets a requested quiz by ID from the server.
     *
     * @param id The
     * @return A quiz corresponding with the id.
     * @throws IllegalQuizException if quiz with that particular ID does not exist.
     */
    Quiz getQuizBy(int id) throws IllegalQuizException;

    /**
     *
     * Adds a player with a name, country and age.
     *
     * @param name A name of a player.
     * @param country The country which the player is from.
     * @param age The age of the player.
     * @throws IllegalArgumentException If the name or aga is null.
     * @return the player that has been created.
     */
    Player addPlayer(String name, String country, int age) throws IllegalArgumentException, RemoteException;

    /**
     * Sets the player as the winner if the player's
     * score is the highest.
     *
     * @param player
     */
    void setPlayerAsWinner(Player player, Quiz quiz) throws RemoteException;

    void resetPlayerScore(Player player) throws RemoteException;

    /**
     * Getter for the winner player.
     *
     * @param quizId The id for a quiz.
     * @return The winner. (Player)
     */
    models.HighScore getWinner(int quizId) throws RemoteException;

    List<Quiz> getClosedQuizList() throws RemoteException;
}
