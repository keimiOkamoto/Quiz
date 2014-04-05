package models;

import java.util.HashSet;
import java.util.Set;

public class QuestionImpl implements Question {
    private Set<Answer> answerSet = new HashSet<>();
    private String question;

    public QuestionImpl(String question) {
        this.question = question;
    }

    @Override
    public void add(Answer answer) {
        answerSet.add(answer);
    }

    @Override
    public boolean contains(String answer) {
        boolean result = false;
        for (Answer value : answerSet) {
            if (!value.getAnswer().equals(answer)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public Set<Answer> getAnswers() {
        return answerSet;
    }
}