package views;

import java.util.Scanner;

/**
 * This is the interactive view for the user
 * when entering an Answer.
 *
 */
public interface UserAnswerView {

    /**
     * When 'message' is set to REQUEST_ANSWER, this method
     * will be called. Depending on the answer the
     * message will change.
     *
     * @param scanner A scanner for the user input.
     * @param setupOrchestrator A setup orchestrator object.
     * @return A message
     */
    String getUsersAnswer(Scanner scanner, SetupOrchestrator setupOrchestrator);
}
