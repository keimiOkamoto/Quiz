package models;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionTest {

    private Question question;

    @Before
    public void buildUp() {
        String questionString = "How do you ask a tyrannosaur out to lunch?";
        question = new QuestionImpl(questionString);
    }

    @Test
    public void shouldBeAbleToGetQuestion() {
        String expected = "How do you ask a tyrannosaur out to lunch?";
        String actual = question.getQuestion();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldBeAbleToAddToTheListOfAnswers() {
        Answer answer1 = mock(Answer.class);
        Set<Answer> expectedAnswersSet = new HashSet<>();
        expectedAnswersSet.add(answer1);

        question.add(answer1);
        Set<Answer> actualAnswersSet = question.getAnswers();

        assertEquals(expectedAnswersSet, actualAnswersSet);
    }

    @Test
    public void shouldBeAbleToCheckIfAnswerAlreadyExists() {
        String answerString = "Tea, Rex?";
        Answer answer1 = mock(Answer.class);

        question.add(answer1);
        when(answer1.getAnswer()).thenReturn(answerString);

        assertFalse(question.contains(answerString));
        assertTrue(question.contains("Tea, Barny?"));
    }
}
