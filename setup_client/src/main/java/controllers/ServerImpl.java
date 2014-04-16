package controllers;

import models.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl implements Server {
    private QuizServer quizServer;

    public ServerImpl(ServerLink serverLink) throws RemoteException, NotBoundException {
        this.quizServer = serverLink.getQuizServer();
    }

    @Override
    public Quiz createQuiz(String title) throws RemoteException {
        return quizServer.generateQuiz(title);
    }

    @Override
    public Question createQuestion(String question) throws RemoteException {
        return quizServer.generateQuestion(question);
    }

    @Override
    public Answer createAnswer(String answer, boolean answerType) throws RemoteException {
        return quizServer.generateAnswer(answer, answerType);
    }

    @Override
    public boolean valid(String title) throws RemoteException {
        return quizServer.titleIsValid(title);
    }

    @Override
    public void closeQuiz(int id)  throws RemoteException {
        quizServer.endQuiz(id);
    }

    @Override
    public boolean valid(int id) throws RemoteException {
        return quizServer.iDIsValid(id);
    }

    @Override
    public void save(Quiz quiz) throws RemoteException {
        quizServer.save(quiz);
    }
}
