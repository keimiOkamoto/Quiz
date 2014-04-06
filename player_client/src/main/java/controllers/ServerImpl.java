package controllers;

import models.Player;
import models.PlayerFactory;
import models.Quiz;
import models.QuizServer;

import java.util.ArrayList;

public class ServerImpl implements Server {
    private QuizServer quizServer;
    private PlayerFactory playerFactory;

    public ServerImpl(ServerLink serverLink) {
        quizServer = serverLink.getQuizServer();
        playerFactory = quizServer.getPlayerFactory();
    }

    @Override
    public ArrayList<Quiz> getQuizzes() {
        return quizServer.getQuizzes();
    }

    @Override
    public Quiz getQuiz(int id) {
        return quizServer.getQuiz(id);
    }

    @Override
    public void createPlayer(String name, String country, int age) {
        playerFactory.generatePlayer(name, country, age);
    }

    @Override
    public Player getWinnerBy(int quizId) {
        return quizServer.getWinnerBy(quizId);
    }

    @Override
    public void setPlayerAsWinner(Player player, Quiz quiz) {
        quizServer.setPlayerAsWinner(player, quiz);
    }
}
