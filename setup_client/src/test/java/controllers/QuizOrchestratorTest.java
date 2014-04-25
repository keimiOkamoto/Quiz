package controllers;

import constants.ExceptionMessages;
import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;
import models.Answer;
import models.Question;
import models.Quiz;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuizOrchestratorTest {
    private QuizOrchestrator quizOrchestrator;
    private Quiz quiz;
    private Server server;
    private Question question;
    private Answer answer;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void buildUp() {
        quiz = mock(Quiz.class);
        server = mock(Server.class);
        question = mock(Question.class);
        answer = mock(Answer.class);

        quizOrchestrator = new QuizOrchestratorImpl(server);
    }

    /*
     * SetupStart of createQuiz()
     */
    @Test
    public void shouldBeAbleToCreateAQuiz() throws IllegalQuizException, RemoteException {
        String expected = "The colour quiz!";
        int id = 0;
        when(server.createQuiz(expected)).thenReturn(id);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(expected);

        verify(server).createQuiz(expected);

        when(quiz.getTitle()).thenReturn(expected);
        String actual = quizOrchestrator.getTitle();

        assertEquals(expected, actual);

        int id1 = 1;
        expected = "The animal quiz!";
        when(server.createQuiz(expected)).thenReturn(id1);
        quizOrchestrator.createQuiz(expected);

        verify(server).createQuiz(expected);

        when(quiz.getTitle()).thenReturn(expected);
        actual = quizOrchestrator.getTitle();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldBeAbleToReturnIdWhenCreatingAQuiz() throws IllegalQuizException, RemoteException {
        String title = "Animal Quiz";
        int id = 5;
        when(server.createQuiz(anyString())).thenReturn(id);
        when(server.valid(anyString())).thenReturn(true);

        when(quiz.getId()).thenReturn(id);

        int actual = quizOrchestrator.createQuiz(title);

        assertEquals(id,actual);
    }

    @Test
    public void shouldHaveAppropriateMessageIfTitleIsNull() throws IllegalArgumentException, IllegalQuizException, RemoteException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionMessages.EMPTY_TITLE);

        quizOrchestrator.createQuiz(null);
    }

    @Test
    public void shouldHaveAppropriateMessageIfTitleIsAnEmptyString() throws IllegalArgumentException, IllegalQuizException, RemoteException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionMessages.EMPTY_TITLE);

        quizOrchestrator.createQuiz("    ");
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfDuplicateQuizNameExists() throws IllegalQuizException, RemoteException {
        String title = "A quiz about cat";
        when(server.valid(title)).thenReturn(false);

        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage(ExceptionMessages.DUPLICATE_QUIZ);

        quizOrchestrator.createQuiz(title);
    }

    /*
     * SetupStart of addQuestion()
     */
    @Test
    public void shouldBeAbleToAddQuestionToQuizCreated() throws IllegalQuizException, RemoteException, IllegalQuestionException {
        String question1 = "What is the biggest cat?";
        quizOrchestrator.addQuestion(question1);
        verify(server).addQuestionToQuiz(question1);
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuizIsNull() throws IllegalQuizException, RemoteException, IllegalQuestionException {
        when(server.isQuizNull()).thenReturn(true);
        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage(ExceptionMessages.NO_QUIZ_EXISTS);

        String question1 = "What is the biggest cat?";
        quizOrchestrator.addQuestion(question1);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsNull() throws IllegalQuizException, RemoteException, IllegalQuestionException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionMessages.EMPTY_QUESTION);

        quizOrchestrator.addQuestion(null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsEmpty() throws IllegalQuizException, RemoteException, IllegalQuestionException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionMessages.EMPTY_QUESTION);

        quizOrchestrator.addQuestion("    ");
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuestionAlreadyExists() throws IllegalQuizException, RemoteException, IllegalQuestionException {
        when(server.quizContains(anyString())).thenReturn(true);

        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage(ExceptionMessages.DUPLICATE_QUESTION);

        String stringAnswer = "Lion";
        quizOrchestrator.addQuestion(stringAnswer);
    }

    /*
     * SetupStart of add(question)
     */
    @Test
    public void shouldBeAbleToAddAnswer() throws IllegalQuizException, IllegalQuestionException, RemoteException {
        String question1 = "What is the biggest cat?";
        quizOrchestrator.addAnswer(question1, true);

        verify(server).addToQuestion(anyString(), anyBoolean());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsNullForAddAnswer() throws IllegalQuestionException, IllegalQuizException, RemoteException {
        when(server.isQuestionNull()).thenReturn(true);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionMessages.EMPTY_ANSWER);

        quizOrchestrator.addAnswer(null, true);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsEmptyForAddAnswer() throws IllegalQuestionException, IllegalQuizException, RemoteException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionMessages.EMPTY_ANSWER);

        quizOrchestrator.addAnswer("       ", true);
    }

    @Test
    public void shouldThrowIllegalQuestionExceptionIfQuestionDoesNotExist() throws IllegalQuestionException, RemoteException {
        when(server.isQuestionNull()).thenReturn(true);
        thrown.expect(IllegalQuestionException.class);
        thrown.expectMessage(ExceptionMessages.NO_QUESTION_EXISTS);

        quizOrchestrator.addAnswer("Any answer", true);
    }

    @Test
    public void shouldThrowIllegalQuestionExceptionIfDuplicateAnswerIsEntered() throws IllegalQuizException, IllegalQuestionException, RemoteException {
        when(server.questionContains(anyString())).thenReturn(true);
        thrown.expect(IllegalQuestionException.class);
        thrown.expectMessage(ExceptionMessages.DUPLICATE_ANSWER);

        quizOrchestrator.addAnswer("Some duplicate answer", true);
    }

    /*
     * SetupStart of closeQuiz(int id)
     */
    @Test
    public void shouldBeAbleToCloseQuizByQuotingId() throws IllegalQuizException, RemoteException {
        int id = 0;
        when(server.valid(anyInt())).thenReturn(true);
        quizOrchestrator.closeQuiz(id);
        verify(server).closeQuiz(anyInt());
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuizWithCorrespondingIdDoesNotExist() throws IllegalQuizException, RemoteException {
        int id = 0;
        when(server.valid(anyInt())).thenReturn(false);

        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage(ExceptionMessages.NO_QUIZ_WITH_ID_EXISTS);

        quizOrchestrator.closeQuiz(id);
    }

    /*
     * Starts of save(Quiz quiz)
     */
    @Test
    public void shouldBeAbleToSaveToQuizToServer() throws IllegalQuizException, RemoteException {
        quizOrchestrator.save();
        verify(server).save();
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuizDoesNotExist() throws IllegalQuizException, RemoteException {
        when(server.isQuizNull()).thenReturn(true);
        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage(ExceptionMessages.NO_QUIZ_TO_SAVE);

        quizOrchestrator.save();
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuizDoesNotContainAQuestion() throws IllegalQuizException, RemoteException {
        when(server.isQuizEmpty()).thenReturn(true);
        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage(ExceptionMessages.NO_QUESTIONS_CANNOT_SAVE);

        when(quiz.isEmpty()).thenReturn(true);
        quizOrchestrator.save();
    }
}
