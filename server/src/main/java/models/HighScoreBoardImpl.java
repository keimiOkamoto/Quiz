package models;

import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class HighScoreBoardImpl implements HighScoreBoard {
    private Map<Integer, ArrayList<Object>> scoreBoardMap = new HashMap<>();

    @Override
    public boolean contains(Quiz quiz) {
        return false;
    }

    @Override
    public boolean scoreIsHighest(Quiz quiz) {
        return false;
    }

    @Override
    public void addHighScore(Quiz quiz) {

    }

    @Override
    public int getHighScore(Quiz quiz) {
        return 0;
    }
}
