package models;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface Quiz extends Remote {

    /**
     * Check if the score is the highest score.
     *
     * @return False if it is not the highest score.
     * @param score The score of a qui.
     */
    boolean checkForHighScore(int score) throws RemoteException;

    /****************** Server methods *******************/

    /**
     * Adds a uestion to the quiz.
     *
     * @param question a question
     */
    void addQuestion(Question question) throws RemoteException;

    /**
     * Checks if the question is contains.
     *
     * @param question A question
     * @return false if the question already exists
     */
    boolean contains(String question) throws RemoteException;

    /**
     * Checks if the quiz contains and questions.
     *
     * @return false if it contains one or more questions.
     */
    boolean isEmpty() throws RemoteException;

    /**
     * Getter for the title of a quiz.
     *
     * @return Title of a quiz
     */
    String getTitle() throws RemoteException;

    /**
     * Getter for the quiz's ID.
     *
     * @return Id of a quiz
     */
    int getId() throws RemoteException;

    /**
     * Getter for all questions within a quiz.
     *
     * @return A set containing all questions in a quiz.
     */
    Set<Question> getQuestions() throws RemoteException;

    /* player client methods
     */

    /**
     * Getter for a Score.
     *
     * @return A Score for a player.
     */
    int getScore() throws RemoteException;

    /**
     * Increments the score if a correct
     * answer is entered.
     */
    void incrementScore() throws RemoteException;
}