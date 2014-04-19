package controllers;

import models.Quiz;

import java.util.List;

/**
 * A container that holds quizzes closed by the setup client.
 */
public interface ClosedQuizContainer {
    /**
     * Adds a quiz that has been closed.
     *
     * @param closedQuiz A Quiz that has been closed.
     */
    void add(Quiz closedQuiz);

    /**
     * Getter for a list of closed quizzes.
     *
     * @return A list of closed quizzes.
     */
    List<Quiz> getClosedQuizList();

    /**
     * Getter for a quiz that has been closed.
     *
     * @param id ID of a quiz that has been closed.
     * @return A previously closed quiz.
     */
    Quiz getQuiz(int id);
}
