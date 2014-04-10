package controllers;

import com.google.inject.Singleton;
import models.Quiz;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Singleton
public class ClosedQuizContainerTest {

    @Test
    public void shouldBeAbleToAddQuizThatHasBeenClosed() throws RemoteException {
        int id = 5;
        ClosedQuizContainer closedQuizContainer = new ClosedQuizContainerImpl();

        Quiz expected = mock(Quiz.class);
        when(expected.getId()).thenReturn(id);
        closedQuizContainer.add(expected);

        Quiz actual = closedQuizContainer.getQuiz(id);

        assertEquals(expected, actual);
    }
}
