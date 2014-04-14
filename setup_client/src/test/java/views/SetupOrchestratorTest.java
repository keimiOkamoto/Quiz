package views;

import constants.ExceptionMessages;
import constants.SetUpMessages;
import controllers.QuizOrchestrator;
import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;
import models.Quiz;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.rules.ExpectedException;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Matchers.*;
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
    private SetupOrchestrator setupOrchestrator;
    private QuizOrchestrator quizOrchestrator;
    private Quiz quiz;

    @Before
    public void buildUp() {
        quizOrchestrator = mock(QuizOrchestrator.class);
        quiz = mock(Quiz.class);

        setupOrchestrator = new SetupOrchestratorImpl(quizOrchestrator);
    }

    @Test
    public void shouldReturnStartMessageIfUserInPutIsNull() throws RemoteException {
        setupOrchestrator.setInput(null);
        String actual = setupOrchestrator.getMessageForQuizTitle();

        assertEquals(SetUpMessages.START_MESSAGE, actual);
    }

    @Test
    public void shouldReturnEnterQuizTitleMessageIfUserEntersOne() throws RemoteException {
        setupOrchestrator.setInput(SetUpMessages.ONE);
        String actual = setupOrchestrator.getMessageForQuizTitle();

        assertEquals(SetUpMessages.ENTER_QUIZ_TITLE, actual);
    }

    @Test
    public void shouldReturnEnterQuizIdRequestMessageIfUserEntersTwo() throws RemoteException {
        setupOrchestrator.setInput(SetUpMessages.TWO);
        String actual = setupOrchestrator.getMessageForQuizTitle();

        assertEquals(SetUpMessages.ENTER_QUIZ_ID_REQUEST, actual);
    }

    @Test
     public void shouldDisplayAHelpfulMessageIfTitleIsADuplicate() throws RemoteException, IllegalQuizException {
        doThrow(new IllegalQuizException("Helpful message")).when(quizOrchestrator).createQuiz(anyString());

        setupOrchestrator.setInput(SetUpMessages.ONE);
        setupOrchestrator.getMessageForQuizTitle();
        setupOrchestrator.setInput("Quiz about cake.");
        setupOrchestrator.getMessageForQuizTitle();

        assertEquals("Helpful message\n", log.getLog());
    }

    @Test
     public void shouldDisplayAHelpfulMessageIfTitleIsNull() throws RemoteException, IllegalQuizException {
        doThrow(new IllegalArgumentException("Helpful message")).when(quizOrchestrator).createQuiz(anyString());

        setupOrchestrator.setInput(SetUpMessages.ONE);
        setupOrchestrator.getMessageForQuizTitle();
        setupOrchestrator.setInput("Quiz about cake.");
        setupOrchestrator.getMessageForQuizTitle();

        assertEquals("Helpful message\n", log.getLog());
    }

    @Test
    public void shouldReturnOriginalMessageIfTitleIsADuplicate() throws RemoteException, IllegalQuizException {
        doThrow(new IllegalQuizException("Helpful message")).when(quizOrchestrator).createQuiz(anyString());

        setupOrchestrator.setInput(SetUpMessages.ONE);
        String message1 = setupOrchestrator.getMessageForQuizTitle();
        setupOrchestrator.setInput("Quiz about cake.");
        String message2 = setupOrchestrator.getMessageForQuizTitle();

        assertEquals(message1, message2);
    }

    @Test
    public void shouldBeAbleToCreateQuiz() throws RemoteException, IllegalQuizException {
        String title = "Quiz about cake.";

        setupOrchestrator.setInput(SetUpMessages.ONE);
        setupOrchestrator.getMessageForQuizTitle();
        setupOrchestrator.setInput(title);
        setupOrchestrator.getMessageForQuizTitle();

        verify(quizOrchestrator).createQuiz("Quiz about cake.");
    }

    @Test
    public void shouldOutPutQuizIdUponQuizCreation() throws RemoteException, IllegalQuizException {
        String title = "Quiz about cake.";

        setupOrchestrator.setInput(SetUpMessages.ONE);
        setupOrchestrator.getMessageForQuizTitle();
        setupOrchestrator.setInput(title);
        setupOrchestrator.getMessageForQuizTitle();

        assertEquals("Your quiz ID is: 0\n", log.getLog());
    }

    @Test
    public void shouldBeAbleToAddAQuestionAndReturnRequestAnswerMessage() throws RemoteException, IllegalQuestionException, IllegalQuizException {
        String question = "How much cake can a smurf consume?";
        String actual = setupOrchestrator.getMessageForQuestion(question);

        verify(quizOrchestrator).addQuestion(question);

        assertEquals(SetUpMessages.REQUEST_ANSWER, actual);
    }

    @Test
    public void shouldDisplayAHelpfulMessageIfQuestionStringIsNull() throws IllegalQuestionException, RemoteException, IllegalQuizException {
        doThrow(new IllegalArgumentException("Helpful message")).when(quizOrchestrator).addQuestion(anyString());

        String question = "How much cake can a smurf consume?";
        setupOrchestrator.getMessageForQuestion(question);

        assertEquals("Helpful message\n", log.getLog());
    }

    @Test
    public void shouldDisplayAHelpfulMessageIfAnswerIsNull() throws RemoteException {
        String expected = setupOrchestrator.getMessageForAnswer(null);
        assertEquals(ExceptionMessages.EMPTY_ANSWER + "\n", log.getLog());
    }

    @Test
    public void shouldReturnRequestAnswerMessageIfAnswerIsNull() throws RemoteException {
        String actual = setupOrchestrator.getMessageForAnswer(null);
        assertEquals(SetUpMessages.REQUEST_ANSWER, actual);
    }

    @Test
    public void shouldReturnSaveOrAddMoreQuestionsMessageIfAnswerIsDone() throws RemoteException {
        String actual = setupOrchestrator.getMessageForAnswer(SetUpMessages.DONE);
        assertEquals(SetUpMessages.SAVE_OR_ADD_MORE_QUESTIONS, actual);
    }

    @Test
    public void shouldSetUserInputToAnswerIfAnyOtherStringBesidesDoneAndEmptyIsEntered() throws RemoteException {
        String userInput = "any other string!";
        setupOrchestrator.getMessageForAnswer(userInput);
        setupOrchestrator.setAnswer(userInput);

        String actual = setupOrchestrator.getAnswer();

        assertEquals(userInput, actual);
    }

    @Test
    public void shouldBeAbleToAddAnswerAndReturn() throws RemoteException, IllegalQuestionException {
        String userInput = "Y";
        String actual = setupOrchestrator.getMessageForYesOrNo(userInput);

        verify(quizOrchestrator).addAnswer(anyString(),anyBoolean());

        assertEquals(SetUpMessages.REQUEST_ANSWER, actual);
    }

    @Test
    public void shouldDisplayAHelpfulMessageIfUserInputIsNotYesOrNoAndShouldReturnCorrectMessage() throws RemoteException, IllegalQuestionException {
        String userInput = "fsf";
        String actual = setupOrchestrator.getMessageForYesOrNo(userInput);

        assertEquals(ExceptionMessages.INVALID_USER_INPUT + "\n", log.getLog());

        assertEquals(SetUpMessages.CORRECT_OR_INCORRECT_ANSWER_REQUEST, actual);
    }

    @Test
    public void shouldDisplayAHelpfulMessageIfUserInputIsNullAndReturnSaveOrAddMoreQuestionsMessage() {
        String userInput = null;
        String actual = setupOrchestrator.getMessageForSave(userInput);

        assertEquals(ExceptionMessages.INVALID_USER_INPUT + "\n",log.getLog());

        assertEquals(SetUpMessages.SAVE_OR_ADD_MORE_QUESTIONS, actual);
    }

    @Test
    public void shouldBeAbleToSaveAQuizAndReturnSaveSuccessMessage() throws RemoteException, IllegalQuizException {
        String userInput = SetUpMessages.SAVE;
        String actual = setupOrchestrator.getMessageForSave(userInput);

        verify(quizOrchestrator).save(quizOrchestrator.getQuiz());

        assertEquals(SetUpMessages.SAVE_SUCCESS, actual);
    }

    @Test
    public void shouldBeAbleToReturnRequestQuestionMessageIfUserInputIsY() {
        String userInput = "Y";
        String actual = setupOrchestrator.getMessageForSave(userInput);

        assertEquals(SetUpMessages.REQUEST_QUESTION, actual);
    }

    @Test
    public void shouldDisplayAHelpfulMessageIfUserInputIsNullAndReturnEnterQuizIdMessage() throws RemoteException, IllegalQuizException {
        String userInput = null;
        String actual = setupOrchestrator.getMessageForCloseQuiz(userInput);

        assertEquals(ExceptionMessages.INVALID_USER_INPUT + "\n", log.getLog());

        assertEquals(SetUpMessages.ENTER_QUIZ_ID_REQUEST, actual);
    }

    @Test
    public void shouldBeAbleToCloseQuizAndReturnQuizClosedSuccessMessage() throws RemoteException, IllegalQuizException {
        String userInput = "1";
        String actual = setupOrchestrator.getMessageForCloseQuiz(userInput);

        verify(quizOrchestrator).closeQuiz(anyInt());

        assertEquals(SetUpMessages.QUIZ_CLOSED_SUCCESS, actual);
    }

    @Test
    public void shouldDisplayAHelpfulMessageIfUserInputHasNoNumbersInTheString() throws RemoteException, IllegalQuizException {
        doThrow(new NumberFormatException(ExceptionMessages.NO_NUMBER_ENTERED)).when(quizOrchestrator).closeQuiz(anyInt());

        String userInput = "string with no numbers!";
        setupOrchestrator.getMessageForCloseQuiz(userInput);

        assertEquals(ExceptionMessages.NO_NUMBER_ENTERED + "\n", log.getLog());
    }

    @Test
    public void shouldBeAbleToReturnEnterQuizIdMessageIfUserInputHasNoNumbersInTheString() throws RemoteException, IllegalQuizException {
        String userInput = "string with no numbers!";
        String actual = setupOrchestrator.getMessageForCloseQuiz(userInput);

        assertEquals(SetUpMessages.ENTER_QUIZ_ID_REQUEST, actual);
    }

    @Test
    public void shouldDisplayAHelpfulMessageIfQuizDoesNotExist() throws RemoteException, IllegalQuizException {
        doThrow(new IllegalQuizException("Helpful message")).when(quizOrchestrator).closeQuiz(anyInt());

        String userInput = "5";
        setupOrchestrator.getMessageForCloseQuiz(userInput);

        verify(quizOrchestrator).closeQuiz(anyInt());

        assertEquals("Helpful message" + "\n", log.getLog());
    }
}
