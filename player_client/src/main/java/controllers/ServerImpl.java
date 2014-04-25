package controllers;

import com.google.inject.Inject;
import models.HighScore;
import models.Player;
import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;

public class ServerImpl implements Server {
    private QuizServer quizServer;

    @Inject
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
    public Player createPlayer(String name, String country, int age) throws RemoteException {
        return quizServer.generatePlayer(name, country, age);
    }

    @Override
    public HighScore getWinnerBy(int quizId) throws RemoteException {
        return quizServer.getWinnerBy(quizId);
    }

    @Override
    public void setPlayerAsWinner(Player player, Quiz quiz, int score) throws RemoteException{
        quizServer.setPlayerAsWinner(quiz,player);
    }

    @Override
    public boolean checkForHighScore(Quiz quiz, Player player) throws RemoteException{
        return quizServer.checkForHighScore(quiz, player);
    }

    @Override
    public void resetPlayerScore(Player player) throws RemoteException {
        quizServer.resetPlayerScore(player);
    }

    @Override
    public List<Quiz> getClosedQuizList() throws RemoteException {
        return quizServer.getClosedQuizList();
    }
}
