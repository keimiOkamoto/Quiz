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

    public SetupOrchestratorImpl(QuizOrchestrator quizOrchestrator) {
        this.quizOrchestrator = quizOrchestrator;
    }

    @Override
    public String startMessage() {
       return "\t\t\t❤ ☆ ★ ☆ ★ Welcome to the Quiz Game Setup! ★ ☆ ★ ☆ ❤\n\nWhat would you like to do?\n1.Create a quiz.          2.Close a quiz.      EXIT:To exit the program.";
    }

    @Override
    public void selectOption() {
        System.out.println("Enter '1' to create a quiz or '2' to close a quiz and 'EXIT' to terminate the program at any point.");
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

    /*
     * Gets the messages
     */
    @Override
    public String getMessageForQuizTitle() throws RemoteException {
        if (userInput == null) {
            message = startMessage();

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
    public String getMessageForAnswer(String userInput, boolean value) throws RemoteException {
        try {
            addAnswer(userInput, value);
            message = printAddQuestionMessage();
        } catch (IllegalQuestionException | IllegalArgumentException e ) {
            System.out.println(e.getMessage());
        }
        return message;
    }


    /*
    * Quiz orchestrator
    */
    @Override
    public void createQuizTitle(String userAnswer) throws RemoteException, IllegalQuizException, IllegalArgumentException{
            int id = quizOrchestrator.createQuiz(userAnswer);
            System.out.println("ID: " +  id);
    }

    @Override
    public void addQuestion(String userInput) throws RemoteException, IllegalQuizException, IllegalArgumentException, IllegalQuestionException {
            quizOrchestrator.addQuestion(userInput);
    }

    private String addAnswer(String userAnswer, boolean value) throws RemoteException, IllegalQuestionException {
            quizOrchestrator.addAnswer(userAnswer, value);
        return userAnswer;
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

                while (message.equals(setupOrchestrator.printAddAnswerMessage())){
                    userInput = scanner.nextLine();
                    String answerStr = userInput;

                    boolean value;
                    System.out.println("Is this answer the correct one? Press 'Y' for yes and 'N' for no.");
                    userInput = scanner.nextLine();

                    if (userInput.trim().equals("Y")) {
                        value = true;
                    } else if (userInput.trim().equals("N")) {
                        value = false;
                    } else {
                        throw new IllegalArgumentException(ExceptionMessages.INVALID_USER_INPUT)
                    }

                    setupOrchestrator.getMessageForAnswer(userInput, value);
                    message = setupOrchestrator.printAddQuestionMessage();
                    System.out.println(message);
                }
            }
        }
        System.exit(0);
    }
}
