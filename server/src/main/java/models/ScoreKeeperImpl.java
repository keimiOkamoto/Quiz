package models;

import java.util.*;

public class ScoreKeeperImpl implements ScoreKeeper {
    private Map<Integer, List<Object>> scoreBoardMap = new HashMap<>();

    @Override
    public void addHighScore(Quiz quiz, Player player) {
        if (!highScoreContains(quiz) || scoreIsHighest(quiz)) {
            setLeader(quiz, player);
        }
    } //TODO append player to list if score is equal.

    @Override
    public boolean highScoreContains(Quiz quiz) {
        return scoreBoardMap.containsKey(quiz.getId());
    }

    @Override
    public int getHighScore(Quiz quiz) {
        List<Object> list = scoreBoardMap.get(quiz.getId());
        return ((Quiz) list.get(0)).getScore();
    }

    @Override
    public Player getLeader(Quiz quiz) {
        List<Object> list = scoreBoardMap.get(quiz.getId());
        return (Player)list.get(1);
    }

    /*
     * Allow other methods to set the leader of a quiz.
     * (Score and player)
     */
    private void setLeader(Quiz quiz, Player player) {
        List<Object> list = Arrays.asList(quiz, player);
        scoreBoardMap.put(quiz.getId(),list);
    }

    @Override
    public boolean scoreIsHighest(Quiz quiz) {
        boolean result = false;
        if (highScoreContains(quiz)) {
            List<Object> list = scoreBoardMap.get(quiz.getId());
            int currentHighest = ((Quiz) list.get(0)).getScore();
            if (quiz.getScore() >= currentHighest) {
                result = true;
            }
        }
        return result;
    }
}
