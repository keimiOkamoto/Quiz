package view;

import models.Quiz;

public interface QuizGameOrchestrator {

    int getQuizIndex();

    /**
     * Gets the quiz and allows the player to play the quiz.
     *
     * @return the message that will lead to the next event.
     */
    Quiz getQuiz();

    String checkForValidNumber(String userInput);

    String getValidNumberMessage();

    void setQuizNumber(int numberIndex);
}
