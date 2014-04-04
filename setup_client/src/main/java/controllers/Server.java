package controllers;

import items.Answer;
import items.Question;
import items.Quiz;

import java.rmi.NotBoundException;
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
     * @return a quiz.
     */
    Quiz createQuiz(String title);

    /**
     * Creates a question.
     *
     * @param question A String question.
     * @return A Question object.
     */
    Question createQuestion(String question);

    /**
     * Creates answers.
     *
     * @param answer Answer to a quiz.
     * @return An Answer object.
     */
    Answer createAnswer(String answer);

    /**
     * Checks if the same quiz title already exists.
     *
     * @param title Title of a quiz.
     * @return false if quiz with the same tile already exists.
     */
    boolean valid(String title);

    /**
     * Closes a quiz with the corresponding id.
     *
     * @param id An ID of a quiz.
     */
    void closeQuiz(int id);

    /**
     * Checks if the quiz is contains.
     *
     * @param id Id of a quiz.
     * @return false if the ID does not exist.
     */
    boolean valid(int id);

    /**
     * Saves quiz to server.
     *
     * @param quiz A quiz.
     */
    void save(Quiz quiz);
}
