package controllers;

import models.Player;
import models.PlayerFactory;
import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;

public class ServerImpl implements Server {
    private QuizServer quizServer;

    public ServerImpl(ServerLink serverLink) {
        quizServer = serverLink.getQuizServer();
    }

    @Override
    public List<Quiz> getQuizzes() {
        return quizServer.getQuizzes();
    }

    @Override
    public Quiz getQuiz(int id) {
        return quizServer.getQuiz(id);
    }

    @Override
    public void createPlayer(String name, String country, int age) throws RemoteException {
        quizServer.generatePlayer(name, country, age);
    }

    @Override
    public Player getWinnerBy(int quizId) {
        return quizServer.getWinnerBy(quizId);
    }

    @Override
    public void setPlayerAsWinner(Player player, Quiz quiz, int score) {
        quizServer.setPlayerAsWinner(player, quiz);
    }

    @Override
    public boolean checkForHighScore(Quiz quiz, Player player) {
        return quizServer.checkForHighScore(quiz, player);
    }
}
