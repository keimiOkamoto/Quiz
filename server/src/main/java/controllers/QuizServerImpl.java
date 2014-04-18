package controllers;

import com.google.inject.Inject;
import factories.ItemsFactory;
import factories.PlayerFactory;
import models.*;
import utils.UniqueNumberGeneratorUtils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class QuizServerImpl extends UnicastRemoteObject implements QuizServer {

    private ItemsFactory itemsFactory;
    private QuizContainer quizContainer;
    private ScoreKeeper scoreKeeper;
    private ClosedQuizContainer closedQuizContainer;
    private PlayerFactory playerFactory;

    @Inject
    public QuizServerImpl(ItemsFactory itemsFactory, PlayerFactory playerFactory, QuizContainer quizContainer, ScoreKeeper scoreKeeper, ClosedQuizContainer closedQuizContainer) throws RemoteException {
        this.itemsFactory = itemsFactory;
        this.playerFactory = playerFactory;
        this.quizContainer = quizContainer;
        this.scoreKeeper = scoreKeeper;
        this.closedQuizContainer = closedQuizContainer;
    }

    @Override
    public synchronized boolean titleIsValid(String title) throws RemoteException {
        return !quizContainer.contains(title);
    }

    @Override
    public synchronized boolean iDIsValid(int id) throws RemoteException {
        return quizContainer.contains(id);
    }

    @Override
    public synchronized void endQuiz(int id) throws RemoteException {
        quizContainer.closeQuizWith(id);
    }

    @Override
    public synchronized void save(Quiz quiz) throws RemoteException {
        quizContainer.save(quiz);
    }

    @Override
    public synchronized ItemsFactory getItemsFactory() throws RemoteException {
        return itemsFactory;
    }

    @Override
    public synchronized Quiz generateQuiz(String title) throws RemoteException {
        return itemsFactory.generateQuiz(title);
    }

    @Override
    public synchronized Question generateQuestion(String question) throws RemoteException {
        return itemsFactory.generateQuestion(question);
    }

    @Override
    public synchronized Answer generateAnswer(String answer, boolean answerType) throws RemoteException {
        return itemsFactory.generateAnswer(answer, answerType);
    }

    /********** Player client methods ***********/

    @Override
    public synchronized List<Quiz> getQuizzes() throws RemoteException {
        return quizContainer.getQuizzes();
    }

    @Override
    public synchronized Quiz getQuiz(int id) throws RemoteException {
        return quizContainer.getQuizBy(id);
    }

    @Override
    public synchronized boolean checkForHighScore(Quiz quiz, Player player)throws RemoteException {
        return scoreKeeper.scoreIsHighest(player, quiz);
    }

    @Override
    public synchronized PlayerFactory getPlayerFactory()throws RemoteException {
        return playerFactory;
    }

    @Override
    public synchronized void setPlayerAsWinner(Quiz quiz, Player player) throws RemoteException {
        scoreKeeper.setLeader(quiz, player);
    }

    @Override
    public synchronized Player getWinnerBy(int quizId)throws RemoteException  {
        return scoreKeeper.getLeader(quizId);
    }

    @Override
    public synchronized Player generatePlayer(String name, String country, int age) throws RemoteException {
        return playerFactory.generatePlayer(name, country, age);
    }

    @Override
    public synchronized void resetPlayerScore(Player player) throws RemoteException {
        player.resetScore();
    }

    @Override
    public synchronized List<Quiz> getClosedQuizList() throws RemoteException {
        return closedQuizContainer.getClosedQuizList();
    }
    @Override
    public synchronized void flush() throws RemoteException {
        quizContainer.flush();
        itemsFactory.flush();
    }
}
