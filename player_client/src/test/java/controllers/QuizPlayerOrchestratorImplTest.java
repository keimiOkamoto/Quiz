package controllers;

import constants.ExceptionMessages;
import exceptions.IllegalQuizException;
import models.Player;
import models.Quiz;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuizPlayerOrchestratorImplTest {
    private QuizPlayerOrchestrator quizPlayerOrchestrator;
    private Server server;
    private Quiz quiz;
    private Player player;

    @Before
    public void buildUp() {
        server = mock(Server.class);
        player = mock(Player.class);
        quizPlayerOrchestrator = new QuizPlayerOrchestratorImpl(server);
        quiz = mock(Quiz.class);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldBeAbleToGetListOfQuizzes() throws exceptions.IllegalGameException {
        Quiz quiz = mock(Quiz.class);

        ArrayList<Quiz> arrayList = new ArrayList<>();
        arrayList.add(quiz);

        when(server.getQuizzes()).thenReturn(arrayList);
        List<Quiz> actual = quizPlayerOrchestrator.getQuizzes();

        assertEquals(arrayList, actual);
    }

    @Test
    public void shouldThrowIllegalGameExceptionIfListIsNull() throws exceptions.IllegalGameException {
        thrown.expect(exceptions.IllegalGameException.class);
        thrown.expectMessage(ExceptionMessages.NO_AVAILABLE_QUIZZES);

        when(server.getQuizzes()).thenReturn(null);
        quizPlayerOrchestrator.getQuizzes();
    }

    @Test
    public void shouldBeAbleToGetRequestedQuiz() throws IllegalQuizException {
        int id = 5;

        when(server.getQuiz(anyInt())).thenReturn(quiz);
        Quiz actual = quizPlayerOrchestrator.getQuizBy(id);

        assertEquals(quiz, actual);
        verify(server).getQuiz(anyInt());
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuizWithParticularIdDoesNotExist() throws IllegalQuizException {
        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage(ExceptionMessages.QUIZ_DOES_NOT_EXIST);

        quizPlayerOrchestrator.getQuizBy(5);
    }

    @Test
    public void shouldBeAbleToSetThePlayerAsTheWinner() throws RemoteException {
        when(quiz.checkForHighScore(anyInt())).thenReturn(true);
        quizPlayerOrchestrator.setPlayerAsWinner(player, quiz);

        verify(quiz).checkForHighScore(anyInt());
    }

    @Test
    public void shouldBeAbleToGetTheWinner() {
        int id = 6;
        when(server.getWinnerBy(anyInt())).thenReturn(player);
        Player actual = quizPlayerOrchestrator.getWinner(id);

        assertEquals(player, actual);
    }

    @Test
    public void shouldBeAbleToAddPlayer() throws RemoteException {
        String name = "KEI";
        String country = "London";
        int age = 27;

        quizPlayerOrchestrator.addPlayer(name, country, age);
        verify(server).createPlayer(name, country, age);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfNoNameIsEntered() throws RemoteException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionMessages.NO_NAME);

        quizPlayerOrchestrator.addPlayer(null, "London", 27);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfNoCountryIsEntered() throws RemoteException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionMessages.NO_COUNTRY);

        quizPlayerOrchestrator.addPlayer("Keimi", null, 27);
    }
}
