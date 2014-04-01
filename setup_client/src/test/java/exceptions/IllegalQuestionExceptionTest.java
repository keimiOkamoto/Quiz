package exceptions;

import exceptions.IllegalQuestionException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IllegalQuestionExceptionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowIllegalQuestionException() throws IllegalQuestionException {
        thrown.expect(IllegalQuestionException.class);
        thrown.expectMessage("items.Quiz does not exist. Please create a quiz and try again.");

        throw new IllegalQuestionException("items.Quiz does not exist. Please create a quiz and try again.");
    }
}
