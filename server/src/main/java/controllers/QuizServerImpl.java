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
    private PlayerFactory playerFactory;

    @Inject
    public QuizServerImpl(ItemsFactory itemsFactory, PlayerFactory playerFactory, QuizContainer quizContainer, ScoreKeeper scoreKeeper) throws RemoteException {
        this.itemsFactory = itemsFactory;
        this.playerFactory = playerFactory;
        this.quizContainer = quizContainer;
        this.scoreKeeper = scoreKeeper;
    }

    @Override
    public boolean titleIsValid(String title) throws RemoteException {
        return !quizContainer.contains(title);
    }

    @Override
    public boolean iDIsValid(int id) throws RemoteException {
        return quizContainer.contains(id);
    }

    @Override
    public void endQuiz(int id) throws RemoteException {
        quizContainer.closeQuizWith(id);
    }

    @Override
    public void save(Quiz quiz) throws RemoteException {
        quizContainer.save(quiz);
    }

    @Override
    public ItemsFactory getItemsFactory() throws RemoteException {
        return itemsFactory;
    }

    @Override
    public Quiz generateQuiz(String title) throws RemoteException {
        return itemsFactory.generateQuiz(title);
    }

    @Override
    public Question generateQuestion(String question) throws RemoteException {
        return itemsFactory.generateQuestion(question);
    }

    @Override
    public Answer generateAnswer(String answer, boolean answerType) throws RemoteException {
        return itemsFactory.generateAnswer(answer, answerType);
    }

    /********** Player client methods ***********/

    @Override
    public List<Quiz> getQuizzes() throws RemoteException {
        return quizContainer.getQuizzes();
    }

    @Override
    public Quiz getQuiz(int id) throws RemoteException {
        return quizContainer.getQuizBy(id);
    }

    @Override
    public boolean checkForHighScore(Quiz quiz, Player player)throws RemoteException {
        return scoreKeeper.scoreIsHighest(player, quiz);
    }

    @Override
    public PlayerFactory getPlayerFactory()throws RemoteException {
        return playerFactory;
    }

    @Override
    public void setPlayerAsWinner(Quiz quiz, Player player) throws RemoteException {
        scoreKeeper.setLeader(quiz, player);
    }

    @Override
    public Player getWinnerBy(int quizId)throws RemoteException  {
        return scoreKeeper.getLeader(quizId);
    }

    @Override
    public Player generatePlayer(String name, String country, int age) throws RemoteException {
        return playerFactory.generatePlayer(name, country, age);
    }

    @Override
    public void flush() throws RemoteException {
        quizContainer.flush();
        itemsFactory.flush();
    }
}
