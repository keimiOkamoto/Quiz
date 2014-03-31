import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IllegalQuizExceptionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldHaveAppropriateMessage() throws IllegalQuizException {
        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage("Quiz does not exist. Please create a quiz and try again.");

        throw new IllegalQuizException("Quiz does not exist. Please create a quiz and try again.");
    }
}
