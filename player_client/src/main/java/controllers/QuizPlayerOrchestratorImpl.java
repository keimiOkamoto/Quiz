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

    @Override
    public boolean checkForHighScore(int score) {
        return server.checkForHighScore(score);
    }

    @Override
    public void addPlayer(String name, String country, int age) throws IllegalArgumentException {
        if (name == null) throw new IllegalArgumentException("Please enter your name.");
        if (country == null) throw new IllegalArgumentException("Please enter your country of origin.");
        server.createPlayer(name, country, age);
    }
}
