package factories;

import models.Answer;
import models.Question;
import models.Quiz;
import utils.UniqueNumberGeneratorUtils;
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
    public void shouldBeAbleToGenerateQuizWithId() {
        int id = 5;
        when(uniqueNumberGeneratorUtils.getUniqueNumber()).thenReturn(id);
        Quiz actual = itemsFactory.generateQuiz("some quiz.");
        int actualId = actual.getId();

        assertEquals(id, actualId);
    }

    @Test
    public void shouldBeAbleToGenerateQuestion() {
        String questionStr = "When you choke a smurf, what color does it turn?";
        itemsFactory.generateQuestion(questionStr);

        Question actual = itemsFactory.generateQuestion(questionStr);
        String actualStr = actual.getQuestion();

        assertEquals(questionStr, actualStr);
    }

    @Test
    public void shouldBeAbleToGenerateAnswer() {
        String answerStr = "No one knows.";
        itemsFactory.generateAnswer(answerStr, false);

        Answer actual = itemsFactory.generateAnswer(answerStr, false);
        String actualStr = actual.getAnswer();

        assertEquals(answerStr, actualStr);
    }
}
