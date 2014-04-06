package models;

public class HighScoreBoardImpl implements HighScoreBoard {
    private static HighScoreBoard highScoreBoard;

    private HighScoreBoardImpl() {
    }

    public static HighScoreBoard getInstance() {
        if (highScoreBoard == null) {
            highScoreBoard = new HighScoreBoardImpl();
        }
        return highScoreBoard;
    }

    @Override
    public boolean checkHighScore(int score) {
        return true;
    }
}
