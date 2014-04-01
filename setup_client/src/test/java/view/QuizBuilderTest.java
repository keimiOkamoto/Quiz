package view;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;


import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

public class QuizBuilderTest {
    private QuizBuilder quizBuilder;

    @Rule
    public final StandardOutputStreamLog output = new StandardOutputStreamLog();

    @Rule
    public final TextFromStandardInputStream userInput = emptyStandardInputStream();

    @Before
    public void buildUp() {
        quizBuilder = new QuizBuilderImpl();
    }

//    @Test
//    public void shouldDisplayWelcomeMessage() {
//        assertEquals("Welcome to the quiz creator. Would you like to create a quiz?\n" +
//                "Please press 'Y' to continue or 'EXIT' to exit.", output.getLog());
//    }
//
//    @Test
//    public void shouldGoInToCreateQuizModeIfUserPressesY() {
//        userInput.provideText("Y");
//        quizBuilder.launch();
//
//        assertEquals("Welcome to the quiz creator. Would you like to create a quiz?\n" +
//                "Please press 'Y' to continue or 'EXIT' to exit.\nEXIT\n", output.getLog());
//    }
//
//    @Test
//    public void shouldExitQuizIfUserEntersExit() {
//
//    }
}
