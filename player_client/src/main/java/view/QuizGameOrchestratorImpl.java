package view;

import controllers.*;
import models.PlayerFactory;

import java.util.Scanner;

public class QuizGameOrchestratorImpl implements QuizGameOrchestrator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        userInput = null;
        String message;

        ServerLink serverLink = new ServerLinkImpl();
        Server server = ServerImpl(serverLink,  );
        QuizPlayerOrchestrator quizPlayerOrchestrator = new QuizPlayerOrchestratorImpl(server);

        while(userInput == null && !userInput.equals("EXIT")) {

        }

    }
}
