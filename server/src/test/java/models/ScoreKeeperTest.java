package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScoreKeeperTest {
    private ScoreKeeper scoreKeeper;
    private Quiz quiz;
    private Player player;

    @Before
    public void buildUp() {

        player = mock(Player.class);
        scoreKeeper = new ScoreKeeperImpl();
        quiz = mock(Quiz.class);
    }

    @Test
    public void shouldBeAbleToCheckIfItContainsHighScore() {
        when(quiz.getScore()).thenReturn(32);
        scoreKeeper.addHighScore(quiz, player);

        assertTrue(scoreKeeper.highScoreContains(quiz));
    }

    @Test
    public void shouldBeAbleToGetHighestScore() {
        int score = 32;
        when(quiz.getScore()).thenReturn(score);
        scoreKeeper.addHighScore(quiz, player);
        int actualHighScore  = scoreKeeper.getHighScore(quiz);

        assertEquals(32, actualHighScore);
    }
}
