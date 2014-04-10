package views;

import controllers.*;
import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class SetupOrchestratorImpl implements SetupOrchestrator {
    private QuizOrchestrator quizOrchestrator;
    private String userAnswer;
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
    public void setInput(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public String getMessage() {
        if (userAnswer == null) {
            message = startMessage();
        } else if (userAnswer.equals("1")) {
            message = printQuizTitleMessage();
        } else if (message.equals(printQuizTitleMessage())) {
            createQuizTitle(userAnswer);
            message = printAddQuestionMessage();
        } else if (message.equals(printAddQuestionMessage())) {
            addQuestion(userAnswer);
            message = addAnswer(userAnswer);
        }
        return message;
    }

    @Override
    public String printQuizTitleMessage() {
        return "Please enter the title of your quiz: ";
    }

    private String printAddQuestionMessage() {
        return "Please enter a question: ";
    }

    private void createQuizTitle(String userAnswer) {
        try {
            quizOrchestrator.createQuiz(userAnswer);
        } catch (IllegalQuizException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void addQuestion(String userAnswer) {
        try {
            quizOrchestrator.addQuestion(userAnswer);
        } catch (IllegalQuizException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private String addAnswer(String userAnswer) {
        try {
            quizOrchestrator.addAnswer(userAnswer, true);
        } catch (IllegalQuestionException e) {
            System.out.println(e.getMessage());
        }
        return userAnswer;
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Scanner scanner = new Scanner(System.in);
        ServerLink serverLink = new ServerLinkImpl();
        Server server = new ServerImpl(serverLink);
        QuizOrchestrator quizOrchestrator = new QuizOrchestratorImpl(server);
        SetupOrchestrator setupOrchestrator = new SetupOrchestratorImpl(quizOrchestrator);


        String userAnswer = null;
        while (userAnswer != "EXIT") {
            setupOrchestrator.setInput(userAnswer);
            System.out.println(setupOrchestrator.getMessage());
            userAnswer = scanner.nextLine();
        }
    }
}
