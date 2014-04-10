package views;

import controllers.*;

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
            message = createQuizTitleMessage();
        } else if (message.equals(createQuizTitleMessage())) {
            createQuizTitle(userAnswer);
            message = addQuestionMessage();
        } else if (message.equals(addQuestionMessage())) {
            createQuestion(userAnswer);
            message = addAnswer(userAnswer);
        }
        return message;
    }

    @Override
    public String createQuizTitleMessage() {
        return "Please enter the title of your quiz: ";
    }

    private void createQuizTitle(String userAnswer) {

    }

    private String addAnswer(String userAnswer) {
        return null;
    }

    private void createQuestion(String userAnswer) {

    }

    private String addQuestionMessage() {
        return null;
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
