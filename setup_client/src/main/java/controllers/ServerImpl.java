package controllers;

import models.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ServerImpl implements Server {

    private ItemsFactory itemsFactory;
    private QuizServer quizServer;


    public ServerImpl(ServerLink serverLink) throws RemoteException, NotBoundException {
        this.quizServer = serverLink.getQuizServer();
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
    public Answer createAnswer(String answer, boolean answerType) {
        return itemsFactory.generateAnswer(answer, answerType);
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
