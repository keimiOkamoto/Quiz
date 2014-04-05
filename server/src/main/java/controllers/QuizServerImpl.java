package controllers;

import com.google.inject.Inject;
import factories.ItemsFactory;
import models.Quiz;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
    public ItemsFactory getItemsFactory() throws RemoteException {
        return itemsFactory;
    }

    @Override
    public void save(Quiz quiz) throws RemoteException {
        quizContainer.save(quiz);
    }
}
