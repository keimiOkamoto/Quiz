package views;

import constants.ExceptionMessages;
import constants.SetUpMessages;
import controllers.*;
import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class SetupOrchestratorImpl implements SetupOrchestrator {
    private QuizOrchestrator quizOrchestrator;
    private String userInput;
    private String message = null;
    private String answer;

    public SetupOrchestratorImpl(QuizOrchestrator quizOrchestrator) {
        this.quizOrchestrator = quizOrchestrator;
    }


    @Override
    public String printQuizTitleMessage() {
        return "♡ Please enter the title of your quiz. ♡";
    }

    @Override
    public String printAddQuestionMessage() {
        return "♡ Please enter a question for your quiz. ♡";
    }

    @Override
    public String printAddAnswerMessage() {
        return "♡ Please enter a possible answer, you can have multiple correct answers, when you are done just type 'DONE'in capital letters! ♡ ";
    }

    @Override
    public String printCorrectQuestionMessage() {
        return "♡ Is this answer correct? Press 'Y' for yes and 'N' for no. ♡";
    }

    @Override
    public String printSaveSuccess() {
        return "Your quiz has been saved!";
    }

    @Override
    public String printSaveOption() {
        return "♡ Would you like to add more question or save the quiz? Press 'Y' to add more and 'SAVE' to save. ♡";
    }

    @Override
    public String printOptionTwoMessage() {
        return "♡ Enter the ID of the Quiz you would like to close! ♡";
    }

    @Override
    public String printCloseSuccessMessage() {
        return "♡ Quiz has been closed. ♡";
    }

    @Override
    public void setInput(String userAnswer) {
        this.userInput = userAnswer;
    }

    /*
     * Gets the messages
     */
    @Override
    public String getMessageForQuizTitle() throws RemoteException {
        if (userInput == null){
            message = SetUpMessages.START_MESSAGE;

        } else if (userInput.trim().isEmpty()) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT + "\n");

        } else if (userInput.equals("1")) {
            message = printQuizTitleMessage();

        } else if (userInput.equals("2")) {
            message = printOptionTwoMessage();

        } else if (message.equals(printQuizTitleMessage())) {
            try {
                createQuizTitle(userInput);
                message = printAddQuestionMessage();
            } catch (IllegalQuizException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return message;
    }

    @Override
    public String getMessageForQuestion(String userInput) throws RemoteException {
        try {
            addQuestion(userInput);
            message = printAddAnswerMessage();
        } catch (IllegalArgumentException | IllegalQuestionException | IllegalQuizException e) {
            System.out.println(e.getMessage());
        }
        return message;
    }

    @Override
    public String getMessageForAnswer(String userInput) throws RemoteException, IllegalArgumentException {
        if (userInput == null || userInput.trim().isEmpty()) {
            message = printAddAnswerMessage();
            System.out.println(ExceptionMessages.EMPTY_ANSWER);
        } else if (userInput.equals("DONE")) {
            message = printSaveOption();
        } else {
            setAnswer(userInput);
            message = printCorrectQuestionMessage();
        }
        return message;
    }

    @Override
    public String getMessageForYesOrNo(String yesOrNo) throws RemoteException, IllegalQuestionException {
        if (yesOrNo.equals("Y") || yesOrNo.equals("N")) {
            boolean value = correct(yesOrNo);
            addAnswer(value);
            message = printAddAnswerMessage();
        } else {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            message = printCorrectQuestionMessage();
        }
        return message;
    }

    @Override
    public String getMessageForSave(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            message = printSaveOption();
        } else if (userInput.equals("SAVE")) {
            save();
            message = printSaveSuccess();
        } else if (userInput.equals(("Y"))) {
            message = printAddQuestionMessage();
        }
        return message;
    }

    @Override
    public String getMessageForCloseQuiz(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            message = printOptionTwoMessage();
        } else {
            try {
                close(userInput);
                message = printCloseSuccessMessage();

            } catch (RemoteException e) {
                System.out.println(ExceptionMessages.SERVER_ERROR);
            } catch (IllegalQuizException e) {
                System.out.println(e.getMessage());
                message = printOptionTwoMessage();
            }
        }
        return message;
    }

    private void close(String userInput) throws RemoteException, IllegalQuizException {
        int id = Integer.parseInt(userInput);
        quizOrchestrator.closeQuiz(id);
    }

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    /*
    * Quiz orchestrator
    */
    @Override
    public void createQuizTitle(String userAnswer) throws
            RemoteException, IllegalQuizException, IllegalArgumentException {
        int id = quizOrchestrator.createQuiz(userAnswer);
        System.out.println("Your quiz ID is: " + id);
    }

    @Override
    public void addQuestion(String userInput) throws
            RemoteException, IllegalQuizException, IllegalArgumentException, IllegalQuestionException {
        quizOrchestrator.addQuestion(userInput);
    }

    private void addAnswer(boolean yesOrNo) throws RemoteException, IllegalQuestionException, IllegalArgumentException {
        quizOrchestrator.addAnswer(getAnswer(), yesOrNo);
    }

    private void save() {
        try {
            quizOrchestrator.save(quizOrchestrator.getQuiz());
        } catch (IllegalQuizException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(ExceptionMessages.SERVER_ERROR);
        }
    }

    @Override
    public boolean correct(String userInput) {
        boolean value = false;
        if (userInput.trim().equals("Y")) {
            value = true;
        } else if (userInput.trim().equals("N")) {
            value = false;
        }
        return value;
    }

    /*
     * Event loop for setting up the quiz.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServerLink serverLink = new ServerLinkImpl();
        Server server = null;
        try {
            server = new ServerImpl(serverLink);
        } catch (RemoteException | NotBoundException e) {
            System.out.println(ExceptionMessages.SERVER_ERROR);
        }
        QuizOrchestrator quizOrchestrator = new QuizOrchestratorImpl(server);
        SetupOrchestrator setupOrchestrator = new SetupOrchestratorImpl(quizOrchestrator);

        String userInput = null;
        String message = "";

        while (userInput == null || !userInput.equals("EXIT")) {
            setupOrchestrator.setInput(userInput);
            message = getMessageForQuizTitle(setupOrchestrator, message);
            System.out.println(message);

            if (message.equals(setupOrchestrator.printOptionTwoMessage())) {

                while (message.equals(setupOrchestrator.printOptionTwoMessage())) {
                    userInput = scanner.nextLine();
                    if (userInput.equals("EXIT")) System.exit(0);
                    message = setupOrchestrator.getMessageForCloseQuiz(userInput);
                    System.out.println(message);

                    while (message.equals(setupOrchestrator.printCloseSuccessMessage())) {
                        message = SetUpMessages.START_MESSAGE;
                        System.out.println(message);
                    }
                }
            }

            if (!message.equals(setupOrchestrator.printAddQuestionMessage())) {
                userInput = scanner.nextLine();
            }

            while (message.equals(setupOrchestrator.printAddQuestionMessage())) {
                userInput = scanner.nextLine();
                if (userInput.equals("EXIT")) System.exit(0);
                message = getMessageForQuestion(setupOrchestrator, userInput, message);
                System.out.println(message);

                while (message.equals(setupOrchestrator.printAddAnswerMessage())) {
                    userInput = scanner.nextLine();
                    if (userInput.equals("EXIT")) System.exit(0);
                    message = setupOrchestrator.printAddQuestionMessage();

                    message = getMessageForAnswer(setupOrchestrator, userInput, message);
                    System.out.println(message);

                    while (message.equals(setupOrchestrator.printCorrectQuestionMessage())) {
                        userInput = scanner.nextLine();
                        if (userInput.equals("EXIT")) System.exit(0);
                        message = getMessageForYesOrNo(setupOrchestrator, userInput, message);
                        System.out.println(message);
                    }
                }

                while (message.equals(setupOrchestrator.printSaveOption())) {
                    userInput = scanner.nextLine();
                    message = setupOrchestrator.getMessageForSave(userInput);
                    System.out.println(message);
                }

                while (message.equals(setupOrchestrator.printSaveSuccess())) {
                    message = SetUpMessages.START_MESSAGE;
                    System.out.println(message);
                }
            }
        }
        System.exit(0);
    }

    private static String getMessageForYesOrNo(SetupOrchestrator setupOrchestrator, String userInput, String message) {
        try {
            message = setupOrchestrator.getMessageForYesOrNo(userInput);
        } catch (IllegalQuestionException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            System.out.println(ExceptionMessages.SERVER_ERROR);
        }
        return message;
    }

    private static String getMessageForAnswer(SetupOrchestrator setupOrchestrator, String userInput, String message) {
        try {
            message = setupOrchestrator.getMessageForAnswer(userInput);
        } catch (RemoteException e) {
            System.out.println(ExceptionMessages.SERVER_ERROR);
        }
        return message;
    }

    private static String getMessageForQuestion(SetupOrchestrator setupOrchestrator, String userInput, String message) {
        try {
            message = setupOrchestrator.getMessageForQuestion(userInput);
        } catch (RemoteException e) {
            System.out.println(ExceptionMessages.SERVER_ERROR);
        }
        return message;
    }

    private static String getMessageForQuizTitle(SetupOrchestrator setupOrchestrator, String message) {
        try {
            message = setupOrchestrator.getMessageForQuizTitle();
        } catch (RemoteException e) {
            System.out.println(ExceptionMessages.SERVER_ERROR);
        }
        return message;
    }
}
