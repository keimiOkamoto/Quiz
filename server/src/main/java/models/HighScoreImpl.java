package models;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HighScoreImpl extends UnicastRemoteObject implements HighScore {

    private int score;
    private int age;
    private Quiz quiz;
    private String name;
    private String country;

    public HighScoreImpl(Quiz quiz, Player player) throws RemoteException {
        this.quiz = quiz;
        try {
            this.name = player.getName();
            this.country = player.getCountry();
            this.age = player.getAge();
            this.score = player.getScore();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPlayerName() throws RemoteException {
        return name;
    }

    @Override
    public String getPlayerCountry() throws RemoteException {
        return country;
    }

    @Override
    public int getPlayerAge() throws RemoteException {
        return age;
    }

    @Override
    public int getHighScore() throws RemoteException {
        return score;
    }
}
