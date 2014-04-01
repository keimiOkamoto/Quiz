package controllers;

import items.Answer;
import items.Question;
import items.Quiz;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class ServerImpl implements Server {

    private ItemsFactory itemsFactory;

    public ServerImpl(ItemsFactory itemsFactory) {
        this.itemsFactory = itemsFactory;
    }

    @Override
    public Quiz createQuiz(String title) {
        return itemsFactory.generateQuiz(title);
    }

    @Override
    public Question createQuestion(String question) {
        return itemsFactory.generateQuestion(question);
    }

    @Override
    public Answer createAnswer(String answer) {
        return null;
    }

    @Override
    public boolean valid(String title) {
        return false;
    }

    @Override
    public void closeQuiz(int id) {

    }
}
