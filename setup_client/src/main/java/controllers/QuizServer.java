package controllers;

import factories.PlayerFactory;
import models.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
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

    /**
     * Get winner by the quiz id.
     *
     * @param quizId The id of the quiz.
     * @return The winner of the quiz.
     */
    Player getWinnerBy(int quizId) throws RemoteException;

    /**
     * Generates a Quiz
     *
     * @param title Title of a quiz
     * @return A quiz.
     */
    Quiz generateQuiz(String title) throws RemoteException;

    /**
     * Generate Question.
     *
     * @param question A Question
     * @return A question
     */
    Question generateQuestion(String question) throws RemoteException;

    /**
     * Generate Answer
     *
     * @param answer A Answer
     * @param answerType False ig it is not the right answer, true if i is.
     * @return An answer
     */
    Answer generateAnswer(String answer, boolean answerType) ;


    /********** Player client methods ***********/

    /**
     * Getter for a list of available quizzes.
     *
     * @return A list of available quizzes.
     */
    List<Quiz> getQuizzes() throws RemoteException;

    /**
     * Getter for a quiz.
     *
     * @param id ID of a quiz.
     * @return A quiz with the corresponding ID.
     */
    Quiz getQuiz(int id) throws RemoteException;

    /**
     * Checks if the score is the highest score.
     *
     *
     * @param quiz A quiz.
     * @return False if the score is not the highest.
     */
    boolean checkForHighScore(Quiz quiz) throws RemoteException;

    /**
     * Getter for a player factory.
     *
     * @return Player object.
     */
    PlayerFactory getPlayerFactory() throws RemoteException;

    /**
     * Setter for setting a player as a winner.
     *
     * @param player A player of a quiz.
     */
    void setPlayerAsWinner(Quiz quiz, Player player) throws RemoteException;


}