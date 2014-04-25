package controllers;

import factories.ItemsFactory;
import factories.PlayerFactory;
import models.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface for the setup and user clients.
 */
public interface QuizServer extends Remote {

    /**
     * Checks if the title already exists.
     *
     * @param title A String title.
     * @return false if the same title already exists.
     * @throws RemoteException if there is a problem with the server/connection.
     */
    boolean titleIsValid(String title) throws RemoteException;

    /**
     * Checks if the ID exists.
     *
     * @param id An ID of a Quiz.
     * @return false if the ID doesn't exist.
     * @throws RemoteException if there is a problem with the server/connection.
     */
    boolean iDIsValid(int id) throws RemoteException;

    /**
     * Saves a quiz to the server.
     *
     * @throws RemoteException if there is a problem with the server/connection.
     */
    void save() throws RemoteException;

    /**
     * Ends quiz by given ID.
     *
     * @param id The ID ofa quiz.
     * @throws RemoteException if there is a problem with the server/connection.
     */
    void endQuiz(int id) throws RemoteException;

    /**
     * Gets a ItemsFactory object.
     *
     * @return An ItemsFactory.
     * @throws RemoteException if there is a problem with the server/connection.
     */
    ItemsFactory getItemsFactory() throws RemoteException;

    /**
     * Generates a Quiz
     *
     * @param title Title of a quiz
     * @return A quiz ID.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    int generateQuiz(String title) throws RemoteException;

    /**
     * Generate Question.
     *
     * @param question A Question
     * @return A question
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
//    Question generateQuestion(String question) throws RemoteException;

    /**
     * Generate Answer
     *
     * @param answer A Answer
     * @param answerType False ig it is not the right answer, true if i is.
     * @return An answer
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
//    Answer generateAnswer(String answer, boolean answerType) throws RemoteException;


    /********** Player client methods ***********/

    Question generateQuestion(String question) throws RemoteException;

    Answer generateAnswer(String answer, boolean answerType) throws RemoteException;

    /**
     * Getter for a list of available quizzes.
     *
     * @return A list of available quizzes.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    List<Quiz> getQuizzes() throws RemoteException;

    /**
     * Getter for a quiz.
     *
     * @param id ID of a quiz.
     * @return A quiz with the corresponding ID.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    Quiz getQuiz(int id) throws RemoteException;

    /**
     * Checks if the score is the highest score.
     *
     * @param quiz A quiz.
     * @param player A player of the quiz.
     * @return False if the score is not the highest.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    boolean checkForHighScore(Quiz quiz, Player player)throws RemoteException;

    /**
     * Getter for a player factory.
     *
     * @return Player object.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    PlayerFactory getPlayerFactory() throws RemoteException;

    /**
     * Setter for setting a player as a winner.
     *
     * @param quiz A quiz that has been played.
     * @param player A player of a quiz.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    void setPlayerAsWinner(Quiz quiz, Player player) throws RemoteException;

    /**
     * Get winner by the quiz id.
     *
     * @param quizId The id of the quiz.
     * @return The winner of the quiz.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
     HighScore getWinnerBy(int quizId) throws RemoteException;

    /**
     * Generates a Player.
     *
     * @param name The name of the player.
     * @param country The country of the player.
     * @param age The age of the player.
     * @return A player with the above fields.
     * @throws RemoteException If there is a problem with the server.
     */
    Player generatePlayer(String name, String country, int age) throws RemoteException;

    /**
     * Resets the player score.
     *
     * @param player A player of the score to be reset.
     * @throws RemoteException if there is a problem with the server/connection.
     */
    void resetPlayerScore(Player player) throws RemoteException;

    /**
     * Getter for a closed quiz list.
     *
     * @return a list of closed quizzes.
     * @throws RemoteException if there is a problem with the server/connection.
     */
    List<Quiz> getClosedQuizList() throws RemoteException;

    boolean isQuizNull() throws RemoteException;

    boolean quizContains(String questionStr) throws RemoteException;

    void addQuestionToQuiz(String question) throws RemoteException;

    boolean isQuestionNull() throws RemoteException;

    boolean questionContains(String answer) throws RemoteException;

    void addToQuestion(String answer, boolean answerType) throws RemoteException;

    boolean isQuizEmpty() throws RemoteException;

}
