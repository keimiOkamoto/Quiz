package controllers;

import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;
import items.Answer;
import items.Question;
import items.Quiz;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    public void shouldBeAbleToCreateAQuiz() throws IllegalQuizException {
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
    public void shouldBeAbleToReturnIdWhenCreatingAQuiz() throws IllegalQuizException {
        String title = "Animal items.Quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);

        int expected = 2;
        when(quiz.getId()).thenReturn(expected);

        int actual = quizOrchestrator.createQuiz(title);
        verify(quiz).getId();

        assertEquals(expected,actual);
    }

    @Test
    public void shouldHaveAppropriateMessageIfTitleIsNull() throws IllegalArgumentException, IllegalQuizException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Title is empty. Please enter a title with at least one character.");

        quizOrchestrator.createQuiz(null);
    }

    @Test
    public void shouldHaveAppropriateMessageIfTitleIsAnEmptyString() throws IllegalArgumentException, IllegalQuizException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Title is empty. Please enter a title with at least one character.");

        quizOrchestrator.createQuiz("    ");
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfDuplicateQuizNameExists() throws IllegalQuizException {
        String title = "A quiz about cat";
        when(server.valid(title)).thenReturn(false);

        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage("A quiz with the same name already exists. Please try again with another name.");

        quizOrchestrator.createQuiz(title);
    }

    /*
     * Start of addQuestion()
     */
    @Test
    public void shouldBeAbleToAddQuestionToQuizCreated() throws IllegalQuizException {
        String title = "A quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        String question1 = "What is the biggest cat?";
        when(server.createQuestion(anyString())).thenReturn(question);
        when(quiz.valid(question1)).thenReturn(true);
        quizOrchestrator.addQuestion(question1);
        verify(quiz).addQuestion(question);
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuizIsNull() throws IllegalQuizException {
        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage("items.Quiz does not exist. Please create a quiz and try again.");

        String question1 = "What is the biggest cat?";
        quizOrchestrator.addQuestion(question1);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsNull() throws IllegalQuizException {
        String title = "A quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("items.Question entered is empty. Please try again.");

        quizOrchestrator.addQuestion(null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsEmpty() throws IllegalQuizException {
        String title = "A quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("items.Question entered is empty. Please try again.");

        quizOrchestrator.addQuestion("    ");
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuestionAlreadyExists() throws IllegalQuizException {
        String title = "Animal quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        when(server.createQuestion(anyString())).thenReturn(question);

        String stringAnswer = "Lion";
        when(quiz.valid(stringAnswer)).thenReturn(false);

        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage("You have already entered that question. Please enter a different one.");

        quizOrchestrator.addQuestion(stringAnswer);
    }

    /*
     * Start of addAnswer()
     */
    @Test
    public void shouldBeAbleToAddAnswer() throws IllegalQuizException, IllegalQuestionException {
        String title = "Animal quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        String question1 = "What is the biggest cat?";
        when(quiz.valid(question1)).thenReturn(true);
        when(server.createQuestion(anyString())).thenReturn(question);
        quizOrchestrator.addQuestion(question1);
        verify(quiz).addQuestion(question);

        when(question.valid(anyString())).thenReturn(true);

        String stringAnswer = "Lion";
        when(server.createAnswer(stringAnswer)).thenReturn(answer);
        quizOrchestrator.addAnswer(stringAnswer);
        verify(question).addAnswer(answer);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsNullForAddAnswer() throws IllegalQuestionException, IllegalQuizException {
        String title = "A items.Quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        String questionString = "How many teeth does a lion have?";
        when(quiz.valid(questionString)).thenReturn(true);
        when(server.createQuestion(anyString())).thenReturn(question);
        quizOrchestrator.addQuestion(questionString);
        verify(quiz).addQuestion(question);

        when(question.valid(anyString())).thenReturn(true);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("items.Answer entered is empty. Please enter a valid answer.");

        quizOrchestrator.addAnswer(null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsEmptyForAddAnswer() throws IllegalQuestionException, IllegalQuizException {
        String title = "A items.Quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        String questionString = "How many teeth does a lion have?";
        when(server.createQuestion(anyString())).thenReturn(question);
        when(quiz.valid(questionString)).thenReturn(true);
        quizOrchestrator.addQuestion(questionString);
        verify(quiz).addQuestion(question);

        when(question.valid(anyString())).thenReturn(true);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("items.Answer entered is empty. Please enter a valid answer.");

        quizOrchestrator.addAnswer("       ");
    }

    @Test
    public void shouldThrowIllegalQuestionExceptionIfQuestionDoesNotExist() throws IllegalQuestionException {
        thrown.expect(IllegalQuestionException.class);
        thrown.expectMessage("items.Question doesn't exist. There must be a question to have an answer!");

        String answer = "lion";
        quizOrchestrator.addAnswer(answer);
    }

    @Test
    public void shouldThrowIllegalQuestionExceptionIfAnswerIsInvalid() throws IllegalQuizException, IllegalQuestionException {
        String title = "Animal quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        when(server.valid(anyString())).thenReturn(true);
        quizOrchestrator.createQuiz(title);
        verify(server).createQuiz(title);

        String question1 = "What is the biggest cat?";
        when(quiz.valid(anyString())).thenReturn(true);
        when(server.createQuestion(anyString())).thenReturn(question);
        quizOrchestrator.addQuestion(question1);
        verify(quiz).addQuestion(question);

        String stringAnswer = "Lion";
        when(question.valid(stringAnswer)).thenReturn(false);

        thrown.expect(IllegalQuestionException.class);
        thrown.expectMessage("You have already entered that answer. Please enter a different one.");

        quizOrchestrator.addAnswer(stringAnswer);
    }

    /*
     * Start of closeQuiz()
     */
    @Test
    public void shouldBeAbleToCloseQuizByQuotingId() throws IllegalQuizException {
        int id = 0;
        when(server.valid(anyInt())).thenReturn(true);
        quizOrchestrator.closeQuiz(id);
        verify(server).closeQuiz(anyInt());
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuizWithCorrespondingIdDoesNotExist() throws IllegalQuizException {
        int id = 0;
        when(server.valid(anyInt())).thenReturn(false);

        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage("A quiz with that ID does not exist. Please enter a valid ID.");

        quizOrchestrator.closeQuiz(id);
    }
}
