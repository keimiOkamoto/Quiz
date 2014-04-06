package controllers;

import java.util.ArrayList;

/**
 * Interface for the quiz server.
 */
public interface QuizServer {

    /**
     * Getter for a list of available quizzes.
     *
     * @return
     */
    ArrayList<Quiz> getQuizzes();

    Quiz getQuiz(int id);
}
