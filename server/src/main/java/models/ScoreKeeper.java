package models;

/**
 * Score keeper class to keep scores organised.
 */
public interface ScoreKeeper {

    /**
     * Adds high score.
     */
    void addHighScore(Quiz quiz, Player player);

    /**
     * Checks if a previous high score exists.
     *
     * @param quiz A quiz.
     * @return false if score does not exist.
     */
    boolean highScoreContains(Quiz quiz);

    /**
     * Getter for the highest score corresponding
     * to the quiz.
     *
     * @param quiz A quiz.
     * @return The highest score for that quiz.
     */
    int getHighScore(Quiz quiz);

    /**
     * Getter for the leader of a quiz.
     *
     * @param quiz A quiz.
     * @return The leader of the quiz.(player)
     */
    Player getLeader(Quiz quiz);
}
