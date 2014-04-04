package items;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemsFactoryTest {

    private ItemsFactory itemsFactory;
    private UniqueNumberGeneratorUtils uniqueNumberGeneratorUtils;

    @Before
    public void buildUp() {
        uniqueNumberGeneratorUtils = mock(UniqueNumberGeneratorUtils.class);
        itemsFactory = new ItemsFactoryImpl(uniqueNumberGeneratorUtils);
    }

    @Test
    public void shouldBeAbleToGenerateQuizWith() {
        int id = 5;
        when(uniqueNumberGeneratorUtils.getUniqueNumber()).thenReturn(id);
        Quiz actual = itemsFactory.generateQuiz("some quiz.");
        int actualId = actual.getId();

        assertEquals(id, actualId);
    }
}
