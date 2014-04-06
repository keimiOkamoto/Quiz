package models;

import java.util.HashSet;
import java.util.Set;

public class QuizImpl implements Quiz {
    private int id;
    private int score;
    private Set<Question> questionSet = new HashSet<>();
    private String title;


    public QuizImpl(int id, String title) {
        this.id = id;
        this.title = title;
        score = 0;
    }

    @Override
    public void addQuestion(Question question) {
        questionSet.add(question);
    }

    @Override
    public boolean contains(String question) {
        boolean result = false;
        for (Question value : questionSet) {
            if (!value.getQuestion().equals(question)) result = true;
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return questionSet.isEmpty();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Set<Question> getQuestions() {
        return questionSet;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void incrementScore() {
        score++;
    }
}
