package models;

public interface HighScoreBoard {

    /**
     * Checks if the score is the highest.
     * If it is, score is replaced with the
     * current high-score.
     *
     * @return false if score is not the highest.
     * @param quiz A quiz.
     */
    boolean contains(Quiz quiz);

    /**
     * Checks if the score is the highest.
     *
     * @param quiz A quiz.
     * @return false if the quizzes score is not the highest.
     */
    boolean scoreIsHighest(Quiz quiz);

    /**
     * Adds high score.
     *
     * @param quiz A quiz containing the score.
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
     * Setter for the leading player of a quz.
     *
     * @param player A player of a quiz.
     */
    void setLeader(Player player, Quiz quiz);

    Player getLeader(Quiz quiz);
}
