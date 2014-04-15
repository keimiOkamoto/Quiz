/**
 * Test for unique number generator. As the meeting and contact
 * both have unique numbers to avoid code repetition a tool package
 * was made.
 */
package utils;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class UniqueNumberGeneratorUtilsTest {

    /**
     * Test to make sure the method is returning a
     * unique number.
     */
    @Test
    public void shouldBeAbleToGenerateUniqueNumber() {
        DiskWriter diskWriter = mock(DiskWriter.class);
        UniqueNumberGeneratorUtils aUniqueNumberGeneratorUtils = new UniqueNumberGeneratorUtilsImpl(diskWriter);
        int actual = aUniqueNumberGeneratorUtils.getUniqueNumber();
        int expected = 0;

        assertEquals(expected, actual);

        actual = aUniqueNumberGeneratorUtils.getUniqueNumber();
        expected = 1;

        assertEquals(expected, actual);
    }
}
