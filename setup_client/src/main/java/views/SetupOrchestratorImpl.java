package views;

import controllers.*;
import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class SetupOrchestratorImpl implements SetupOrchestrator {
    private final UserInput userInput;
    private String userAnswer;

    public SetupOrchestratorImpl(UserInput userInput) {
        this.userInput = userInput;
    }

    @Override
    public String startMessage() {
       return "❤ ☆ ★ ☆ ★ Welcome to the Quiz Game Setup! ★ ☆ ★ ☆ ❤\nWhat would you like to do?\n1.Create a quiz.          2.Close a quiz.      EXIT:To exit the program.";
    }

    @Override
    public void selectOption() throws IllegalOptionException, IllegalQuestionException, IllegalQuizException {
        System.out.println("Enter '1' to create a quiz or '2' to close a quiz and 'EXIT' to terminate the program at any point.");

        String message = userInput.type();
//        boolean createTitle = userInput.selectOption(message);
//
//        if (createTitle) {
//            createQuizTitle();
//        } else {
//            closeAQuizOption();
//        }
    }

    @Override
    public void createQuizTitle() throws IllegalQuizException, IllegalQuestionException, IllegalOptionException {
        System.out.println("Please enter the title of your quiz: ");

        String title = userInput.type();
        userInput.enterTitle(title);
    }

    @Override
    public void getInput(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public String getMessage() {
        String message = null;
        if (userAnswer == null) {
            message = startMessage();
        } else if (userAnswer == "y") {

        } else {
            message = "";
        }
        return message;
    }


    public static void main(String[] args) throws RemoteException, NotBoundException {
        String userAnswer = null;
        Scanner scanner = new Scanner(System.in);
        ServerLink serverLink = new ServerLinkImpl();
        Server server = new ServerImpl(serverLink);
        QuizOrchestrator quizOrchestrator = new QuizOrchestratorImpl(server);
        UserInput userInput1 = new UserInputImpl(quizOrchestrator);
        SetupOrchestrator setupOrchestrator = new SetupOrchestratorImpl(userInput1);

        while (userAnswer != "Exit") {
            setupOrchestrator.getInput(userAnswer);
            System.out.printf(setupOrchestrator.getMessage());
            userAnswer = scanner.nextLine();
        }
    }
}
