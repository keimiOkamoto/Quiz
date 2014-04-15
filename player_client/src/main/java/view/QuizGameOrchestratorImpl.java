package view;

import constants.ExceptionMessages;
import controllers.*;
import exceptions.IllegalGameException;
import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class QuizGameOrchestratorImpl implements QuizGameOrchestrator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Views views = new ViewsImpl();

        ServerLink serverLink = new ServerLinkImpl();
        Server server = new ServerImpl(serverLink);
        QuizPlayerOrchestrator quizPlayerOrchestrator = new QuizPlayerOrchestratorImpl(server);
        QuizGameOrchestrator quizGameOrchestrator = new QuizGameOrchestratorImpl();

        String userInput = null;
        String message;

        while(userInput == null || !userInput.equals("EXIT")) {

            List<Quiz> quizList = null;
            try {
                quizList = quizPlayerOrchestrator.getQuizzes();
            } catch (IllegalGameException e) {
                System.out.println(e.getMessage());
            }


            QuizMenu quizMenu = new QuizMenuImpl(quizList);
            message = quizMenu.getQuizNumberMessage();

            System.out.println(message);
            try {
                quizMenu.printListOfQuizzes();
            } catch (RemoteException e) {
                System.out.println(ExceptionMessages.SERVER_ERROR);
            }

            if (message.equals(quizMenu.getQuizNumberMessage())) {
                System.out.println("Yay");
                userInput = scanner.nextLine();
                message = quizGameOrchestrator.getQuiz();
            }

        }

    }

    @Override
    public String getQuiz() {
        return null;
    }
}