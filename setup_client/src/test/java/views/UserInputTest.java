package views;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class UserInputTest {
    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    private UserInput userInput;

//    @Before
//    public void buildUp() {
//        userInput = new UserInputImpl();
//    }
//
//    @Test
//    public void shouldBeAbleToGetUserInput() throws InterruptedException {
//        systemInMock.provideText("Hello");
//
//        String input = userInput.type();
//        System.out.print("Hello");
//
//        Thread.sleep(100);
//
//        assertEquals(input, log.getLog());
//    }

}
