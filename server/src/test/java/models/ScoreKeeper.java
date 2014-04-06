package models;

/**
 * Score keeper class to keep scores organised.
 */
public interface ScoreKeeper {

    /**
     * Checks if a previous high score exists.
     *
     * @param quiz A quiz.
     * @return false if score does not exist.
     */
    boolean highScoreContains(Quiz quiz);

    /**
     * Checks if the score is the highest.
     *
     * @param quiz A quiz.
     * @return false if the quizzes score is not the highest.
     */
    boolean scoreIsHighest(Quiz quiz);

    /**
     * Adds high score.
     */
    void addHighScore(Quiz quiz);

    /**
     * Getter for the highest score corresponding
     * to the quiz.
     *
     * @param quiz A quiz.
     * @return The highest score for that quiz.
     */
    int getHighScore(Quiz quiz);

    /**
     * Setter for the leading player of a quiz.
     *
     * @param player A player.
     * @param quiz A quiz.
     */
    void setLeader(Player player, Quiz quiz);

    /**
     * Getter for the leader of a quiz.
     *
     * @param quiz A quiz.
     * @return The leader of the quiz.(player)
     */
    Player getLeader(Quiz quiz);
}
