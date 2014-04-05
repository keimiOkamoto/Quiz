package controllers;

import exceptions.IllegalQuizException;

import java.util.ArrayList;

public interface QuizPlayerOrchestrator {

    /**
     * Gets a list of available quizzes.
     *
     * @return A list of available quizzes.
     * @throws IllegalGameException Throws illegal games exception if no games exit.
     */
    ArrayList<Quiz> getQuizzes() throws IllegalGameException;

    /**
     * Gets a requested quiz by ID from the server.
     *
     * @param id The
     * @return A quiz corresponding with the id.
     */
    Quiz getQuizBy(int id) throws IllegalQuizException;
}
