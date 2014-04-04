package items;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnswerTest {

    @Test
    public void shouldBeAbleToGetAnswer() {
        String expected = "tea, Rex?";

        Answer answer = new AnswerImpl(expected, true);
        String actual= answer.getAnswer();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldBeAbleToGetAnswerType() {
        boolean expectedAnswerType = true;
        Answer answer = new AnswerImpl("tea, Rex?", expectedAnswerType);
        boolean actual = answer.getAnswerType();

        assertEquals(expectedAnswerType, actual);
    }
}
