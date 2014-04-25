package controllers;

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
     * @return a quiz ID.
     * @throws java.rmi.RemoteException if there is a problem with the server.
     */
    int createQuiz(String title) throws RemoteException;

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
     * @throws java.rmi.RemoteException java.rmi.RemoteException if there is a problem with the server.
     */
    void save() throws RemoteException;

    /**
     * Closes a quiz with the corresponding id.
     *
     * @param id An ID of a quiz.
     * @throws java.rmi.RemoteException if there is a problem with the server.
     */
    void closeQuiz(int id) throws RemoteException;

    /**
     * Checks id the quiz is null.
     *
     * @return true if it is null.
     */
    boolean isQuizNull();

    /**
     * Checks if the quiz contains the same question.
     *
     * @param questionStr A question string.
     * @return true if a duplicate exists.
     */
    boolean quizContains(String questionStr);

    /**
     * Adds a question to a quiz.
     *
     * @param question A question.
     */
    void addQuestionToQuiz(String question);

    /**
     * Checks if the question is null.
     *
     * @return true if the question value is null.
     */
    boolean isQuestionNull();

    /**
     * Checks if the question contains the answer.
     *
     * @param answer An answer string.
     * @return True is the question contains an answer.
     */
    boolean questionContains(String answer);

    /**
     * Adds answer to question.
     *
     * @param answer An answer string.
     * @param answerType An answer value, true if corrects
     *                   false if incorrect.
     */
    void addToQuestion(String answer, boolean answerType);

    /**
     * checks if the quiz is empty.
     *
     * @return true if the quiz is empty.
     */
    boolean isQuizEmpty();
}
