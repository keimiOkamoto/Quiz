package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScoreKeeperImpl implements ScoreKeeper {
    private Map<Integer, ArrayList<Object>> scoreBoardMap = new HashMap<>();

    private HighScoreBoard highScoreBoard;

    public ScoreKeeperImpl(HighScoreBoard highScoreBoard) {
        this.highScoreBoard = highScoreBoard;
    }

    @Override
    public boolean highScoreContains(Quiz quiz) {
        return highScoreBoard.contains(quiz);
    }

    @Override
    public boolean scoreIsHighest(Quiz quiz) {
        return highScoreBoard.scoreIsHighest(quiz);
    }

    @Override
    public void addHighScore(Quiz quiz) {
        highScoreBoard.addHighScore(quiz);
    }

    @Override
    public int getHighScore(Quiz quiz) {
        return highScoreBoard.getHighScore(quiz);
    }

    @Override
    public void setLeader(Player player, Quiz quiz) {
        highScoreBoard.setLeader(player, quiz);
    }

    @Override
    public Player getLeader(Quiz quiz) {
        return null;
    }
}
