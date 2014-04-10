package controllers;

import com.google.inject.Singleton;
import models.Quiz;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class ClosedQuizContainerImpl implements ClosedQuizContainer {
    private Map<Integer, Quiz> closedQuizMap = new HashMap<>();

    @Override
    public void add(Quiz closedQuiz) throws RemoteException {
        closedQuizMap.put(closedQuiz.getId(), closedQuiz);
    }

    @Override
    public Quiz getQuiz(int id) {
        return closedQuizMap.get(id);
    }
}
