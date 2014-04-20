package view;

import controllers.Server;
import models.Player;
import models.Quiz;

import java.rmi.RemoteException;
import java.util.Scanner;

public interface QuizGameOrchestrator {

    /**
     * Gets the quiz and allows the player to play the quiz.
     *
     * @return the message that will lead to the next event.
     */
    Quiz getQuiz();

    Player makePlayer(Scanner scanner, Player player);

    void setQuizNumber(int numberIndex);

    String checkForHighScore(Player player, Quiz quiz, Server server);

    String checkIfClosedQuizIsNull();

    String selectClosedQuiz(Scanner scanner, String message);

    String getStartChoice(String userInput);

}
