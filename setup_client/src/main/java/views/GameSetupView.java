package views;

import java.util.Scanner;

public interface GameSetupView {

    /**
     *The start of setting up the quiz.
     *
     * @param scanner A scanner for the user input
     * @param setupOrchestrator A setup orchestrator
     * @param userInput A user's menu choice.
     * @param message A message that determines the current stage of the setup process.
     * @param views A views object.
     */
    void setupGameStart(Scanner scanner, SetupOrchestrator setupOrchestrator, String userInput, String message, Views views);
}
