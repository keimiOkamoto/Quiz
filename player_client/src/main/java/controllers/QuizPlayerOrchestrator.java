package controllers;

import java.util.ArrayList;

public interface QuizPlayerOrchestrator {
    /**
     * Gets a list of available quizzes.
     *
     */
    ArrayList<Quiz> getQuizzes() throws IllegalGameException;
}
