package exceptions;

import exceptions.IllegalQuizException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IllegalQuizExceptionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldHaveAppropriateMessage() throws IllegalQuizException {
        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage("models.Quiz does not exist. Please create a quiz and try again.");

        throw new IllegalQuizException("models.Quiz does not exist. Please create a quiz and try again.");
    }
}
