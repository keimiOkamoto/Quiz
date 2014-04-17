package models;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface Quiz extends Remote {

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

}
