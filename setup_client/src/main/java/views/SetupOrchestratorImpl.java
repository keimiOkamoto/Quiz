package views;

import constants.ExceptionMessages;
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
    public String printStartMessage() {
        return "\t\t\t❤ ☆ ★ ☆ ★ Welcome to the Quiz Game Setup! ★ ☆ ★ ☆ ❤\n\nWhat would you like to do?\n1.Create a quiz.          2.Close a quiz.      EXIT:To exit the program.\nEnter '1' to create a quiz or '2' to close a quiz and 'EXIT' to terminate the program at any point.";
    }

    @Override
    public String printQuizTitleMessage() {
        return "Please enter the title of your quiz: ";
    }

    @Override
    public String printAddQuestionMessage() {
        return "Please enter a question: ";
    }

    @Override
    public String printAddAnswerMessage() {
        return "Please enter an Answer: ";
    }

    @Override
    public void setInput(String userAnswer) {
        this.userInput = userAnswer;
    }

    @Override
    public String printCorrectQuestionMessage() {
        return "Is this answer the correct one? Press 'Y' for yes and 'N' for no.";

    }

    /*
     * Gets the messages
     */
    @Override
    public String getMessageForQuizTitle() throws RemoteException {
        if (userInput == null) {
            message = printStartMessage();

        } else if (userInput.equals("1")) {
            message = printQuizTitleMessage();

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
        } else {
            message = printCorrectQuestionMessage();
        }
        return message;
    }

    @Override
    public String getMessageForYesOrNo(String yesOrNo) throws RemoteException, IllegalQuestionException {
        if (yesOrNo == null || yesOrNo.trim().isEmpty()) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            message = printCorrectQuestionMessage();
        } else {
            boolean value = correct(yesOrNo);
            addAnswer(value);
            message = printAddQuestionMessage();
        }
        return message;
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
        System.out.println("ID: " + id);
    }

    @Override
    public void addQuestion(String userInput) throws
            RemoteException, IllegalQuizException, IllegalArgumentException, IllegalQuestionException {
        quizOrchestrator.addQuestion(userInput);
    }

    private void addAnswer(boolean yesOrNo) throws RemoteException, IllegalQuestionException, IllegalArgumentException {
        quizOrchestrator.addAnswer(getAnswer(), yesOrNo);
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
     * MAIN
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, IllegalQuizException {
        Scanner scanner = new Scanner(System.in);
        ServerLink serverLink = new ServerLinkImpl();
        Server server = new ServerImpl(serverLink);
        QuizOrchestrator quizOrchestrator = new QuizOrchestratorImpl(server);
        SetupOrchestrator setupOrchestrator = new SetupOrchestratorImpl(quizOrchestrator);

        String userInput = null;
        String message;

        while (userInput == null || !userInput.equals("EXIT")) {
            setupOrchestrator.setInput(userInput);
            message = setupOrchestrator.getMessageForQuizTitle();
            System.out.println(message);

            if (!message.equals(setupOrchestrator.printAddQuestionMessage())) {
                userInput = scanner.nextLine();
            }

            while (message.equals(setupOrchestrator.printAddQuestionMessage())) {
                userInput = scanner.nextLine();
                message = setupOrchestrator.getMessageForQuestion(userInput);
                System.out.println(message);


                while (message.equals(setupOrchestrator.printAddAnswerMessage())) {
                    userInput = scanner.nextLine();
                    setupOrchestrator.setAnswer(userInput);
                    message = setupOrchestrator.getMessageForAnswer(userInput);
                    System.out.println(message);
                }

                while (message.equals(setupOrchestrator.printCorrectQuestionMessage())) {
                    userInput = scanner.nextLine();
                    try {
                        message = setupOrchestrator.getMessageForYesOrNo(userInput);
                        quizOrchestrator.save(quizOrchestrator.getQuiz());
                    } catch (IllegalQuestionException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(message);
                }
            }
        }
        System.exit(0);
    }
}
