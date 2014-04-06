package controllers;

import java.util.ArrayList;

public class ServerImpl implements Server {
    QuizServer quizServer;

    public ServerImpl(ServerLink serverLink) {
        this.quizServer = serverLink.getQuizServer();
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
    public boolean checkForHighScore(Player player) {
        return quizServer.checkForHighScore(player);
    }

    @Override
    public void createPlayer(String name, String country, int age) {

    }

    @Override
    public Player getWinner(int quizId) {
        return null;
    }

    @Override
    public void setPlayerAsWinner(Player player) {

    }
}
