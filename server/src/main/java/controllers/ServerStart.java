package controllers;
/**
 * This class starts the server.
 */
import com.google.inject.Guice;
import modules.QuizSeverModule;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerStart {
    /**
     * Main method to launch the sever.
     *
     * @param args
     */
    public static void main(String[] args) {
        QuizServer quizServer = Guice.createInjector(new QuizSeverModule()).getInstance(QuizServerImpl.class);

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
    }
}
