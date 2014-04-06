package models;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ScoreKeeperTest {

    private ScoreKeeper scoreKeeper;
    private Quiz quiz;
    private HighScoreBoard highScoreBoard;

    @Before
    public void buildUp() {
        highScoreBoard = mock(HighScoreBoard.class);
        scoreKeeper = new ScoreKeeperImpl(highScoreBoard);
        quiz = mock(Quiz.class);
    }

    @Test
    public void shouldBeAbleToCheckIfItContainsHighScore() {
        scoreKeeper.highScoreContains(quiz);
        verify(highScoreBoard).contains(quiz);
    }

    @Test
    public void shouldBeAbleToCheckForHighScore() {
        scoreKeeper.scoreIsHighest(quiz);
        verify(highScoreBoard).scoreIsHighest(quiz);
    }

    @Test
    public void shouldBeAbleToAddHighScore() {
        scoreKeeper.addHighScore(quiz);
        verify(highScoreBoard).addHighScore(quiz);
    }

    @Test
    public void shouldBeAbleToGetHigScore() {
        scoreKeeper.getHighScore(quiz);
        verify(highScoreBoard).getHighScore(quiz);
    }
}
