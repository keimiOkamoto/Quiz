package views;

import java.util.Scanner;

public class SaveQuizViewImpl implements SaveQuizView {

    /*
 * This method takes in the user input for their
 * decision to save the quiz or carry on adding
 * questions. Returns the next message depending
 * on the user input.
 */
    @Override
    public String getUserSaveMessage(Scanner scanner, SetupOrchestrator setupOrchestrator) {
        String message;
        String userInput = scanner.nextLine();
        message = setupOrchestrator.getMessageForSave(userInput);
        System.out.println(message);

        return message;
    }
}
