package controllers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IllegalGameExceptionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void ShouldThrowIllegalGameException() throws IllegalGameException {
        thrown.expect(IllegalGameException.class);
        thrown.expectMessage("There are no Quizzes available.");

        throw new IllegalGameException("There are no Quizzes available.");
    }
}
