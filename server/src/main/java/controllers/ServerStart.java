package controllers;
/**
 * This class starts the server.
 */
import com.google.inject.Guice;
import com.google.inject.Injector;
import factories.QuizServerFactory;
import factories.QuizServerFactoryImpl;
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
        QuizServerFactory quizServerFactory = injector.getInstance(QuizServerFactoryImpl.class);

        System.setProperty("java.security.policy", "server/security.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("QuizServer", quizServerFactory);

            System.out.println("Server is ready.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("Press enter to shutdown the server.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.exit(0);
    }
}
