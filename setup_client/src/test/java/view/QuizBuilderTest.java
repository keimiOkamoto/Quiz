package view;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class QuizBuilderTest {
    private QuizBuilder quizBuilder;

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Before
    public void buildUp() {
        quizBuilder = new QuizBuilderImpl();
        quizBuilder.launch();
    }

    @Test
    public void shouldDisplayWelcomeMessage() {
        assertEquals("Welcome to the quiz creator. Would you like to create a quiz?\n" +
                "Please press Y to continue or N to exit.\n", log.getLog());
    }

    @Test
    public void shouldGoInToCreateQuizModeIfUserPressesEnter() {
        systemInMock.provideText("Y");
    }
}
