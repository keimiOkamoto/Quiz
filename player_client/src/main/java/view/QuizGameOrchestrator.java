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

    String getMenuMessage();

    void setQuizNumber(int numberIndex);

    String checkForHighScore(Player player, Quiz quiz, Server server);

    String checkIfClosedQuizIsNull();

    String getStartChoice(String userInput);

    String getClosedQuizMessage();

    String getNameMessage();

    String getCountryMessage();

    String getAgeMessage();

    String getValidClosedQuizMessage();

    String selectClosedQuiz(Scanner scanner);
}
