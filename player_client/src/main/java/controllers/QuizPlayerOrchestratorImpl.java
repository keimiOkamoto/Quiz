package controllers;

import constants.ExceptionMessages;
import exceptions.IllegalGameException;
import exceptions.IllegalQuizException;
import models.HighScore;
import models.Player;
import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;

public class QuizPlayerOrchestratorImpl implements QuizPlayerOrchestrator {
    private Server server;

    public QuizPlayerOrchestratorImpl(Server server) {
        this.server = server;
    }

    public List<Quiz> getQuizzes() throws IllegalGameException {
        List<Quiz> result = server.getQuizzes();
        if (result == null) throw new IllegalGameException(ExceptionMessages.NO_AVAILABLE_QUIZZES);
        return result;
    }

    @Override
    public Quiz getQuizBy(int id) throws IllegalQuizException {
        Quiz result = server.getQuiz(id);
        if (result == null) throw new IllegalQuizException(ExceptionMessages.QUIZ_DOES_NOT_EXIST);
        return result;
    }

    @Override
    public Player addPlayer(String name, String country, int age) throws IllegalArgumentException, RemoteException {
        if (name == null) throw new IllegalArgumentException(ExceptionMessages.NO_NAME);
        if (country == null) throw new IllegalArgumentException(ExceptionMessages.NO_COUNTRY);
        return server.createPlayer(name, country, age);
    }

    @Override
    public void setPlayerAsWinner(Player player, Quiz quiz) throws RemoteException {
        if (server.checkForHighScore(quiz, player)) server.setPlayerAsWinner(player, quiz, player.getScore());
    }

    @Override
    public void resetPlayerScore(Player player) throws RemoteException {
        server.resetPlayerScore(player);
    }

    @Override
    public HighScore getWinner(int quizId) throws RemoteException {
        return server.getWinnerBy(quizId);
    }

    @Override
    public List<Quiz> getClosedQuizList() throws RemoteException {
        return server.getClosedQuizList();
    }
}
