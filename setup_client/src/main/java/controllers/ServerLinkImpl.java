package controllers;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerLinkImpl implements ServerLink {

    private Registry registry;

    public ServerLinkImpl() {
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
             quizServer = (QuizServer) registry.lookup("QuizServer");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return quizServer;
    }
}
