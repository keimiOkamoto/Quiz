package controllers;

import java.util.ArrayList;

public interface Server {

    /**
     * Getter for available quizzes.
     *
     * @return An ArrayList of Quizzes.
     */
    ArrayList<Quiz> getQuizzes();
}
