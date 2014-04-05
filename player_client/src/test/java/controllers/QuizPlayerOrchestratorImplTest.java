package controllers;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuizPlayerOrchestratorImplTest {

    @Test
    public void shouldBeAbleToGetListOfQuizzes() {
        Server server = mock(Server.class);
        QuizPlayerOrchestratorImpl quizPlayerOrchestrator = new QuizPlayerOrchestratorImpl(server);

        Quiz quiz = mock(Quiz.class);

        ArrayList<Quiz> arrayList = new ArrayList<>();
        arrayList.add(quiz);

        when(server.getQuizzes()).thenReturn(arrayList);
        ArrayList<Quiz> actual = quizPlayerOrchestrator.getQuizzes();

        assertEquals(arrayList, actual);
    }
}
