package views;

import constants.SetUpMessages;
import controllers.QuizOrchestrator;
import exceptions.IllegalQuizException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.rules.ExpectedException;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SetupOrchestratorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    private SetupOrchestrator setupView;
    private QuizOrchestrator quizOrchestrator;

    @Before
    public void buildUp() {
        quizOrchestrator = mock(QuizOrchestrator.class);
        setupView = new SetupOrchestratorImpl(quizOrchestrator);
    }

    @Test
    public void shouldReturnStartMessageIfUserInPutIsNull() throws RemoteException {
        setupView.setInput(null);
        String actual = setupView.getMessageForQuizTitle();

        assertEquals(SetUpMessages.START_MESSAGE, actual);
    }

    @Test
    public void shouldReturnEnterQuizTitleMessageIfUserEntersOne() throws RemoteException {
        setupView.setInput(SetUpMessages.ONE);
        String actual = setupView.getMessageForQuizTitle();

        assertEquals(SetUpMessages.ENTER_QUIZ_TITLE, actual);
    }

    @Test
    public void shouldReturnEnterQuizIdRequestMessageIfUserEntersTwo() throws RemoteException {
        setupView.setInput(SetUpMessages.TWO);
        String actual = setupView.getMessageForQuizTitle();

        assertEquals(SetUpMessages.ENTER_QUIZ_ID_REQUEST, actual);
    }

    @Test
     public void shouldThrowIllegalQuizExceptionIfTitleIsADuplicate() throws RemoteException, IllegalQuizException {
        doThrow(new IllegalQuizException("Helpful message")).when(quizOrchestrator).createQuiz(anyString());

        setupView.setInput(SetUpMessages.ONE);
        setupView.getMessageForQuizTitle();
        setupView.setInput("Quiz about cake.");
        setupView.getMessageForQuizTitle();

        assertEquals("Helpful message\n", log.getLog());
    }

    @Test
     public void shouldThrowIllegalArgumentExceptionIfTitleIsNull() throws RemoteException, IllegalQuizException {
        doThrow(new IllegalArgumentException("Helpful message")).when(quizOrchestrator).createQuiz(anyString());

        setupView.setInput(SetUpMessages.ONE);
        setupView.getMessageForQuizTitle();
        setupView.setInput("Quiz about cake.");
        setupView.getMessageForQuizTitle();

        assertEquals("Helpful message\n", log.getLog());
    }

    @Test
    public void shouldReturnOriginalMessageIfTitleIsADuplicate() throws RemoteException, IllegalQuizException {
        doThrow(new IllegalQuizException("Helpful message")).when(quizOrchestrator).createQuiz(anyString());

        setupView.setInput(SetUpMessages.ONE);
        String message1 = setupView.getMessageForQuizTitle();
        setupView.setInput("Quiz about cake.");
        String message2 = setupView.getMessageForQuizTitle();

        assertEquals(message1, message2);
    }

    @Test
    public void shouldBeAbleToCreateQuiz() throws RemoteException, IllegalQuizException {
        String title = "Quiz about cake.";

        setupView.setInput(SetUpMessages.ONE);
        setupView.getMessageForQuizTitle();
        setupView.setInput(title);
        setupView.getMessageForQuizTitle();

        verify(quizOrchestrator).createQuiz("Quiz about cake.");
    }

    @Test
    public void shouldOutPutQuizIdUponQuizCreation() throws RemoteException, IllegalQuizException {
        String title = "Quiz about cake.";

        setupView.setInput(SetUpMessages.ONE);
        setupView.getMessageForQuizTitle();
        setupView.setInput(title);
        setupView.getMessageForQuizTitle();

        assertEquals("Your quiz ID is: 0\n", log.getLog());
    }
}
