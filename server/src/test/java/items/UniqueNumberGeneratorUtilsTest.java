/**
 * Test for unique number generator. As the meeting and contact
 * both have unique numbers to avoid code repetition a tool package
 * was made.
 */
package items;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;

public class UniqueNumberGeneratorUtilsTest {

    /**
     * Test to make sure the method is returning a
     * unique number.
     */
    @Test
    public void shouldBeAbleToGenerateUniqueNumber() {
        UniqueNumberGeneratorUtils aUniqueNumberGeneratorUtils = UniqueNumberGeneratorUtilsImpl.getInstance();
        int actual = aUniqueNumberGeneratorUtils.getUniqueNumber();
        int expected = 0;

        assertEquals(expected, actual);

        actual = aUniqueNumberGeneratorUtils.getUniqueNumber();
        expected = 1;

        assertEquals(expected, actual);
    }
}
