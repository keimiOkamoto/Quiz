package views;

import constants.ExceptionMessages;
import constants.SetUpMessages;

import java.rmi.RemoteException;
import java.util.Scanner;

public class GameSetupViewImpl implements GameSetupView {

    @Override
    public void setupGameStart(Scanner scanner, SetupOrchestrator setupOrchestrator, String userInput, String message, Views views) {
        while (userInput == null || !userInput.equals(SetUpMessages.EXIT)) {
            setupOrchestrator.setInput(userInput);
            message = getMessageForQuizTitle(setupOrchestrator, message);
            System.out.println(message);

            QuizCloser quizCloser = views.getQuizCloser(setupOrchestrator);
            QuestionView questionView = views.getQuestionView();
            /*
             * Option 2: Closing quiz with Id
             */
            message = quizCloser.closeQuizWithId(message, scanner);

            if (!message.equals(SetUpMessages.REQUEST_QUESTION)) {
                userInput = scanner.nextLine();
            }

            while (message.equals(SetUpMessages.REQUEST_QUESTION)) {
                message = questionView.getUserQuestionMessage(scanner, setupOrchestrator, message, views);

                if (message.equals(SetUpMessages.SAVE_SUCCESS)) {
                    message = SetUpMessages.START_MESSAGE;
                    userInput = null;
                }
            }
        }
    }

    /*
     * Helper method for setupGameStart()
     */
    private String getMessageForQuizTitle(SetupOrchestrator setupOrchestrator, String message) {
        try {
            message = setupOrchestrator.getMessageForQuizTitle();
        } catch (RemoteException e) {
            System.out.println(ExceptionMessages.SERVER_ERROR);
        }
        return message;
    } //TODO
}
