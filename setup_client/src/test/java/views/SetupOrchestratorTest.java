package views;

import constants.ExceptionMessages;
import controllers.QuizOrchestrator;
import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SetupOrchestratorTest {
    /*
    * set up person sees welcome message and menu.
    *
    * CONSOLE: "WELCOME TO THE QUIZ SETUP"
    * CONSOLE: "What would you like to do?"
    * CONSOLE: "1.Create a quiz"          "2.Close a quiz.      EXIT: To exit the program."
    * CONSOLE: "Enter 1 to create a quiz or 2 to close a quiz
    *           and 'EXIT' to terminate the program at any point."
    *
    * USER INPUTS: "1"
    *
    * CONSOLE: "Would you like to create a quiz?
    * Press 'Y' or 'EXIT' to end the setup process at any point. "
    *
    * Y:
    * CONSOLE: "Please enter the title of your quiz: "
    * USER INPUTS: ""
    *
    * CONSOLE: "Please enter a question."
    * USER INPUTS: ""
    * CONSOLE: "Please enter the correct answer."
    * USER INPUTS: ""
    * CONSOLE: "Please enter a distraction answer."
    * USER INPUTS: ""
    * CONSOLE: "Would you like to enter another distraction answer? 'Y' for yes 'N' for no."
    * USER INPUTS: "N"
    *
    * CONSOLE: "Please enter a question."
    * USER INPUTS: "EXIT"
    *
    * CONSOLE: "Quiz has been saved."
    *
    * CONSOLE: "What would you like to do?"
    * CONSOLE: "1.Create a quiz"          "2.Close a quiz.      EXIT: To exit the program."
    *
    */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    private SetupOrchestrator setupView;
    private UserInput userInput;
    private QuizOrchestrator quizOrchestrator;

    @Before
    public void buildUp() {
        quizOrchestrator = mock(QuizOrchestrator.class);
        userInput = mock(UserInput.class);
        setupView = new SetupOrchestratorImpl(userInput);
    }

    @Test
    public void shouldBeAbleToSeeWelcomeMessageWithOptions() {
        setupView.startMessage();
        assertEquals("❤ ☆ ★ ☆ ★ Welcome to the Quiz Game Setup! ★ ☆ ★ ☆ ❤\n\nWhat would you like to do?\n1.Create a quiz.          2.Close a quiz.      EXIT:To exit the program.\nEnter '1' to create a quiz or '2' to close a quiz and 'EXIT' to terminate the program at any point.\n", log.getLog());
    }

    @Test
    public void shouldBeAbleToSelectOption1() throws InterruptedException, IllegalQuizException, IllegalQuestionException, IllegalOptionException {
        when(userInput.type()).thenReturn("1");
        setupView.selectOption();

        assertEquals("Please enter the title of your quiz: \n", log.getLog());
    }

    @Test
    public void shouldBeAbleToSelectOption2() throws InterruptedException, IllegalQuizException, IllegalQuestionException, IllegalOptionException {
        when(userInput.type()).thenReturn("2");
        setupView.selectOption();

        assertEquals("Please enter the ID of the quiz you would like to close.\n", log.getLog());
    }

    @Test
    public void shouldBeAbleToSelectOptionExit() throws InterruptedException, IllegalQuizException, IllegalQuestionException, IllegalOptionException {
        when(userInput.type()).thenReturn("EXIT");

        setupView.selectOption();
        Thread.sleep(100);

        assertEquals("System exiting.\n", log.getLog());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfInValidCommandIsEntered() throws IllegalQuizException, IllegalQuestionException, IllegalOptionException {
        when(userInput.type()).thenReturn("asdasdsa");

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionMessages.INVALID_USER_INPUT);

        setupView.selectOption();
    }

    @Test
    public void shouldBeAbleToCreateATitleForAQuiz() throws InterruptedException, IllegalQuizException, IllegalQuestionException, IllegalOptionException {
        String quiz = "Quiz";
        when(userInput.type()).thenReturn("1", quiz);
        setupView.selectOption();

        assertEquals("Please enter the title of your quiz: \n", log.getLog());

        verify(quizOrchestrator).createQuiz(quiz);
    }
}
