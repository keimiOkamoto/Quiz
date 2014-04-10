package controllers;

import models.Quiz;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuizContainerImplTest {

    private QuizContainer quizContainer;
    private Quiz quiz;
    private ClosedQuizContainer closedQuizContainer;

    @Before
    public void buildUp() throws RemoteException {
        closedQuizContainer = mock(ClosedQuizContainer.class);
        quiz = mock(Quiz.class);
        quizContainer = new QuizContainerImpl(closedQuizContainer);
    }
    
    @Test
    public void shouldBeAbleToGetASavedQuiz() throws RemoteException {
        int id = 5;
        when(quiz.getId()).thenReturn(id);
        quizContainer.save(quiz);
        Quiz actualQuiz = quizContainer.getQuizBy(id);

        assertEquals(quiz, actualQuiz);
    }

    @Test
    public void shouldBeAbleToCheckIfTitleExists() throws RemoteException {
        String title = "Quiz about cookies?";
        int id = 6;

        when(quiz.getId()).thenReturn(id);
        when(quiz.getTitle()).thenReturn(title);
        quizContainer.save(quiz);

        assertTrue(quizContainer.contains(title));
        assertFalse(quizContainer.contains("Smurfs your mum"));
    }

    @Test
    public void shouldBeAbleToValidateSavedQuizById() throws RemoteException {
        int id = 6;
        when(quiz.getId()).thenReturn(id);
        quizContainer.save(quiz);

        assertTrue(quizContainer.contains(id));
    }

    @Test
    public void shouldNotBeAbleToRetrieveClosedQuiz() throws RemoteException {
        int id = 12;

        when(quiz.getId()).thenReturn(id);
        quizContainer.save(quiz);
        quizContainer.closeQuizWith(id);

        Quiz actualQuiz = quizContainer.getQuizBy(id);
        verify(closedQuizContainer).add(quiz);

        assertEquals(null, actualQuiz);
    }

    @Test
    public void shouldBeAbleToGetListOfQuizzes() throws RemoteException {
        Quiz quiz1 = mock(Quiz.class);
        List<Quiz> expected = new ArrayList<>();
        expected.add(quiz);
        expected.add(quiz1);

        when(quiz.getId()).thenReturn(5);
        when(quiz1.getId()).thenReturn(6);

        quizContainer.save(quiz);
        quizContainer.save(quiz1);

        List<Quiz> actualQuizzes = quizContainer.getQuizzes();

        assertEquals(expected,actualQuizzes);
    }
}
