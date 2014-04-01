package view;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

public class QuizBuilderTest {
    private QuizBuilder quizBuilder;

    @Rule
    public final StandardOutputStreamLog output = new StandardOutputStreamLog();

    @Rule
    public final TextFromStandardInputStream userInput = emptyStandardInputStream();
    private InputStream stdin;

    @Before
    public void buildUp() {
        quizBuilder = new QuizBuilderImpl();

        stdin = System.in;
    }

    @After
    public void tearDown() {
        System.setIn(stdin);
    }

    @Test
    public void shouldDisplayWelcomeMessage() {
        assertEquals("Welcome to the quiz creator. Would you like to create a quiz?\n" +
                "Please press Y to continue or N to exit.\n", output.getLog());
    }

    @Test
    public void shouldGoInToCreateQuizModeIfUserPressesEnter() {
        quizBuilder.launch();
        System.setIn(new ByteArrayInputStream("y\n".getBytes()));

        assertEquals("Welcome to the quiz creator. Would you like to create a quiz?\n" +
                "Please press Y to continue or N to exit.\nY\n", output.getLog());
    }
}
