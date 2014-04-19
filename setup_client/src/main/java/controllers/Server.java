package controllers;

import models.Answer;
import models.Question;
import models.Quiz;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This class is the connection between the setup client and the
 * server.
 */
public interface Server {
    /**
     * creates a quiz.
     *
     * @param title Title of a quiz.
     * @return a quiz with a title.
     * @throws java.rmi.RemoteException if there is a problem with the server.
     */
    Quiz createQuiz(String title) throws RemoteException;

    /**
     * Creates a question.
     *
     * @param question A question String.
     * @return A Question object.
     * @throws java.rmi.RemoteException
     */
    Question createQuestion(String question) throws RemoteException;

    /**
     * Creates answers.
     *
     * @param answer Answer to a question.
     * @param answerType a boolean value to signify w=if the answer is correct or incorrect.
     * @return An Answer object.
     * @throws java.rmi.RemoteException if there is a problem with the server.
     */
    Answer createAnswer(String answer, boolean answerType)throws RemoteException;

    /**
     * Checks if the same quiz title already exists.
     *
     * @param title Title of a quiz.
     * @return false if quiz with the same tile already exists.
     * @throws java.rmi.RemoteException if there is a problem with the server.
     */
    boolean valid(String title) throws RemoteException;

    /**
     * Checks if the quiz is contains.
     *
     * @param id Id of a quiz.
     * @return false if the ID does not exist.
     * @throws java.rmi.RemoteException if there is a problem with the server.
     */
    boolean valid(int id) throws RemoteException;

    /**
     * Saves quiz to server.
     *
     * @param quiz A quiz to be saved.
     * @throws java.rmi.RemoteException java.rmi.RemoteException if there is a problem with the server.
     */
    void save(Quiz quiz) throws RemoteException;

    /**
     * Closes a quiz with the corresponding id.
     *
     * @param id An ID of a quiz.
     * @throws java.rmi.RemoteException if there is a problem with the server.
     */
    void closeQuiz(int id) throws RemoteException;
}
