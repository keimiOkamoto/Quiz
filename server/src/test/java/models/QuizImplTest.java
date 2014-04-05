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

public class QuizImplTest {

    private Quiz quiz;

    @Before
    public void buildUp() {
        int id = 5;
        quiz = new QuizImpl(id, "Quiz about dinosaurs.");
    }

    @Test
    public void shouldBeAbleToAddAQuestion() {
        Question question = mock(Question.class);
        quiz.addQuestion(question);
        Set<Question> actualQuestionSet = quiz.getQuestions();


        Set<Question> expectedQuestionSet = new HashSet<>();
        expectedQuestionSet.add(question);

        assertEquals(expectedQuestionSet, actualQuestionSet);
    }

    @Test
    public void shouldBeAbleToCheckIfQuizContainsASpecificQuestion() {
        Question question = mock(Question.class);
        String question1 = "How do you ask a tyrannosaur out to lunch?";

        when(question.getQuestion()).thenReturn(question1);
        quiz.addQuestion(question);

        assertFalse(quiz.contains(question1));
        assertTrue(quiz.contains("How do you ask a dolphin to lunch?"));
    }

    @Test
    public void shouldBeAbleToCheckIfQuizIsEmpty() {
        Question question = mock(Question.class);
        quiz.addQuestion(question);
        boolean actual = quiz.isEmpty();

        assertFalse(actual);
    }

    @Test
    public void shouldBeAbleToGetQuizTitle() {
        String expected = "Quiz about dinosaurs.";
        String actual = quiz.getTitle();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldBeAbleToGetQuizId() {
        int expectedId = 5;
        int actualId = quiz.getId();

        assertEquals(expectedId, actualId);
    }
}
