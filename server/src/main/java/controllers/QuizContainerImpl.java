package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Quiz;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

@Singleton
public class QuizContainerImpl extends UnicastRemoteObject implements QuizContainer {
    private TreeMap<Integer, Quiz> quizTreeMap = new TreeMap<>();
    private ClosedQuizContainer closedQuizContainer;

    @Inject
    public QuizContainerImpl(ClosedQuizContainer closedQuizContainer) throws RemoteException {
        this.closedQuizContainer = closedQuizContainer;
    }

    @Override
    public boolean contains(String title) throws RemoteException {
        Collection<Quiz> quizCollection = quizTreeMap.values();
        boolean result = false;
        for (Quiz quiz : quizCollection) {
            if (quiz.getTitle().equals(title)) result = true;
        }
        return result;
    }

    @Override
    public boolean contains(int id) throws RemoteException {
        return quizTreeMap.containsKey(id);
    }

    @Override
    public void closeQuizWith(int id) throws RemoteException {
        Quiz closedQuiz = quizTreeMap.remove(id);
        closedQuizContainer.add(closedQuiz);
    }

    @Override
    public void save(Quiz quiz) throws RemoteException {
        quizTreeMap.put(quiz.getId(), quiz);
    }

    @Override
    public Quiz getQuizBy(int id) throws RemoteException {
        return quizTreeMap.get(id);
    }

    @Override
    public List<Quiz> getQuizzes() throws RemoteException {
        Collection<Quiz> collection = quizTreeMap.values();
        List<Quiz> list = new ArrayList<>();

        for (Quiz quiz : collection) {
            list.add(quiz);
        }
        return list;
    }
}
