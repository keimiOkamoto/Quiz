package models;

import factories.ItemsFactory;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScoreKeeperTest {
    private ScoreKeeper scoreKeeper;
    private Quiz quiz;
    private Player player;
    private ItemsFactory itemsFactory;
    private HighScore highScore;

    @Before
    public void buildUp() throws RemoteException {
        player = mock(Player.class);
        itemsFactory = mock(ItemsFactory.class);
        scoreKeeper = new ScoreKeeperImpl(itemsFactory);
        highScore = mock(HighScore.class);
        quiz = mock(Quiz.class);
    }

    @Test
    public void shouldBeAbleToCheckIfItContainsHighScore() throws RemoteException {
        when(player.getScore()).thenReturn(32);
        scoreKeeper.addHighScore(quiz, player);

        assertTrue(scoreKeeper.highScoreContains(quiz));
    }

    @Test
    public void shouldBeAbleToGetHighestScore() throws RemoteException {
        int score = 32;
        when(player.getScore()).thenReturn(score);
        when(itemsFactory.getHighScore(eq(quiz), eq(player))).thenReturn(highScore);
        when(highScore.getHighScore()).thenReturn(score);

        scoreKeeper.addHighScore(quiz, player);


        int actualHighScore  = scoreKeeper.getHighScore(quiz);

        assertEquals(score, actualHighScore);
    }

    @Test
    public void shouldBeAbleToGetLeader() throws RemoteException {
        int id = 6;
        String expected = "Batman";
        when(quiz.getId()).thenReturn(id);
        when(player.getName()).thenReturn(expected);

        when(itemsFactory.getHighScore(eq(quiz), eq(player))).thenReturn(highScore);
        when(highScore.getPlayer()).thenReturn(player);
        scoreKeeper.setLeader(quiz, player);

        Player actualLeader  = scoreKeeper.getLeader(id);
        String actual = actualLeader.getName();

        assertEquals(expected, actual);
    }
}
