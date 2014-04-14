package views;

import constants.ExceptionMessages;
import constants.SetUpMessages;

import java.rmi.RemoteException;
import java.util.Scanner;

public class QuestionViewImpl implements QuestionView {

    @Override
    public String getUserQuestionMessage(Scanner scanner, SetupOrchestrator setupOrchestrator, String message, Views views) {
        String userInput = scanner.nextLine();

        UserAnswerView userAnswerView = views.getUserAnswerView();
        SaveQuizView saveQuizView = views.getSaveQuizView();

        if (userInput.equals(SetUpMessages.EXIT)) System.exit(0);
        message = getMessageForQuestion(setupOrchestrator, userInput, message);
        System.out.println(message);

        while (message.equals(SetUpMessages.REQUEST_ANSWER)) {
            message = userAnswerView.getUsersAnswer(scanner, setupOrchestrator);
        }

        while (message.equals(SetUpMessages.SAVE_OR_ADD_MORE_QUESTIONS)) {
            message = saveQuizView.getUserSaveMessage(scanner, setupOrchestrator);
        }
        return message;
    }

    /*
     * Helper method for getUserQuestionMethod()
     */
    private String getMessageForQuestion(SetupOrchestrator setupOrchestrator, String userInput, String message) {
        try {
            message = setupOrchestrator.getMessageForQuestion(userInput);
        } catch (RemoteException e) {
            System.out.println(ExceptionMessages.SERVER_ERROR);
        }
        return message;
    }
}
