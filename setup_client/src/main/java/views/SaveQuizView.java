package views;

import java.util.Scanner;

public interface SaveQuizView {

    /**
     * This method takes in the user input for their
     * decision to save the quiz or carry on adding
     * questions. Returns the next message depending
     * on the user input.
     *
     * @param scanner A scanner for the user input.
     * @param setupOrchestrator A setup orchestrator object.
     * @return A message for the next scenario.
     */
    String getUserSaveMessage(Scanner scanner, SetupOrchestrator setupOrchestrator);
}
