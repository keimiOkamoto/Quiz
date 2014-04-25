package controllers;

import factories.ItemsFactory;
import factories.PlayerFactory;
import models.*;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
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
    private HighScore highScore;

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
        highScore = mock(HighScore.class);
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
    public void shouldBeAbleToSaveQuizToContainer() throws RemoteException {
        when(itemsFactory.generateQuiz(anyString())).thenReturn(quiz);
        quizServer.generateQuiz("any title");

        quizServer.save();

        verify(quizContainer).save(eq(quiz));
    }

    @Test
    public void shouldBeAbleToGetItemsFactory() throws RemoteException {
        ItemsFactory actualItemsFactory = quizServer.getItemsFactory();
        assertEquals(itemsFactory, actualItemsFactory);
    }

    /********** Player client methods ***********/

    @Test
    public void shouldBeAbleToGenerateAQuiz() throws RemoteException {
        String title = "Quiz about cats";
        int id = 5;
        when(quiz.getId()).thenReturn(id);
        when(itemsFactory.generateQuiz(anyString())).thenReturn(quiz);

        int actual = quizServer.generateQuiz(title);

        assertEquals(id, actual);
    }

    @Test
    public void shouldBeAbleToGenerateAQuestion() throws RemoteException {
        Question question = mock(Question.class);
        String questionStr = "What colour is a smurf?";

        when(question.getQuestion()).thenReturn(questionStr);
        when(itemsFactory.generateQuestion(anyString())).thenReturn(question);

        Question actual = quizServer.generateQuestion(questionStr);
        String actualStr = actual.getQuestion();

        assertEquals(question, actual);
        assertEquals(questionStr, actualStr);
    }

    @Test
    public void shouldBeAbleToGenerateAnswer() throws RemoteException {
        Answer answer = mock(Answer.class);
        String answerStr = "blue";

        when(itemsFactory.generateAnswer(anyString(), anyBoolean())).thenReturn(answer);

        Answer actual = quizServer.generateAnswer(answerStr, true);

        assertEquals(answer, actual);
    }

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

        quizServer.setPlayerAsWinner(quiz, player);
        verify(scoreKeeper).setLeader(quiz, player);

        when(scoreKeeper.getLeader(quizId)).thenReturn(highScore);
        HighScore actual = quizServer.getWinnerBy(quizId);

        assertEquals(highScore, actual);
    }

    @Test
    public void shouldBeAbleToGetWinnerByID() throws RemoteException {
        int id = 5;

        when(scoreKeeper.getLeader(anyInt())).thenReturn(highScore);
        HighScore actual = quizServer.getWinnerBy(id);

        assertEquals(highScore, actual);
        verify(scoreKeeper).getLeader(anyInt());
    }

    @Test
    public void shouldBeAbleToGeneratePlayer() throws RemoteException {
        String name = "Adam";
        String country = "UK";
        int age = 21;

        when(playerFactory.generatePlayer(anyString(), anyString(), anyInt())).thenReturn(player);
        when(player.getName()).thenReturn(name);
        when(player.getCountry()).thenReturn(country);

        Player actual = quizServer.generatePlayer(name, country, age);

        assertEquals(player.getName(), actual.getName());

        verify(playerFactory).generatePlayer(anyString(),anyString(),anyInt());
    }
}
