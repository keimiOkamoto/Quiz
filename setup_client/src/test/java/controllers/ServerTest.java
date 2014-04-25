package controllers;

import factories.ItemsFactory;
import models.*;
import org.junit.Before;
import org.junit.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServerTest {

    private Server server;
    private ItemsFactory itemsFactory;
    private QuizServer quizServer;

    @Before
    public void buildUp() throws RemoteException, NotBoundException {
        itemsFactory = mock(ItemsFactory.class);
        quizServer = mock(QuizServer.class);
        ServerLink serverLink = mock(ServerLink.class);
        when(serverLink.getQuizServer()).thenReturn(quizServer);
        when(quizServer.getItemsFactory()).thenReturn(itemsFactory);
        server = new ServerImpl(serverLink);
    }

    @Test
    public void shouldBeAbleToCreateQuizWithAValidTitle() throws RemoteException {
        String title = "Quiz about cake.";

        server.createQuiz(title);
        verify(quizServer).generateQuiz(anyString());
    }
//
//    @Test
//    public void shouldBeAbleToCreateQuestion() throws RemoteException {
//        String question = "Where is the treasure buried?";
//        Question expectedQuestion = mock(Question.class);
//
//        verify(quizServer).
//    }
//
//    @Test
//    public void shouldBeAbleToCreateAnswer() throws RemoteException {
//        String answer = "on the moon";
//        Answer expectedAnswer = mock(Answer.class);
//
////        when(quizServer.generateAnswer(anyString(), anyBoolean())).thenReturn(expectedAnswer);
////        Answer actualQuestion = server.createAnswer(answer, true);
//
////        assertEquals(expectedAnswer, actualQuestion);
//    }

    /*
     * Test for valid (String title)
     */
    @Test
    public void shouldBeAbleToCheckForValidQuizTitle() throws RemoteException {
        String title = "Quiz about noodles";

        server.valid(title);
        verify(quizServer).titleIsValid(anyString());
    }

    /*
     *Test for valid(int id)
     */
    @Test
    public void shouldBeAbleToCheckForValidQuizByID() throws RemoteException {
        int id = 8;
        server.valid(id);

        verify(quizServer).iDIsValid(anyInt());
    }

    /*
     *Test for save()
     */
    @Test
    public void shouldBeAbleToSaveQuizToServer() throws RemoteException {
        server.save();
        verify(quizServer).save();
    }

    /*
     *Test for closeQuiz(int id)
     */
    @Test
    public void shouldBeAbleToCloseQuiz() throws RemoteException {
        int id = 0;
        server.closeQuiz(id);
        verify(quizServer).endQuiz(anyInt());
    }

    /*
     * Test for isQuizNull()
     */
    @Test
    public void shouldBeAbleToCheckIfQuizIsNull() throws RemoteException {

        server.isQuizNull();
        verify(quizServer).isQuizNull();
    }
}
