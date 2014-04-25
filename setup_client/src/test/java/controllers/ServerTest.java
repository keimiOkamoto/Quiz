package controllers;

import factories.ItemsFactory;
import org.junit.Before;
import org.junit.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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

    /*
     * Test for quizContains(String questionStr)
     */
    @Test
    public void shouldBeAbleToCheckIfQuizContainsIdBeingCalled() throws RemoteException {
        String answer = "Yes";
        server.questionContains(answer);

        verify(quizServer).questionContains(answer);
    }

    /*
     * Test for addQuestionToQuiz(String question)
     */
    @Test
    public void shouldBeAbleToCallAddQuestionToQuiz() throws RemoteException {
        String question = "Are cats cute?";
        server.addQuestionToQuiz(question);

        verify(quizServer).addQuestionToQuiz(question);
    }

    /*
     *Test for isQuestionNull()
     */
    @Test
    public void shouldBeAbleToCheckIfQuestionIsNull() {
        server.isQuestionNull();

        verify(quizServer).isQuestionNull();
    }

    /*
     * Test for questionContains(String answer)
     */
    @Test
    public void shouldBeAbleToCheckIfQuestionContainsAnAnswerStr() throws RemoteException {
        server.questionContains("Yes");

        verify(quizServer).questionContains(anyString());
    }

    /*
     * Test for addToQuestion(String answer, boolean answerType)
     */
    @Test
    public void shouldBeAbleToAddAnswersToQuestion() {
        server.addQuestionToQuiz("any question");

        verify(quizServer).addQuestionToQuiz(anyString());
    }

    /*
     * Test for isQuizEmpty()
     */
    @Test
    public void shouldBeAbleToCheckIfQuizIsEmpty() throws RemoteException {
        server.isQuizEmpty();

        verify(quizServer).isQuizEmpty();
    }
}
