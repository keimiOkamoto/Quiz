package controllers;

import com.google.inject.Singleton;
import models.Quiz;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class ClosedQuizContainerImpl extends UnicastRemoteObject implements ClosedQuizContainer {
    private Map<Integer, Quiz> closedQuizMap = new HashMap<>();

    protected ClosedQuizContainerImpl() throws RemoteException {
    }

    @Override
    public void add(Quiz closedQuiz) throws RemoteException {
        closedQuizMap.put(closedQuiz.getId(), closedQuiz);
    }

    @Override
    public Quiz getQuiz(int id) throws RemoteException {
        return closedQuizMap.get(id);
    }
}
