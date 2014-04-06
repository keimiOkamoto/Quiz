package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ScoreKeeperTest {
    private ScoreKeeper scoreKeeper;
    private Quiz quiz;
    private HighScoreBoard highScoreBoard;
    private Player player;

    @Before
    public void buildUp() {
        highScoreBoard = mock(HighScoreBoard.class);
        player = mock(Player.class);
        scoreKeeper = new ScoreKeeperImpl(highScoreBoard);
        quiz = mock(Quiz.class);
    }

    @Test
    public void shouldBeAbleToCheckIfItContainsHighScore() {
        when(quiz.getScore()).thenReturn(32);
        scoreKeeper.addHighScore(quiz, player);

        assertTrue(scoreKeeper.highScoreContains(quiz));
    }

    @Test
    public void shouldBeAbleToCheckForHighScore() {
        scoreKeeper.scoreIsHighest(quiz);
        verify(highScoreBoard).scoreIsHighest(quiz);
    }

    @Test
    public void shouldBeAbleToAddHighScore() {
        scoreKeeper.addHighScore(quiz, player);
        verify(highScoreBoard).addHighScore(quiz);
    }

    @Test
    public void shouldBeAbleToGetHigScore() {
        scoreKeeper.getHighScore(quiz);
        verify(highScoreBoard).getHighScore(quiz);
    }

    @Test
    public void shouldBeAbleToSetThePlayerWithTheHighestScore() {
        scoreKeeper.setLeader(player, quiz);
        verify(highScoreBoard).setLeader(player, quiz);
    }

    @Test
    public void shouldBeAbleToGetThePlayerWithTheHighestScore() {
        scoreKeeper.getLeader(quiz);
        verify(highScoreBoard).getLeader(quiz);
    }
}