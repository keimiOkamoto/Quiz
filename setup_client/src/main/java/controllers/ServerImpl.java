package controllers;

import items.Answer;
import items.Question;
import items.Quiz;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class ServerImpl implements Server {

    private ItemsFactory itemsFactory;
    private ServerLink serverLink;


    public ServerImpl(ServerLink serverLink) {
        this.serverLink = serverLink;
        this.itemsFactory = serverLink.getItemsFactory();
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
        return itemsFactory.generateAnswer(answer);
    }

    @Override
    public boolean valid(String title) {
        return serverLink.titleIsValid(title);
    }

    @Override
    public void closeQuiz(int id) {

    }
}
