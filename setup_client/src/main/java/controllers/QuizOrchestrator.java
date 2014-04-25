package controllers;

import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;

import java.rmi.RemoteException;

/**
 * This class orchestrates the creation of a quiz and the closing of
 * a quiz.
 */

public interface QuizOrchestrator {

    /**
     * Creates a quiz and returns a unique id for the quiz.
     *
     * @param title a title for the quiz
     * @return a unique id
     * @throws IllegalArgumentException if the name entered is null or an empty string.
     * @throws exceptions.IllegalQuizException if the name of the quiz already exists.
     * @throws java.rmi.RemoteException if there is a problem with the server.
     */
    int createQuiz(String title) throws IllegalArgumentException, IllegalQuizException, RemoteException;

    /**
     * Adds a question to a quiz.
     *
     * @param question for a quiz
     * @throws IllegalQuizException if quiz does not exist.
     * @throws IllegalArgumentException if question is null or an empty string.
     * @throws java.rmi.RemoteException if there is a problem with the server.
     */
    void addQuestion(String question) throws  IllegalArgumentException, IllegalQuizException, RemoteException;

    /**
     * Add answer to a question.
     *
     * @param answer to a question.
     * @throws IllegalQuestionException if question does not exist.
     * @throws IllegalArgumentException if answer is null or an empty string.
     * @throws java.rmi.RemoteException if there is a problem with the server.
     */
    void addAnswer(String answer, boolean answerType) throws IllegalArgumentException, IllegalQuestionException, RemoteException;

    /**
     * Saves quiz to the server.
     *
     * @throws IllegalQuizException if quiz is null.
     * @throws java.rmi.RemoteException if there is a problem with the server.
     */
    void save() throws IllegalQuizException, RemoteException;

    /**
     * Closes a quiz with the corresponding ID.
     *
     * @param id ID of a quiz.
     * @throws IllegalQuizException if quiz does not exist or id quiz without
     * questions is attempted to be saved.
     * @throws java.rmi.RemoteException if there is a problem with the server.
     */
    void closeQuiz(int id) throws IllegalQuizException, RemoteException;

    /**
     * Getter for the title of the quiz.
     *
     * @return a title of the quiz.
     */
    String getTitle();
}
