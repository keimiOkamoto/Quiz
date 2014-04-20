package views;

import constants.ExceptionMessages;
import controllers.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class SetupStart {
    /*
     * Event loop for setting up the quiz.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServerLink serverLink = new ServerLinkImpl();
        Views views = new ViewsImpl();
        Server server = null;

        try {
            server = new ServerImpl(serverLink);
        } catch (RemoteException | NotBoundException e) {
            System.out.println(ExceptionMessages.SERVER_ERROR);
        }
        QuizOrchestrator quizOrchestrator = new QuizOrchestratorImpl(server);
        SetupOrchestrator setupOrchestrator = new SetupOrchestratorImpl(quizOrchestrator);
        GameSetupView gameSetupView = views.getGameSetupView();

        String message = "";

        gameSetupView.setupGameStart(scanner, setupOrchestrator, null, message, views);
        System.exit(0);
    }
}
