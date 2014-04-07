package models;

import java.util.*;

public class ScoreKeeperImpl implements ScoreKeeper {
    private Map<Integer, List<Object>> scoreBoardMap = new HashMap<>();

    @Override
    public void addHighScore(Quiz quiz, Player player) {
        if (!highScoreContains(quiz) || scoreIsHighest(quiz)) {
            setLeader(quiz, player);
        }
    }

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
    public Player getLeader(Quiz quiz, Player player) {
        return null;
    }

    /*
     * Allow other methods to set the leader of a quiz.
     * (Score and player)
     */
    private void setLeader(Quiz quiz, Player player) {
        List<Object> list = Arrays.asList(quiz, player);
        scoreBoardMap.put(quiz.getId(),list);
    }

    /*
     * Checks if the quiz score is the highest.
     */
    private boolean scoreIsHighest(Quiz quiz) {
        boolean result = false;
        if (highScoreContains(quiz)) {
            List<Object> list = scoreBoardMap.get(quiz.getId());
            if (quiz.getScore() > ((Quiz) list.get(0)).getScore()) {
                result = true;
            }
        }
        return result;
    }
}
