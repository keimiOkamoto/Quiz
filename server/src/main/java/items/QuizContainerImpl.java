package items;

public class QuizContainerImpl implements QuizContainer {
    @Override
    public boolean hasValid(String title) {
        return false;
    }

    @Override
    public boolean hasValid(int id) {
        return false;
    }

    @Override
    public void closeQuizWith(int id) {

    }
}
