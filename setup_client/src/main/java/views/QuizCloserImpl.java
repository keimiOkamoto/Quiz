package views;

import constants.SetUpMessages;

import java.util.Scanner;

public class QuizCloserImpl implements QuizCloser {

    private SetupOrchestrator setupOrchestrator;

    public QuizCloserImpl(SetupOrchestrator setupOrchestrator) {

        this.setupOrchestrator = setupOrchestrator;
    }

    @Override
    public String closeQuizWithId(String message, Scanner scanner) {
        if (message.equals(SetUpMessages.ENTER_QUIZ_ID_REQUEST)) {

            while (message.equals(SetUpMessages.ENTER_QUIZ_ID_REQUEST)) {
                String userInput = scanner.nextLine();

                message = getOptionTwo(setupOrchestrator, userInput);
            }
        }
        return message;
    }

    /*
     * Helper method fof closeQuizWithId.
     */
    private String getOptionTwo(SetupOrchestrator setupOrchestrator, String userInput) {
        String message;
        if (userInput.equals(SetUpMessages.EXIT)) System.exit(0);
        message = setupOrchestrator.getMessageForCloseQuiz(userInput);
        System.out.println(message);

        while (message.equals(SetUpMessages.QUIZ_CLOSED_SUCCESS)) {
            message = getStartMessage();
        }
        return message;
    }

    /*
    * Helper method fof closeQuizWithId.
    */
    private String getStartMessage() {
        String message;
        message = SetUpMessages.START_MESSAGE;
        System.out.println(message);
        return message;
    }

}
