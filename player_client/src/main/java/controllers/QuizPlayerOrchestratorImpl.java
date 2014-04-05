package controllers;

import java.util.ArrayList;

public class QuizPlayerOrchestratorImpl implements QuizPlayerOrchestrator {
    private Server server;

    public QuizPlayerOrchestratorImpl(Server server) {
        this.server = server;
    }

    public ArrayList<Quiz> getQuizzes() throws IllegalGameException {
        if (server.getQuizzes() == null) throw new IllegalGameException("There are no Quizzes available.");
        ArrayList<Quiz> result = server.getQuizzes();
        return result;
    }

    @Override
    public Quiz getQuizBy(int id) {
        return server.getQuiz(id);
    }
}
