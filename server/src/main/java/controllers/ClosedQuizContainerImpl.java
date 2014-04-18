package controllers;

import com.google.inject.Singleton;
import models.Player;
import models.Quiz;
import models.ScoreKeeper;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class ClosedQuizContainerImpl implements ClosedQuizContainer, Serializable {
    private static final long serialVersionUID = -9098843522797392142L;
    private Map<Integer, Quiz> closedQuizMap = new HashMap<>();

    protected ClosedQuizContainerImpl() {
    }

    @Override
    public void add(Quiz closedQuiz) {
        try {
            closedQuizMap.put(closedQuiz.getId(), closedQuiz);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Quiz> getClosedQuizList() {
        return new ArrayList<>(closedQuizMap.values());
    }


    @Override
    public Quiz getQuiz(int id) {
        return closedQuizMap.get(id);
    }
}
