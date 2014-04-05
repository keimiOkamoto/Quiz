package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Quiz;

import java.util.Collection;
import java.util.TreeMap;

@Singleton
public class QuizContainerImpl implements QuizContainer {
    private TreeMap<Integer, Quiz> quizTreeMap = new TreeMap<>();
    private ClosedQuizContainer closedQuizContainer;

    @Inject
    public QuizContainerImpl(ClosedQuizContainer closedQuizContainer) {
        this.closedQuizContainer = closedQuizContainer;
    }

    @Override
    public boolean contains(String title) {
        Collection<Quiz> quizCollection = quizTreeMap.values();
        boolean result = false;

        for (Quiz quiz : quizCollection) {
            if (!quiz.getTitle().equals(title)) result = true;
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
        quizTreeMap.put(quiz.getId(), quiz);
    }

    @Override
    public Quiz getQuizBy(int id) {
        return quizTreeMap.get(id);
    }
}
