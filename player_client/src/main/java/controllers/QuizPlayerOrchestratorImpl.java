package controllers;

import exceptions.IllegalQuizException;

import java.util.ArrayList;

public class QuizPlayerOrchestratorImpl implements QuizPlayerOrchestrator {
    private Server server;

    public QuizPlayerOrchestratorImpl(Server server) {
        this.server = server;
    }

    public ArrayList<Quiz> getQuizzes() throws IllegalGameException {
        ArrayList<Quiz> result = server.getQuizzes();
        if (result == null) throw new IllegalGameException("There are no Quizzes available.");
        return result;
    }

    @Override
    public Quiz getQuizBy(int id) throws IllegalQuizException {
        Quiz result = server.getQuiz(id);
        if (result == null) throw new IllegalQuizException("Quiz with that particular ID does not exist.");
        return result;
    }
}
