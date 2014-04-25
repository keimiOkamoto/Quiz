package controllers;

import com.google.inject.Singleton;
import models.Quiz;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Singleton
public class ClosedQuizContainerTest {

    private ClosedQuizContainer closedQuizContainer;
    private Quiz quiz;

    @Before
    public void buildUp() {
        quiz = mock(Quiz.class);
        closedQuizContainer = new ClosedQuizContainerImpl();
    }

    @Test
    public void shouldBeAbleToAddQuizThatHasBeenClosed() throws RemoteException {
        int id = 5;

        when(quiz.getId()).thenReturn(id);
        closedQuizContainer.add(quiz);

        Quiz actual = closedQuizContainer.getQuiz(id);

        assertEquals(quiz, actual);
    }

    @Test
    public void shouldBeAbleToGetAListOfClosedQuizzes() throws RemoteException {
        Quiz quiz1 = mock(Quiz.class);
        List<Quiz> expected = new ArrayList<>();
        expected.add(quiz);
        expected.add(quiz1);

        when(quiz.getId()).thenReturn(1);
        when(quiz1.getId()).thenReturn(2);
        closedQuizContainer.add(quiz);
        closedQuizContainer.add(quiz1);

        List<Quiz> actual = closedQuizContainer.getClosedQuizList();

        assertEquals(expected, actual);
    }
}
