package models;

import factories.ItemsFactory;
import org.junit.Before;
import org.junit.Test;
import utils.DiskWriter;

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
    private DiskWriter diskWriter;

    @Before
    public void buildUp() throws RemoteException {
        diskWriter = mock(DiskWriter.class);
        player = mock(Player.class);
        itemsFactory = mock(ItemsFactory.class);
        scoreKeeper = new ScoreKeeperImpl(itemsFactory, diskWriter);
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
        when(itemsFactory.generateHighScore(eq(quiz), eq(player))).thenReturn(highScore);
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

        when(itemsFactory.generateHighScore(eq(quiz), eq(player))).thenReturn(highScore);
        when(highScore.getPlayerName()).thenReturn(expected);
        scoreKeeper.setLeader(quiz, player);

        HighScore actualLeader  = scoreKeeper.getLeader(id);
        String actual = actualLeader.getPlayerName();

        assertEquals(expected, actual);
    }
}
