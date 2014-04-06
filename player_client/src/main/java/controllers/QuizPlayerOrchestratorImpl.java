package controllers;

import constants.ExceptionMessages;
import exceptions.IllegalQuizException;
import models.Player;
import models.Quiz;

import java.util.ArrayList;

public class QuizPlayerOrchestratorImpl implements QuizPlayerOrchestrator {
    private Server server;

    public QuizPlayerOrchestratorImpl(Server server) {
        this.server = server;
    }

    public ArrayList<Quiz> getQuizzes() throws exceptions.IllegalGameException {
        ArrayList<Quiz> result = server.getQuizzes();
        if (result == null) throw new exceptions.IllegalGameException(ExceptionMessages.NO_AVAILABLE_QUIZZES);
        return result;
    }

    @Override
    public Quiz getQuizBy(int id) throws IllegalQuizException {
        Quiz result = server.getQuiz(id);
        if (result == null) throw new IllegalQuizException(ExceptionMessages.QUIZ_DOES_NOT_EXIST);
        return result;
    }

    @Override
    public void addPlayer(String name, String country, int age) throws IllegalArgumentException {
        if (name == null) throw new IllegalArgumentException(ExceptionMessages.NO_NAME);
        if (country == null) throw new IllegalArgumentException(ExceptionMessages.NO_COUNTRY);
        server.createPlayer(name, country, age);
    }

    @Override
    public void setPlayerAsWinner(Player player) {
        if (server.checkForHighScore(player)) server.setPlayerAsWinner(player);
    }

    @Override
    public Player getWinner(int quizId) {
        return server.getWinnerBy(quizId);
    }
}
