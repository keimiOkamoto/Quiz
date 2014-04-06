package exceptions;

import controllers.exceptions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IllegalGameExceptionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void ShouldThrowIllegalGameException() throws exceptions.IllegalGameException {
        thrown.expect(exceptions.IllegalGameException.class);
        thrown.expectMessage("There are no Quizzes available.");

        throw new exceptions.IllegalGameException("There are no Quizzes available.");
    }
}
