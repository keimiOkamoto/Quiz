package controllers;

import factories.ItemsFactory;
import models.Quiz;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class QuizServerTest {
    private QuizServer quizServer;
    private ItemsFactory itemsFactory;
    private QuizContainer quizContainer;

    @Before
    public void buildUp() throws RemoteException {
        itemsFactory = mock(ItemsFactory.class);
        quizContainer = mock(QuizContainer.class);
        quizServer = new QuizServerImpl(itemsFactory, quizContainer);
    }

    @Test
    public void shouldBeAbleToCheckIfTitleForQuizIsValid() throws RemoteException {
        String title = "Quiz about noodles.";

        quizServer.titleIsValid(title);
        verify(quizContainer).contains(anyString());
    }

    @Test
    public void shouldBeAbleToCheckIfIdForQuizIsValid() throws RemoteException {
        int id = 5;
        quizServer.iDIsValid(id);
        verify(quizContainer).contains(anyInt());
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
        quizServer.save(quiz);
        verify(quizContainer).save(quiz);
    }

    /********** Player client methods ***********/

    @Test
    public void shouldBeAbleToGetListOfQuizzes() {

    }

}
