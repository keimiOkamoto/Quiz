package items;

public class AnswerImpl implements Answer {
    private String answerStr;
    private boolean answerType;

    public AnswerImpl(String answerStr, boolean answerType) {
        this.answerStr = answerStr;
        this.answerType = answerType;
    }

    @Override
    public String getAnswer() {
        return answerStr;
    }

    @Override
    public boolean getAnswerType() {
        return answerType;
    }
}
