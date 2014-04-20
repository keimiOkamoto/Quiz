package models;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * A quiz for players to play.
 */
public interface Quiz extends Remote {

    /**
     * Adds a question to the quiz.
     *
     * @param question a question
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    void addQuestion(Question question) throws RemoteException;

    /**
     * Checks if the question is contains.
     *
     * @param question A question
     * @return false if the question already exists
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    boolean contains(String question) throws RemoteException;

    /**
     * Checks if the quiz contains and questions.
     *
     * @return false if it contains one or more questions.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    boolean isEmpty() throws RemoteException;

    /**
     * Getter for the title of a quiz.
     *
     * @return Title of a quiz
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    String getTitle() throws RemoteException;

    /**
     * Getter for the quiz's ID.
     *
     * @return Id of a quiz
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    int getId() throws RemoteException;

    /**
     * Getter for all questions within a quiz.
     *
     * @return A set containing all questions in a quiz.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    Set<Question> getQuestions() throws RemoteException;
}