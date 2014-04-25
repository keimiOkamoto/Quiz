package controllers;

import factories.ItemsFactory;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the quiz server to the setup client and the player client.
 */
public interface QuizServer extends Remote {

    /**
     * Checks if the title already exists.
     *
     * @param title A title string.
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
     * Ends quiz by ID of a quiz.
     *
     * @param id The ID of a quiz to be closed.
     * @throws RemoteException if there is a problem with the server/connection.
     */
    void endQuiz(int id) throws RemoteException;

    /**
     * Saves a quiz to the server.
     *
     * @throws RemoteException
     */
    void save() throws RemoteException;

    /**
     * Gets a ItemsFactory object.
     *
     * @return An ItemsFactory.
     * @throws RemoteException
     */
    ItemsFactory getItemsFactory() throws RemoteException;

    /**
     * Generates a Quiz
     *
     * @param title Title of a quiz
     * @return A quiz.
     */
    int generateQuiz(String title) throws RemoteException;

    /**
     * Checks if the Quiz value is null.
     *
     * @return true if it is null.
     */
    boolean isQuizNull();

    /**
     * Checks if the quiz contains the same question.
     *
     * @param questionStr The question for the quiz.
     * @return true if it does contain duplicates.
     */
    boolean quizContains(String questionStr);

    /**
     * Adds a questions string to the quiz.
     *
     * @param question A question for the quiz.
     */
    void addQuestionToQuiz(String question);

    /**
     * Checks if the question value is null.
     *
     * @return true if it is null.
     */
    boolean isQuestionNull();

    /**
     * Checks if the quiz contains the same answer.
     *
     * @param answer An answer String for the question.
     * @return trues id it does contain duplicates.
     */
    boolean questionContains(String answer);

    /**
     * Adds the answer and the answer value, true for correct
     * and false for incorrect.
     *
     * @param answer An answer for the question.
     * @param answerType true for correct
     * and false for incorrect.
     */
    void addToQuestion(String answer, boolean answerType);

    /**
     * Checks if the Quiz is empty.
     *
     * @return true if it is empty.
     */
    boolean isQuizEmpty();
}
