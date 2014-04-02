package items;

public class QuizServerImpl implements QuizServer {
    private ItemsFactory itemsFactory;

    public QuizServerImpl(ItemsFactory itemsFactory) {
        this.itemsFactory = itemsFactory;
    }

    @Override
    public ItemsFactory getItemsFactory() {
        return itemsFactory;
    }

    @Override
    public boolean titleIsValid(String title) {
        return false;
    }

    @Override
    public void endQuiz(int id) {

    }

    @Override
    public boolean iDIsValid(int id) {
        return false;
    }
}
