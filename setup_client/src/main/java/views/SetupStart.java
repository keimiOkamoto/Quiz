package views;

import com.google.inject.Guice;
import com.google.inject.Injector;
import constants.ExceptionMessages;
import controllers.*;
import modules.SetupOrchestratorModule;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class SetupStart {
    /*
     * Event loop for setting up the quiz.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Views views = new ViewsImpl();

        GameSetupView gameSetupView = views.getGameSetupView();

        Injector injector = Guice.createInjector(new SetupOrchestratorModule());
        SetupOrchestrator setupOrchestrator = injector.getInstance(SetupOrchestratorImpl.class);

        String message = "";

        gameSetupView.setupGameStart(scanner, setupOrchestrator, null, message, views);
        System.exit(0);
    }
}
