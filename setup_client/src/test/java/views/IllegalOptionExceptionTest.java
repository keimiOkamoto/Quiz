package views;

import constants.ExceptionMessages;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IllegalOptionExceptionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldHaveAppropriateMessage() throws IllegalOptionException {
        thrown.expect(IllegalOptionException.class);
        thrown.expectMessage(ExceptionMessages.INVALID_USER_INPUT);

        throw new IllegalOptionException(ExceptionMessages.INVALID_USER_INPUT);
    }
}
