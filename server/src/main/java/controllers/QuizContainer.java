package controllers;

import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;

/**
 * A container class that contains and validates Quizzes.
 */
public interface QuizContainer {
    /**
     * Checks if a quiz with the same title exists.
     *
     * @param title A title of a Quiz.
     * @return False if the same name exists.
     */
    boolean contains(String title) throws RemoteException;

    /**
     * Checks if a quiz with the specified ID exists.
     *
     * @param id ID of a quiz.
     * @return False if the quiz does not exist.
     */
    boolean contains(int id);

    /**
     * Finds the Quiz with the corresponding ID.
     *
     * @param id ID of a quiz.
     */
    void closeQuizWith(int id) throws RemoteException;

    /**
     * Saves a quiz to a container.
     *
     * @param quiz A quiz object.
     */
    void save(Quiz quiz) throws RemoteException;

    /**
     * Getter for a quiz by ID.
     *
     * @param id ID of a quiz.
     * @return A Quiz object.
     */
    Quiz getQuizBy(int id);

    /**
     * Getter for a list of quizzes
     *
     * @return A list of available quizzes.
     */
    List<Quiz> getQuizzes();
}
