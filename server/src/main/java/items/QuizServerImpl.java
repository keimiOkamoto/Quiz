package items;

public class QuizServerImpl implements QuizServer {
    private ItemsFactory itemsFactory;
    private QuizContainer quizContainer;

    public QuizServerImpl(ItemsFactory itemsFactory, QuizContainer quizContainer) {
        this.itemsFactory = itemsFactory;
        this.quizContainer = quizContainer;
    }

    @Override
    public boolean titleIsValid(String title) {
        return quizContainer.contains(title);
    }

    @Override
    public boolean iDIsValid(int id) {
        return quizContainer.contains(id);
    }

    @Override
    public void endQuiz(int id) {
        quizContainer.closeQuizWith(id);
    }

    @Override
    public ItemsFactory getItemsFactory() {
        return itemsFactory;
    }

    @Override
    public void save(Quiz quiz) {
        quizContainer.save(quiz);
    }
}
