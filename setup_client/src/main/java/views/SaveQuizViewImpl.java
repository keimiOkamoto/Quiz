package views;

import java.util.Scanner;

public class SaveQuizViewImpl implements SaveQuizView {

    @Override
    public String getUserSaveMessage(Scanner scanner, SetupOrchestrator setupOrchestrator) {
        String message;
        String userInput = scanner.nextLine();
        message = setupOrchestrator.getMessageForSave(userInput);
        System.out.println(message);

        return message;
    }
}
