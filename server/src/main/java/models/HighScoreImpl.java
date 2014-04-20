package models;

import java.rmi.RemoteException;

public class HighScoreImpl implements HighScore {

    private Quiz quiz;
    private Player player;

    public HighScoreImpl(Quiz quiz, Player player) {
        this.quiz = quiz;
        this.player = player;
    }

    @Override
    public int getHighScore() throws RemoteException {
        return player.getScore();
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
