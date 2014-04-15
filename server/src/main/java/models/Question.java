package models;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

public interface Question extends Remote {
    /**
     * Adds an answer to the question.
     *
     * @param answer an answer to the question.
     */
    void add(Answer answer);

    /**
     * Checks for duplicate answer
     *
     * @param answer an answer for a question
     * @return false if invalid
     */
    boolean contains(String answer);

    /**
     * Getter for question.
     *
     * @return A question String.
     */
    String getQuestion();

    /**
     * Getter for a set of answers belonging to the quiz.
     *
     * @return A set of answers.
     */
    Set<Answer> getAnswers();
}
