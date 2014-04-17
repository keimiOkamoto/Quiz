package view;

import models.Quiz;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class QuizMenuTest {

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();
    private QuizMenu quizMenu;
    private Quiz quiz1;
    private Quiz quiz2;

    @Before
    public void buildUp() {
        quiz1 = mock(Quiz.class);
        quiz2 = mock(Quiz.class);

        List<Quiz> quizList = Arrays.asList(quiz1, quiz2);
        quizMenu = new QuizMenuImpl(quizList);

    }

    @Test
    public void shouldBeAbleToSeeAListOfQuizzes() throws RemoteException {
        quizMenu.printListOfQuizzes();

        assertEquals("1. " + quiz1.getTitle() + "\n" + "2. " + quiz2.getTitle() + "\n", log.getLog());
    }

}
