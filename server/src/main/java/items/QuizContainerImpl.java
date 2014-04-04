package items;

import com.google.inject.Singleton;

import java.util.Collection;
import java.util.TreeMap;

@Singleton
public class QuizContainerImpl implements QuizContainer {
    private TreeMap<Integer, Quiz> quizTreeMap = new TreeMap<>();
    private ClosedQuizContainer closedQuizContainer;

    public QuizContainerImpl(ClosedQuizContainer closedQuizContainer) {
        this.closedQuizContainer = closedQuizContainer;
    }

    @Override
    public boolean contains(String title) {
        Collection<Quiz> quizCollection = quizTreeMap.values();
        boolean result = false;

        for (Quiz quiz : quizCollection) {
            if (!quiz.getTitle().equals(title)) result = true;
        }
        return result;
    }

    @Override
    public boolean hasValid(int id) {
        return false;
    }

    @Override
    public void closeQuizWith(int id) {

    }

    @Override
    public void save(Quiz quiz) {
        quizTreeMap.put(quiz.getId(), quiz);
    }

    @Override
    public Quiz getQuizBy(int id) {
        return quizTreeMap.get(id);
    }
}
