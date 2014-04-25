package view;

import controllers.QuizPlayerOrchestrator;
import models.Player;

import java.util.Scanner;

/**
 * Player creator that takes input from a user and
 * creates a player with the entered details.
 */
public interface PlayerCreator {
    /**
     * Creates a player
     * @param player A player with no details.
     * @param scanner A scanner for users to input
     * @param quizPlayerOrchestrator A quizPlayerOrchestrator.
     * @return A player with the users details.
     */
    Player createPlayer(Player player, Scanner scanner, QuizPlayerOrchestrator quizPlayerOrchestrator);
}
