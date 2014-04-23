package controllers;

import factories.ItemsFactory;
import factories.PlayerFactory;
import models.Player;
import models.Quiz;
import models.ScoreKeeper;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuizServerTest {
    private QuizServer quizServer;
    private ItemsFactory itemsFactory;
    private QuizContainer quizContainer;
    private Quiz quiz;
    private ScoreKeeper scoreKeeper;
    private Player player;
    private PlayerFactory playerFactory;
    private ClosedQuizContainer closedQuizContainer;

    @Before
    public void buildUp() throws RemoteException {
        playerFactory = mock(PlayerFactory.class);
        player = mock(Player.class);
        scoreKeeper = mock(ScoreKeeper.class);
        quiz = mock(Quiz.class);
        itemsFactory = mock(ItemsFactory.class);
        quizContainer = mock(QuizContainer.class);
        closedQuizContainer = mock(ClosedQuizContainer.class);
        quizServer = new QuizServerImpl(itemsFactory, playerFactory, quizContainer, scoreKeeper, closedQuizContainer);
    }

    @Test
    public void shouldBeAbleToCheckIfTitleForQuizIsValid() throws RemoteException {
        String title = "Quiz about noodles.";

        quizServer.titleIsValid(title);
        verify(quizContainer).contains(anyString());

        when(quiz.getTitle()).thenReturn(title);
        quizContainer.save(quiz);

        when(quizContainer.contains(anyString())).thenReturn(false);
        assertTrue(quizServer.titleIsValid(title));
    }

    @Test
    public void shouldBeAbleToCheckIfIdForQuizIsValid() throws RemoteException {
        int id = 5;
        quizServer.iDIsValid(id);
        verify(quizContainer).contains(anyInt());

        when(quizContainer.contains(anyInt())).thenReturn(true);

        assertTrue((quizServer.iDIsValid(5)));
    }

    @Test
    public void shouldBeAbleToCloseQuizById() throws RemoteException {
        int id  = 0;
        quizServer.endQuiz(id);
        verify(quizContainer).closeQuizWith(id);
    }

    @Test
    public void shouldBeAbleToGetItemsFactory() throws RemoteException {
        ItemsFactory actualItemsFactory = quizServer.getItemsFactory();
        assertEquals(itemsFactory, actualItemsFactory);
    }

    @Test
    public void shouldBeAbleToSaveQuizToContainer() throws RemoteException {
        Quiz quiz = mock(Quiz.class);
        quizServer.save();
        verify(quizContainer).save(quiz);
    }

    /********** Player client methods ***********/

    @Test
    public void shouldBeAbleToGetListOfQuizzes() throws RemoteException {
        quizServer.getQuizzes();
        verify(quizContainer).getQuizzes();
    }

    @Test
    public void shouldBeAbleToGetQuizById() throws RemoteException {
        int id  = 6;

        quizServer.getQuiz(id);
        verify(quizContainer).getQuizBy(id);

        when(quizContainer.getQuizBy(anyInt())).thenReturn(quiz);
        Quiz actual = quizServer.getQuiz(id);

        assertEquals(quiz, actual);
    }

    @Test
    public void shouldBeAbleToCheckForHighScoreOfAParticularQuiz() throws RemoteException {
        quizServer.checkForHighScore(quiz, player);
        verify(scoreKeeper).scoreIsHighest(player, quiz);

        when(player.getScore()).thenReturn(50);
        when(scoreKeeper.scoreIsHighest(player, quiz)).thenReturn(true);

        assertTrue(quizServer.checkForHighScore(quiz, player));
    }

    @Test
    public void shouldBeAbleToGetAPlayerFactory() throws RemoteException {
        PlayerFactory actualPlayerFactory = quizServer.getPlayerFactory();

        assertEquals(playerFactory, actualPlayerFactory);
    }

    @Test
    public void shouldBeAbleToSetWinner() throws RemoteException {
        int quizId = 5;

        when(quiz.getId()).thenReturn(quizId);
        when(player.getScore()).thenReturn(50);
        when(player.getName()).thenReturn("Green Goblin");
        quizServer.setPlayerAsWinner(quiz, player);
        verify(scoreKeeper).setLeader(quiz, player);

        when(scoreKeeper.getLeader(quizId)).thenReturn(player);
        Player actual = quizServer.getWinnerBy(quizId);

        assertEquals(player, actual);
    }
}
