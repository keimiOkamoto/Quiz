package models;

public interface Quiz {

    /**
     * Check if the score is the highest score.
     *
     * @return False if it is not the highest score.
     * @param score The score of a qui.
     */
    boolean checkForHighScore(int score);

    /**
     * Getter for a Score.
     *
     * @return A Score for a player.
     */
    int getScore();
}
