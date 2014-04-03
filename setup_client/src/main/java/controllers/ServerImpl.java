package controllers;

import items.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ServerImpl implements Server {

    private ItemsFactory itemsFactory;
    private QuizServer quizServer;


    public ServerImpl(QuizServer quizServer) throws RemoteException, NotBoundException {
        this.quizServer = quizServer;
        this.itemsFactory = quizServer.getItemsFactory();
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
        return quizServer.titleIsValid(title);
    }

    @Override
    public void closeQuiz(int id) {
        quizServer.endQuiz(id);
    }

    @Override
    public boolean valid(int id) {
        return quizServer.iDIsValid(id);
    }

    @Override
    public void save(Quiz quiz) {
        quizServer.save(quiz);
    }
}
