package controllers;

import java.util.ArrayList;

public interface Server {

    /**
     * Getter for available quizzes.
     *
     * @return An ArrayList of Quizzes.
     */
    ArrayList<Quiz> getQuizzes();

    /**
     * Getter for a quiz.
     *
     * @param id A id for the quiz.
     * @return A quiz with the corresponding ID.
     */
    Quiz getQuiz(int id);
}
