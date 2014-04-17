package view;

import controllers.Server;
import models.Player;
import models.Quiz;

import java.rmi.RemoteException;
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

    String getUserHighScoreMessage();

    String getNewWinnerMessage(Player player) throws RemoteException;

    String getThanksForPlayingMessage();

    void setQuizNumber(int numberIndex);

    String play(Quiz quiz, Scanner scanner, Player player);

    void checkForHighScore(Player player, Quiz quiz, Server server);

    String getStartMessage();

}
