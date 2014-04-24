package controllers;


import factories.QuizServerFactory;
import models.Quiz;

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
             QuizServerFactory quizServerFactory = (QuizServerFactory) registry.lookup("QuizServer");
            quizServer = quizServerFactory.getQuizServer();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return quizServer;
    }
}
