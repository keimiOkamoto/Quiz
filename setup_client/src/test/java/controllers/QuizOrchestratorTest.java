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
     * Start of createQuiz()
     */
    @Test
    public void shouldBeAbleToCreateAQuiz() throws IllegalQuizException, RemoteException {
        String expected = "The colour quiz!";
        when(server.createQuiz(expected)).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(expected);

        verify(server).createQuiz(expected);

        when(quiz.getTitle()).thenReturn(expected);
        String actual = quizOrchestrator.getTitle();

        assertEquals(expected, actual);

        expected = "The animal quiz!";
        when(server.createQuiz(expected)).thenReturn(quiz);
        quizOrchestrator.createQuiz(expected);

        verify(server).createQuiz(expected);

        when(quiz.getTitle()).thenReturn(expected);
        actual = quizOrchestrator.getTitle();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldBeAbleToReturnIdWhenCreatingAQuiz() throws IllegalQuizException, RemoteException {
        String title = "Animal Quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);

        int expected = 2;
        when(quiz.getId()).thenReturn(expected);

        int actual = quizOrchestrator.createQuiz(title);
        verify(quiz).getId();

        assertEquals(expected,actual);
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
     * Start of addQuestion()
     */
    @Test
    public void shouldBeAbleToAddQuestionToQuizCreated() throws IllegalQuizException, RemoteException {
        String title = "A quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        String question1 = "What is the biggest cat?";
        when(server.createQuestion(anyString())).thenReturn(question);
        when(quiz.contains(question1)).thenReturn(true);
        quizOrchestrator.addQuestion(question1);
        verify(quiz).addQuestion(question);
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuizIsNull() throws IllegalQuizException, RemoteException {
        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage(ExceptionMessages.NO_QUIZ_EXISTS);

        String question1 = "What is the biggest cat?";
        quizOrchestrator.addQuestion(question1);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsNull() throws IllegalQuizException, RemoteException {
        String title = "A quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionMessages.EMPTY_QUESTION);

        quizOrchestrator.addQuestion(null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsEmpty() throws IllegalQuizException, RemoteException {
        String title = "A quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Question entered is empty. Please try again.");

        quizOrchestrator.addQuestion("    ");
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuestionAlreadyExists() throws IllegalQuizException, RemoteException {
        String title = "Animal quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        when(server.createQuestion(anyString())).thenReturn(question);

        String stringAnswer = "Lion";
        when(quiz.contains(stringAnswer)).thenReturn(false);

        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage(ExceptionMessages.DUPLICATE_QUESTION);

        quizOrchestrator.addQuestion(stringAnswer);
    }

    /*
     * Start of add(question)
     */
    @Test
    public void shouldBeAbleToAddAnswer() throws IllegalQuizException, IllegalQuestionException, RemoteException {
        String title = "Animal quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        String question1 = "What is the biggest cat?";
        when(quiz.contains(question1)).thenReturn(true);
        when(server.createQuestion(anyString())).thenReturn(question);
        quizOrchestrator.addQuestion(question1);
        verify(quiz).addQuestion(question);

        when(question.contains(anyString())).thenReturn(true);

        String stringAnswer = "Lion";
        when(server.createAnswer(stringAnswer, true)).thenReturn(answer);
        quizOrchestrator.addAnswer(stringAnswer, true);
        verify(question).add(answer);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsNullForAddAnswer() throws IllegalQuestionException, IllegalQuizException, RemoteException {
        String title = "A Quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        String questionString = "How many teeth does a lion have?";
        when(quiz.contains(questionString)).thenReturn(true);
        when(server.createQuestion(anyString())).thenReturn(question);
        quizOrchestrator.addQuestion(questionString);
        verify(quiz).addQuestion(question);

        when(question.contains(anyString())).thenReturn(true);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionMessages.EMPTY_ANSWER);

        quizOrchestrator.addAnswer(null, true);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsEmptyForAddAnswer() throws IllegalQuestionException, IllegalQuizException, RemoteException {
        String title = "A models.Quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        String questionString = "How many teeth does a lion have?";
        when(server.createQuestion(anyString())).thenReturn(question);
        when(quiz.contains(questionString)).thenReturn(true);
        quizOrchestrator.addQuestion(questionString);
        verify(quiz).addQuestion(question);

        when(question.contains(anyString())).thenReturn(true);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionMessages.EMPTY_ANSWER);

        quizOrchestrator.addAnswer("       ", true);
    }

    @Test
    public void shouldThrowIllegalQuestionExceptionIfQuestionDoesNotExist() throws IllegalQuestionException {
        thrown.expect(IllegalQuestionException.class);
        thrown.expectMessage(ExceptionMessages.NO_QUESTION_EXISTS);

        String answer = "lion";
        quizOrchestrator.addAnswer(answer, true);
    }

    @Test
    public void shouldThrowIllegalQuestionExceptionIfAnswerIsInvalid() throws IllegalQuizException, IllegalQuestionException, RemoteException {
        String title = "Animal quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        String question1 = "What is the biggest cat?";
        when(quiz.contains(anyString())).thenReturn(true);
        when(server.createQuestion(anyString())).thenReturn(question);
        quizOrchestrator.addQuestion(question1);
        verify(quiz).addQuestion(question);

        String stringAnswer = "Lion";
        when(question.contains(stringAnswer)).thenReturn(false);

        thrown.expect(IllegalQuestionException.class);
        thrown.expectMessage(ExceptionMessages.DUPLICATE_ANSWER);

        quizOrchestrator.addAnswer(stringAnswer, true);
    }

    /*
     * Start of closeQuiz(int id)
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
        quizOrchestrator.save(quiz);
        verify(server).save(quiz);
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuizDoesNotExist() throws IllegalQuizException, RemoteException {
        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage(ExceptionMessages.NO_QUIZ_TO_SAVE);

        quizOrchestrator.save(null);
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuizDoesNotContainAQuestion() throws IllegalQuizException, RemoteException {
        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage(ExceptionMessages.NO_QUESTIONS_CANNOT_SAVE);

        when(quiz.isEmpty()).thenReturn(true);
        quizOrchestrator.save(quiz);
    }
}
