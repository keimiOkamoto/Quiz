package view;

import models.Quiz;

import java.util.Scanner;

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

    String play(Quiz quiz, Scanner scanner);

    void checkForValidInputForAnswer(String userInput);
}
