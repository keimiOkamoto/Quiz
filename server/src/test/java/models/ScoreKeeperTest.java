package models;

import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScoreKeeperTest {
    private ScoreKeeper scoreKeeper;
    private Quiz quiz;
    private Player player;

    @Before
    public void buildUp() throws RemoteException {
        player = mock(Player.class);
        scoreKeeper = new ScoreKeeperImpl();
        quiz = mock(Quiz.class);
    }

    @Test
    public void shouldBeAbleToCheckIfItContainsHighScore() throws RemoteException {
        when(quiz.getScore()).thenReturn(32);
        scoreKeeper.addHighScore(quiz, player);

        assertTrue(scoreKeeper.highScoreContains(quiz));
    }

    @Test
    public void shouldBeAbleToGetHighestScore() throws RemoteException {
        int score = 32;
        when(quiz.getScore()).thenReturn(score);
        scoreKeeper.addHighScore(quiz, player);
        int actualHighScore  = scoreKeeper.getHighScore(quiz);

        assertEquals(32, actualHighScore);
    }

    @Test
    public void shouldBeAbleToGetLeader() throws RemoteException {
        int id = 6;
        String expected = "Batman";
        when(quiz.getId()).thenReturn(id);
        when(player.getName()).thenReturn(expected);
        scoreKeeper.addHighScore(quiz, player);

        Player actualLeader  = scoreKeeper.getLeader(id);
        String actual = actualLeader.getName();

        assertEquals(expected, actual);
    }
}
