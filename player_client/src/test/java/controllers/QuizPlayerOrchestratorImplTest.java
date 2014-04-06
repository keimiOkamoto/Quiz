package controllers;

import exceptions.IllegalQuizException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
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
    public void shouldBeAbleToGetListOfQuizzes() throws IllegalGameException {
        Quiz quiz = mock(Quiz.class);

        ArrayList<Quiz> arrayList = new ArrayList<>();
        arrayList.add(quiz);

        when(server.getQuizzes()).thenReturn(arrayList);
        ArrayList<Quiz> actual = quizPlayerOrchestrator.getQuizzes();

        assertEquals(arrayList, actual);
    }

    @Test
    public void shouldThrowIllegalGameExceptionIfListIsNull() throws IllegalGameException {
        thrown.expect(IllegalGameException.class);
        thrown.expectMessage("There are no Quizzes available.");

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
    public void shouldBeAbleToSetThePlayerAsTheWinner() {
        when(server.checkForHighScore(eq(player))).thenReturn(true);
        quizPlayerOrchestrator.setPlayerAsWinner(player);

        verify(server).checkForHighScore(eq(player));
    }

    @Test
    public void shouldBeAbleToGetTheWinner() {
        int id = 6;
        when(server.getWinner(anyInt())).thenReturn(player);
        Player actual = quizPlayerOrchestrator.getWinner(id);

        assertEquals(player, actual);
    }

    @Test
    public void shouldBeAbleToAddPlayer() {
        String name = "KEI";
        String country = "London";
        int age = 27;

        quizPlayerOrchestrator.addPlayer(name, country, age);
        verify(server).createPlayer(name, country, age);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfNoNameIsEntered() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Please enter your name.");

        quizPlayerOrchestrator.addPlayer(null, "London", 27);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfNoCountryIsEntered() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Please enter your country of origin.");

        quizPlayerOrchestrator.addPlayer("Keimi", null, 27);
    }
}
