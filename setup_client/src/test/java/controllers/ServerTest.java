package controllers;

import items.Answer;
import items.Question;
import items.Quiz;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    private Server server;
    private ItemsFactory itemsFactory;
    private ServerLink serverLink;

    @Before
    public void buildUp() {
        itemsFactory = mock(ItemsFactory.class);
        serverLink = mock(ServerLink.class);

        when(serverLink.getItemsFactory()).thenReturn(itemsFactory);
        server = new ServerImpl(serverLink);
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

    @Test
    public void shouldBeAbleToCreateAnswer() {
        String answer = "on the moon";
        Answer expectedAnswer = mock(Answer.class);

        when(itemsFactory.generateAnswer(anyString())).thenReturn(expectedAnswer);
        Answer actualQuestion = server.createAnswer(answer);

        assertEquals(expectedAnswer, actualQuestion);
    }

    @Test
    public void shouldBeAbleToCheckForValidQuizTitle() {
        String title = "Quiz about noodles";

        when(serverLink.titleIsValid(anyString())).thenReturn(true);
        assertTrue(server.valid(title));
    }

    @Test
    public void shouldBeAbleToCloseQuiz() {

    }
}
