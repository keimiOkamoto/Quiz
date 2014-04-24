package controllers;

import models.Quiz;

import java.util.List;

/**
 * A container class that contains and validates Quizzes.
 */
public interface QuizContainer {
    /**
     * Checks if a quiz with the same title exists.
     *
     * @param title A title of a Quiz.
     * @return true if the same name exists.
     */
    boolean contains(String title);

    /**
     * Checks if a quiz with the specified ID exists.
     *
     * @param id ID of a quiz.
     * @return true if the quiz with the same id exists.
     */
    boolean contains(int id);

    /**
     * Finds the Quiz with the corresponding ID.
     *
     * @param id ID of a quiz that is to be closed.
     */
    void closeQuizWith(int id);

    /**
     * Saves a quiz to the quiz container.
     *
     * @param quiz A quiz object to be saved.
     */
    void save(Quiz quiz);

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

    List<Quiz> getClosedQuizList();
}
