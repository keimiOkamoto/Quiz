package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Quiz;

import utils.DiskWriter;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

@Singleton
public class QuizContainerImpl implements QuizContainer {

    private TreeMap<Integer, Quiz> quizTreeMap;
    private ClosedQuizContainer closedQuizContainer;
    private DiskWriter diskWriter;

    @Inject
    public QuizContainerImpl(ClosedQuizContainer closedQuizContainer, DiskWriter diskWriter) {
        if (diskWriter.checkIfDataExists()) {
            diskWriter.readDisk();
            this.closedQuizContainer = diskWriter.getClosedQuizContainer();
            this.quizTreeMap = diskWriter.getQuizTreeMap();
        } else {
            this.closedQuizContainer = closedQuizContainer;
            this.quizTreeMap = new TreeMap<>();
        }
        this.diskWriter = diskWriter;
        addShutdownHook();
    }

    @Override
    public boolean contains(String title) {
        Collection<Quiz> quizCollection = quizTreeMap.values();
        boolean result = false;
        for (Quiz quiz : quizCollection) {
            try {
                if (quiz.getTitle().equals(title)) result = true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean contains(int id) {
        return quizTreeMap.containsKey(id);
    }

    @Override
    public void closeQuizWith(int id) {
        Quiz closedQuiz = quizTreeMap.remove(id);
        closedQuizContainer.add(closedQuiz);
    }

    @Override
    public void save(Quiz quiz) {
        try {
            quizTreeMap.put(quiz.getId(), quiz);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Quiz getQuizBy(int id) {
        return quizTreeMap.get(id);
    }

    @Override
    public List<Quiz> getQuizzes() {
        Collection<Quiz> collection = quizTreeMap.values();
        List<Quiz> list = new ArrayList<>();

        for (Quiz quiz : collection) {
            list.add(quiz);
        }
        return list;
    }

    @Override
    public void flush() {
        diskWriter.writeToDisk(closedQuizContainer, quizTreeMap);
    }

    /**
     * This method adds a shutdown hook
     */
    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                flush();
            }
        });
    }
}
