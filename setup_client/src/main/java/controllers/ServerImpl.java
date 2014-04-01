package controllers;

import items.Answer;
import items.Question;
import items.Quiz;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class ServerImpl implements Server {

    private Registry registry;

    public ServerImpl(Registry registry) {
        this.registry = registry;
    }

    @Override
    public Quiz createQuiz(String title) {
        Quiz newQuiz = null;
        try {
            newQuiz = (Quiz)registry.lookup("Quiz");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return newQuiz;
    }

    @Override
    public Question createQuestion(String questionString) {
        return null;
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
