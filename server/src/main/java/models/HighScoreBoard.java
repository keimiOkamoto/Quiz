package models;

public interface HighScoreBoard {

    /**
     * Checks if the score is the highest.
     * If it is, score is replaced with the
     * current high-score.
     *
     * @return false if score is not the highest.
     */
    boolean checkHighScore(int score);
}
