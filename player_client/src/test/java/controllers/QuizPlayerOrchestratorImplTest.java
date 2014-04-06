package controllers;

import exceptions.IllegalQuizException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuizPlayerOrchestratorImplTest {
    private QuizPlayerOrchestratorImpl quizPlayerOrchestrator;
    private Server server;
    private Quiz quiz;

    @Before
    public void buildUp() {
        server = mock(Server.class);

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
        thrown.expectMessage("Quiz with that particular ID does not exist.");

        quizPlayerOrchestrator.getQuizBy(5);
    }

    @Test
    public void shouldBeAbleToCheckForHighScore() {
        int score = 0;
        when(server.checkForHighScore(anyInt())).thenReturn(false);

        assertFalse(quizPlayerOrchestrator.checkForHighScore(score));
        verify(server).checkForHighScore(anyInt());
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
