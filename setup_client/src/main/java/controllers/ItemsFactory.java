package controllers;

import items.Question;
import items.Quiz;

public interface ItemsFactory {
    /**
     * Generated a quiz.
     *
     * @param title A title of a quiz
     * @return Quiz object
     */
    Quiz generateQuiz(String title);

    /**
     * Generates a question.
     *
     * @param question A question
     * @return Question object
     */
    Question generateQuestion(String question);
}
