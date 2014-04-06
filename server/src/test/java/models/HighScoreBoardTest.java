package models;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class HighScoreBoardTest {

    @Test
    public void shouldBeAbleToCheckForHighScore() {
        Player player = mock(Player.class);
        Quiz quiz = mock(Quiz.class);

        HighScoreBoard highScoreBoard = new HighScoreBoardImpl();

        assertTrue(highScoreBoard.contains(quiz));
    }

}
