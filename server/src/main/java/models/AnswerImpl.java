package models;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AnswerImpl extends UnicastRemoteObject implements Answer {

    private String answerStr;
    private boolean answerType;

    public AnswerImpl(String answerStr, boolean answerType) throws RemoteException {
        this.answerStr = answerStr;
        this.answerType = answerType;
    }

    @Override
    public String getAnswer() throws RemoteException{
        return answerStr;
    }

    @Override
    public boolean getAnswerType() throws RemoteException{
        return answerType;
    }
}
