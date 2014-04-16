package controllers;
/**
 * This class starts the server.
 */
import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.QuizSeverModule;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ServerStart {
    /**
     * Main method to launch the sever.
     *
     * @param args
     */
    public static void main(String[] args) throws RemoteException {
        Injector injector = Guice.createInjector(new QuizSeverModule());
        QuizServer quizServer = injector.getInstance(QuizServerImpl.class);

        System.setProperty("java.security.policy", "server/security.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("QuizServer", quizServer);

            System.out.println("Server is ready.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("Press enter to shutdown the server.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        quizServer.flush();
        System.exit(0);
    }
}
