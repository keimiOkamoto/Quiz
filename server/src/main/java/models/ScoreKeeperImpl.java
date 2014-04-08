package models;

import com.google.inject.Singleton;

import java.util.*;

@Singleton
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
    public Player getLeader(int quizId) {
        List<Object> list = scoreBoardMap.get(quizId);
        return (Player)list.get(1);
    }

    @Override
    public void setLeader(Quiz quiz, Player player) {
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
