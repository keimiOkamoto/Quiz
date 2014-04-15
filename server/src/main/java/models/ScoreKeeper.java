package models;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
     * @param quizId A quiz's id.
     * @return The leader of the quiz.(player)
     */
    Player getLeader(int quizId);

    /**
     * Sets the player of a particular quiz
     * as a leader.
     *
     * @param quiz A quiz with the score.
     * @param player A player of the quiz.
     */
    void setLeader(Quiz quiz, Player player);

    /**
     * Checks if the score is the highest for a
     * particular quiz.
     *
     * @param quiz A quiz with the score.
     * @return false if the score is not the highest.
     */
    public boolean scoreIsHighest(Quiz quiz);
}
