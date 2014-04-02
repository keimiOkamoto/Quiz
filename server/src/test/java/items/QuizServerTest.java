package items;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class QuizServerTest {

    private QuizServer quizServer;
    private ItemsFactory itemsFactory;

    @Before
    public void buildUp() {
        itemsFactory = mock(ItemsFactory.class);
        quizServer = new QuizServerImpl(itemsFactory);
    }
    @Test
    public void shouldBeAbleToGetItemsFactory() {
        ItemsFactory actualItemsFactory = quizServer.getItemsFactory();
        assertEquals(itemsFactory, actualItemsFactory);
    }
}
