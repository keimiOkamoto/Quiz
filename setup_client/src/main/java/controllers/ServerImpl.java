package controllers;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ServerImpl implements Server {
    private QuizServer quizServer;

    public ServerImpl(ServerLink serverLink) throws RemoteException, NotBoundException {
        this.quizServer = serverLink.getQuizServer();
    }

    @Override
    public int createQuiz(String title) throws RemoteException {
         return quizServer.generateQuiz(title);
    }

    @Override
    public boolean valid(String title) throws RemoteException {
        return quizServer.titleIsValid(title);
    }

    @Override
    public boolean valid(int id) throws RemoteException {
        return quizServer.iDIsValid(id);
    }

    @Override
    public void save() throws RemoteException {
        quizServer.save();
    }

    @Override
    public void closeQuiz(int id)  throws RemoteException {
        quizServer.endQuiz(id);
    }

    @Override
    public boolean isQuizNull() {
        return quizServer.isQuizNull();
    }

    @Override
    public boolean quizContains(String questionStr) {
        return quizServer.quizContains(questionStr);
    }

    @Override
    public void addQuestionToQuiz(String question) {
        quizServer.addQuestionToQuiz(question);
    }

    @Override
    public boolean isQuestionNull() {
        return quizServer.isQuestionNull();
    }

    @Override
    public boolean questionContains(String answer) {
        return quizServer.questionContains(answer);
    }

    @Override
    public void addToQuestion(String answer, boolean answerType) {
        quizServer.addToQuestion(answer, answerType);
    }

    @Override
    public boolean isQuizEmpty() {
        return quizServer.isQuizEmpty();
    }
}
