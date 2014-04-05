package controllers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuizPlayerOrchestratorImplTest {

    private QuizPlayerOrchestratorImpl quizPlayerOrchestrator;
    private Server server;

    @Before
    public void buildUp() {
        server = mock(Server.class);

        quizPlayerOrchestrator = new QuizPlayerOrchestratorImpl(server);

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
}
