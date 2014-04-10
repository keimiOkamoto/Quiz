package views;

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
            createQuizTitle(userInput);
            message = printAddQuestionMessage();

        }
        return message;
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
    public String getMessageForQuestion(String userInput) throws RemoteException, IllegalQuizException {
        if (message.equals(printAddQuestionMessage())) {
            addQuestion(userInput);
            message = printAddAnswerMessage();
        }
        return message;
    }

    @Override
    public String getMessageForAnswer(String userInput) throws RemoteException {
        if(message.equals(printAddAnswerMessage())) {
            addAnswer(this.userInput);
        }
        return message;
    }

    /*
    * Quiz orchestrator
    */
    @Override
    public void createQuizTitle(String userAnswer) {
        try {
            int id = quizOrchestrator.createQuiz(userAnswer);
            System.out.println("ID: " +  id);

        } catch (IllegalQuizException | RemoteException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addQuestion(String userInput) throws RemoteException{
        System.out.println("userinput in addQuestion is " + userInput);
        try {
            quizOrchestrator.addQuestion(userInput);
        } catch (IllegalQuizException e) {
            System.out.println(e.getMessage());
        }
    }

    private String addAnswer(String userAnswer) throws RemoteException {
        try {
            quizOrchestrator.addAnswer(userAnswer, true);

        } catch (IllegalQuestionException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return userAnswer;
    }

    /*
     * MAIN
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {
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
                userInput = scanner.nextLine().trim();
            }

            while (message.equals(setupOrchestrator.printAddQuestionMessage())) {
                userInput = scanner.nextLine().trim();
                try {
                    message = setupOrchestrator.getMessageForQuestion(userInput);
                } catch (IllegalQuizException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(message);

                while (message.equals(setupOrchestrator.printAddAnswerMessage())){
                    userInput = scanner.nextLine().trim();
                    setupOrchestrator.getMessageForAnswer(userInput);
                    message = setupOrchestrator.printAddQuestionMessage();
                    System.out.println(message);
                }
            }
        }
        System.exit(0);
    }
}
