package models;

import java.io.Serializable;
import java.rmi.RemoteException;

public class HighScoreImpl implements HighScore, Serializable {

    private int score;
    private int age;
    private Quiz quiz;
    private String name;
    private String country;

    public HighScoreImpl(Quiz quiz, Player player) {
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
    public String getPlayerName() {
        return name;
    }

    @Override
    public String getPlayerCountry() {
        return country;
    }

    @Override
    public int getPlayerAge() {
        return age;
    }

    @Override
    public int getHighScore() throws RemoteException {
        return score;
    }
}
