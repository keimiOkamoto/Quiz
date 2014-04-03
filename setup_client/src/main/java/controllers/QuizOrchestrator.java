package controllers;

import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;
import items.Quiz;

public interface QuizOrchestrator {

    /**
     * Creates a quiz and returns a unique id for the quiz.
     *
     * @param title a title for the quiz
     * @return a unique id
     * @throws IllegalArgumentException
     * @throws exceptions.IllegalQuizException if the name of the quiz already exists.
     */
    int createQuiz(String title) throws IllegalArgumentException, IllegalQuizException;

    /**
     * Getter for a title of a quiz
     *
     * @return a title of a quiz
     */
    String getTitle();

    /**
     * Adds a question to a quiz.
     *
     * @param question for a quiz
     * @throws IllegalQuizException if quiz does not exist.
     * @throws IllegalArgumentException if question is null or empty.
     */
    void addQuestion(String question) throws IllegalQuizException, IllegalArgumentException;

    /**
     * Add answer to a question.
     *
     * @param answer to a question.
     * @throws IllegalQuestionException if question does not exist.
     * @throws IllegalArgumentException if answer is null or empty.
     */
    void addAnswer(String answer) throws IllegalArgumentException, IllegalQuestionException;

    /**
     * Closes a quiz with the corresponding ID.
     *
     * @param id ID of a quiz.
     */
    void closeQuiz(int id) throws IllegalQuizException;

    /**
     * Saves quiz to server.
     *
     * @param quiz A quiz.
     */
    void save(Quiz quiz)throws IllegalQuizException;
}
