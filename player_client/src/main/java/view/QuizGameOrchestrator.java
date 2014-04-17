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

    String getUserAnswerMessage();

    void setQuizNumber(int numberIndex);

    String play(Quiz quiz);

    void checkForValidInputForAnswer(String userInput);
}
