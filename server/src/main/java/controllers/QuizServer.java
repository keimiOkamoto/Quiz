package controllers;

import factories.ItemsFactory;
import factories.PlayerFactory;
import models.Player;
import models.Quiz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface for the quiz server.
 */
public interface QuizServer extends Remote {

    /**
     * Checks if the title already exists.
     *
     * @param title A String title.
     * @return false if the same title already exists.
     * @throws RemoteException
     */
    boolean titleIsValid(String title) throws RemoteException;

    /**
     * Checks if the ID exists.
     *
     * @param id An ID of a Quiz.
     * @return false if the ID doesn't exist.
     * @throws RemoteException
     */
    boolean iDIsValid(int id) throws RemoteException;

    /**
     * Ends quiz by given ID.
     *
     * @param id The ID ofa quiz.
     * @throws RemoteException
     */
    void endQuiz(int id) throws RemoteException;

    /**
     * Saves a quiz to the server.
     *
     * @param quiz A quiz.
     * @throws RemoteException
     */
    void save(Quiz quiz) throws RemoteException;

    /**
     * Gets a ItemsFactory object.
     *
     * @return An ItemsFactory.
     * @throws RemoteException
     */
    ItemsFactory getItemsFactory() throws RemoteException;




    /********** Player client methods ***********/

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
