package controllers;

import java.util.ArrayList;

public class QuizPlayerOrchestratorImpl implements QuizPlayerOrchestrator {
    private Server server;

    public QuizPlayerOrchestratorImpl(Server server) {
        this.server = server;
    }

    public ArrayList<Quiz> getQuizzes() {
        return server.getQuizzes();
    }
}
