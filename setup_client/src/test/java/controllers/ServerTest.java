package controllers;

import items.Question;
import items.Quiz;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    private Server server;
    private ItemsFactory itemsFactory;

    @Before
    public void buildUp() {
        itemsFactory = mock(ItemsFactory.class);
        server = new ServerImpl(itemsFactory);
    }

    @Test
    public void shouldBeAbleToCreateQuizWithAValidTitle() {
        String title = "Quiz about cake.";
        Quiz expectedQuiz = mock(Quiz.class);

        when(itemsFactory.generateQuiz(anyString())).thenReturn(expectedQuiz);
        Quiz actualQuiz = server.createQuiz(title);

        assertEquals(expectedQuiz, actualQuiz);
    }

    @Test
    public void shouldBeAbleToCreateQuestion() {
        String question = "Where is the treasure berried?";
        Question expectedQuestion = mock(Question.class);

        when(itemsFactory.generateQuestion(anyString())).thenReturn(expectedQuestion);
        Question actualQuestion = server.createQuestion(question);

        assertEquals(expectedQuestion, actualQuestion);
    }
}
