package items;

import java.util.HashMap;
import java.util.Map;

public class ClosedQuizContainerImpl implements ClosedQuizContainer {
    private Map<Integer, Quiz> closedQuizMap = new HashMap<>();

    @Override
    public void add(Quiz closedQuiz) {
        closedQuizMap.put(closedQuiz.getId(), closedQuiz);
    }

    @Override
    public Quiz getQuiz(int id) {
        return closedQuizMap.get(id);
    }
}
