package views;

import constants.ExceptionMessages;
import constants.SetUpMessages;
import exceptions.IllegalQuestionException;

import java.rmi.RemoteException;
import java.util.Scanner;

public class UserAnswerViewImpl implements UserAnswerView {


    @Override
    public String getUsersAnswer(Scanner scanner, SetupOrchestrator setupOrchestrator) {
        String message;
        String userInput = scanner.nextLine();

        if (userInput.equals(SetUpMessages.EXIT)) System.exit(0);
        message = SetUpMessages.REQUEST_QUESTION;

        message = getMessageForAnswer(setupOrchestrator, userInput, message);
        System.out.println(message);

        while (message.equals(SetUpMessages.CORRECT_OR_INCORRECT_ANSWER_REQUEST)) {
            message = getAnswerValue(scanner, setupOrchestrator, message);
        }
        return message;
    }

    /*
     * If 'message' is set to CORRECT_OR_INCORRECT
     * this method will be called.
     * Depending on useInput the 'message' will be
     * altered.
     */
    private String getAnswerValue(Scanner scanner, SetupOrchestrator setupOrchestrator, String message) {
        String userInput = scanner.nextLine();
        if (userInput.equals(SetUpMessages.EXIT)) System.exit(0);
        message = getMessageForYesOrNo(setupOrchestrator, userInput, message);
        System.out.println(message);

        return message;
    }

    /*
     * Helper method for the getAnswerValue() method.
     */
    private String getMessageForYesOrNo(SetupOrchestrator setupOrchestrator, String userInput, String message) {
        try {
            message = setupOrchestrator.getMessageForYesOrNo(userInput);
        } catch (IllegalQuestionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(ExceptionMessages.SERVER_ERROR);
        }
        return message;
    }

    /*
    * Helper method for the getUserAnswer() method.
    */
    private String getMessageForAnswer(SetupOrchestrator setupOrchestrator, String userInput, String message) {
        try {
            message = setupOrchestrator.getMessageForAnswer(userInput);
        } catch (RemoteException e) {
            System.out.println(ExceptionMessages.SERVER_ERROR);
        }
        return message;
    }
}
