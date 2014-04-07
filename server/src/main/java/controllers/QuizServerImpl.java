package controllers;

import com.google.inject.Inject;
import factories.ItemsFactory;
import factories.PlayerFactory;
import models.Player;
import models.Quiz;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class QuizServerImpl extends UnicastRemoteObject implements QuizServer {

    private ItemsFactory itemsFactory;
    private QuizContainer quizContainer;

    @Inject
    public QuizServerImpl(ItemsFactory itemsFactory, QuizContainer quizContainer) throws RemoteException {
        this.itemsFactory = itemsFactory;
        this.quizContainer = quizContainer;
    }

    @Override
    public boolean titleIsValid(String title) throws RemoteException {
        return quizContainer.contains(title);
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
        return false;
    }

    @Override
    public PlayerFactory getPlayerFactory()throws RemoteException {
        return null;
    }

    @Override
    public Player getWinnerBy(int quizId)throws RemoteException  {
        return null;
    }

    @Override
    public void setPlayerAsWinner(Player player, Quiz quiz) throws RemoteException {

    }


}
