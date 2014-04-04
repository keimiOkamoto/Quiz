package controllers;

import items.*;
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

        when(quizServer.getItemsFactory()).thenReturn(itemsFactory);
        server = new ServerImpl(quizServer);
    }

    @Test
    public void shouldBeAbleToCreateQuizWithAValidTitle() {
        String title = "Quiz about cake.";
        Quiz expectedQuiz = mock(Quiz.class);

        when(itemsFactory.generateQuiz(anyString())).thenReturn(expectedQuiz);
        Quiz actualQuiz = server.createQuiz(title);

        assertEquals(expectedQuiz, actualQuiz);
    }

    @Test
    public void shouldBeAbleToCreateQuestion() {
        String question = "Where is the treasure buried?";
        Question expectedQuestion = mock(Question.class);

        when(itemsFactory.generateQuestion(anyString())).thenReturn(expectedQuestion);
        Question actualQuestion = server.createQuestion(question);

        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    public void shouldBeAbleToCreateAnswer() {
        String answer = "on the moon";
        Answer expectedAnswer = mock(Answer.class);

        when(itemsFactory.generateAnswer(anyString(), anyBoolean())).thenReturn(expectedAnswer);
        Answer actualQuestion = server.createAnswer(answer, true);

        assertEquals(expectedAnswer, actualQuestion);
    }

    @Test
    public void shouldBeAbleToCheckForValidQuizTitle() {
        String title = "Quiz about noodles";

        when(quizServer.titleIsValid(anyString())).thenReturn(true);
        assertTrue(server.valid(title));
    }

    @Test
    public void shouldBeAbleToCloseQuiz() {
        int id = 0;
        server.closeQuiz(id);
        verify(quizServer).endQuiz(anyInt());
    }

    @Test
    public void shouldBeAbleToCheckForValidQuizId() {
        int id = 5;

        when(quizServer.iDIsValid(anyInt())).thenReturn(true);
        assertTrue(server.valid(id));
    }

    @Test
    public void shouldBeAbleToSaveQuizToServer() {
        Quiz quiz = mock(Quiz.class);

        server.save(quiz);
        verify(quizServer).save(quiz);
    }
}
