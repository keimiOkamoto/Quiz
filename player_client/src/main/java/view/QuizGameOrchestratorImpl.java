package view;

import constants.ExceptionMessages;
import controllers.*;
import exceptions.IllegalGameException;
import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class QuizGameOrchestratorImpl implements QuizGameOrchestrator {

    private static QuizPlayerOrchestrator quizPlayerOrchestrator;
    private static QuizGameOrchestrator quizGameOrchestrator;
    private static String message;
    private static QuizMenu quizMenu;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Views views = new ViewsImpl();

        ServerLink serverLink = new ServerLinkImpl();
        Server server = new ServerImpl(serverLink);
        quizPlayerOrchestrator = new QuizPlayerOrchestratorImpl(server);
        quizGameOrchestrator = new QuizGameOrchestratorImpl();

        String userInput = null;

        while(userInput == null || !userInput.equals("EXIT")) {

            List<Quiz> quizList = null;
            try {
                quizList = quizPlayerOrchestrator.getQuizzes();
            } catch (IllegalGameException e) {
                System.out.println(e.getMessage());
            }

            quizMenu = new QuizMenuImpl(quizList);
            message = quizMenu.getQuizNumberMessage();

            System.out.println(message);
            try {
                quizMenu.printListOfQuizzes();
            } catch (RemoteException e) {
                System.out.println(ExceptionMessages.SERVER_ERROR);
            }

            if (message.equals(quizMenu.getQuizNumberMessage())) {
                userInput = scanner.nextLine();
                message = quizGameOrchestrator.checkForValidNumber(userInput);
            }
        }
    }

    @Override
    public String checkForValidNumber(String userInput) {
        int index = 0;
        try {
            index = Integer.parseInt(userInput);
            quizGameOrchestrator.setQuizNumber(index);
            message = quizGameOrchestrator.getValidNumberMessage();

        } catch (NumberFormatException e) {
            System.out.println(ExceptionMessages.NO_NUMBER_ENTERED);
            message = quizMenu.getQuizNumberMessage();
        }
        return message;
    }

    @Override
    public String getValidNumberMessage() {
        return "Number valid.";
    }

    public String getQuiz() {

        return null;
    }
}
