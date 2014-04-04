package items;

import com.google.inject.Singleton;

@Singleton
public class ItemsFactoryImpl implements ItemsFactory {
    private UniqueNumberGeneratorUtils uniqueNumberGeneratorUtils;

    public ItemsFactoryImpl(UniqueNumberGeneratorUtils uniqueNumberGeneratorUtils) {
        this.uniqueNumberGeneratorUtils = uniqueNumberGeneratorUtils;
    }

    @Override
    public Quiz generateQuiz(String title) {
        return new QuizImpl(uniqueNumberGeneratorUtils.getUniqueNumber(), title);
    }

    @Override
    public Question generateQuestion(String question) {
        return new QuestionImpl(question);
    }

    @Override
    public Answer generateAnswer(String answer, boolean answerType) {
        return null;
    }
}
