package controllers;

import factories.ItemsFactory;
import models.*;

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
     * Generate Question.
     *
     * @param question A Question
     * @return A question
     */
//    Question generateQuestion(String question) throws RemoteException;

    /**
     * Generate Answer
     *
     * @param answer A Answer
     * @param answerType False ig it is not the right answer, true if it is.
     * @return An answer
     */
//    Answer generateAnswer(String answer, boolean answerType) ;

    boolean isQuizNull();

    boolean quizContains(String questionStr);

    void addQuestionToQuiz(String question);

    boolean isQuestionNull();

    boolean questionContains(String answer);

    void addToQuestion(String answer, boolean answerType);

    boolean isQuizEmpty();
}
