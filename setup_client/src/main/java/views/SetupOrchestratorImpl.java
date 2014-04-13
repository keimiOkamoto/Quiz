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

        } else if (userInput.equals(SetUpMessages.ONE)) {
            message = SetUpMessages.ENTER_QUIZ_TITLE;

        } else if (userInput.equals(SetUpMessages.TWO)) {
            message = SetUpMessages.ENTER_QUIZ_ID_REQUEST;

        } else if (message.equals(SetUpMessages.ENTER_QUIZ_TITLE)) {
            try {
                createQuizTitle(userInput);
                message = SetUpMessages.REQUEST_QUESTION;
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
            message = SetUpMessages.REQUEST_ANSWER;
        } catch (IllegalArgumentException | IllegalQuestionException | IllegalQuizException e) {
            System.out.println(e.getMessage());
        }
        return message;
    }

    @Override
    public String getMessageForAnswer(String userInput) throws RemoteException, IllegalArgumentException {
        if (userInput == null || userInput.trim().isEmpty()) {
            message = SetUpMessages.REQUEST_ANSWER;
            System.out.println(ExceptionMessages.EMPTY_ANSWER);
        } else if (userInput.equals(SetUpMessages.DONE)) {
            message = SetUpMessages.SAVE_OR_ADD_MORE_QUESTIONS;
        } else {
            setAnswer(userInput);
            message = SetUpMessages.CORRECT_OR_INCORRECT_ANSWER_REQUEST;
        }
        return message;
    }

    @Override
    public String getMessageForYesOrNo(String yesOrNo) throws RemoteException, IllegalQuestionException {
        if (yesOrNo.equals(SetUpMessages.YES) || yesOrNo.equals(SetUpMessages.NO)) {
            boolean value = correct(yesOrNo);
            addAnswer(value);
            message = SetUpMessages.REQUEST_ANSWER;
        } else {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            message = SetUpMessages.CORRECT_OR_INCORRECT_ANSWER_REQUEST;
        }
        return message;
    }

    @Override
    public String getMessageForSave(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            message = SetUpMessages.SAVE_OR_ADD_MORE_QUESTIONS;
        } else if (userInput.equals(SetUpMessages.SAVE)) {
            save();
            message = SetUpMessages.SAVE_SUCCESS;
        } else if (userInput.equals((SetUpMessages.YES))) {
            message = SetUpMessages.REQUEST_QUESTION;
        }
        return message;
    }

    @Override
    public String getMessageForCloseQuiz(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            message = SetUpMessages.ENTER_QUIZ_ID_REQUEST;
        } else {
            try {
                close(userInput);
                message = SetUpMessages.QUIZ_CLOSED_SUCCESS;

            } catch (RemoteException e) {
                System.out.println(ExceptionMessages.SERVER_ERROR);
            } catch (IllegalQuizException e) {
                System.out.println(e.getMessage());
                message = SetUpMessages.START_MESSAGE;
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
        System.out.println(SetUpMessages.YOUR_QUIZ_ID + id);
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
        if (userInput.trim().equals(SetUpMessages.YES)) {
            value = true;
        } else if (userInput.trim().equals(SetUpMessages.NO)) {
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

        while (userInput == null || !userInput.equals(SetUpMessages.EXIT)) {
            setupOrchestrator.setInput(userInput);
            message = getMessageForQuizTitle(setupOrchestrator, message);
            System.out.println(message);

            if (message.equals(SetUpMessages.ENTER_QUIZ_ID_REQUEST)) {

                while (message.equals(SetUpMessages.ENTER_QUIZ_ID_REQUEST)) {
                    userInput = scanner.nextLine();
                    if (userInput.equals(SetUpMessages.EXIT)) System.exit(0);
                    message = setupOrchestrator.getMessageForCloseQuiz(userInput);
                    System.out.println(message);

                    while (message.equals(SetUpMessages.QUIZ_CLOSED_SUCCESS)) {
                        message = SetUpMessages.START_MESSAGE;
                        System.out.println(message);
                    }
                }
            }

            if (!message.equals(SetUpMessages.REQUEST_QUESTION)) {
                userInput = scanner.nextLine();
            }

            while (message.equals(SetUpMessages.REQUEST_QUESTION)) {
                userInput = scanner.nextLine();
                if (userInput.equals(SetUpMessages.EXIT)) System.exit(0);
                message = getMessageForQuestion(setupOrchestrator, userInput, message);
                System.out.println(message);

                while (message.equals(SetUpMessages.REQUEST_ANSWER)) {
                    userInput = scanner.nextLine();
                    if (userInput.equals(SetUpMessages.EXIT)) System.exit(0);
                    message = SetUpMessages.REQUEST_QUESTION;

                    message = getMessageForAnswer(setupOrchestrator, userInput, message);
                    System.out.println(message);

                    while (message.equals(SetUpMessages.CORRECT_OR_INCORRECT_ANSWER_REQUEST)) {
                        userInput = scanner.nextLine();
                        if (userInput.equals(SetUpMessages.EXIT)) System.exit(0);
                        message = getMessageForYesOrNo(setupOrchestrator, userInput, message);
                        System.out.println(message);
                    }
                }

                while (message.equals(SetUpMessages.SAVE_OR_ADD_MORE_QUESTIONS)) {
                    userInput = scanner.nextLine();
                    message = setupOrchestrator.getMessageForSave(userInput);
                    System.out.println(message);
                }

                while (message.equals(SetUpMessages.SAVE_SUCCESS)) {
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
