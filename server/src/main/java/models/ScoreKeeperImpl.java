package models;

import java.util.*;

public class ScoreKeeperImpl implements ScoreKeeper {
    private Map<Integer, List<Object>> scoreBoardMap = new HashMap<>();
    private HighScoreBoard highScoreBoard;

    public ScoreKeeperImpl(HighScoreBoard highScoreBoard) {
        this.highScoreBoard = highScoreBoard;
    }

    @Override
    public boolean highScoreContains(Quiz quiz) {
        return scoreBoardMap.containsKey(quiz.getId());
    }

    @Override
    public boolean scoreIsHighest(Quiz quiz) {
        return highScoreBoard.scoreIsHighest(quiz);
    }

    @Override
    public void addHighScore(Quiz quiz, Player player) {
        List<Object> list = Arrays.asList(quiz, player);
        scoreBoardMap.put(quiz.getId(), list);
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
