package controllers;

import com.google.inject.Singleton;
import factories.QuizServerFactory;

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Singleton
public class ServerLinkImpl implements ServerLink {

    private Registry registry;

    public ServerLinkImpl() {
        System.setProperty("java.security.policy", "server/security.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try {
            this.registry = LocateRegistry.getRegistry("localhost", 1099);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public QuizServer getQuizServer() {
        QuizServer quizServer = null;
        try {
            QuizServerFactory quizServerFactory = (QuizServerFactory) registry.lookup("QuizServer");
            quizServer = quizServerFactory.getQuizServer();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return quizServer;
    }
}
