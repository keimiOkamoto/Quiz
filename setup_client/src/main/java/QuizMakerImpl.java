
public class QuizMakerImpl implements QuizMaker {
    private Server server;
    private Quiz quiz;

    public QuizMakerImpl(Server server) {
        this.server = server;
    }

    @Override
    public int createQuiz(String title) {
        if (title == null || title.trim().equals("")) throw new IllegalArgumentException("Title is empty. Please enter a title with at least one character.");
        quiz = server.createQuiz(title.trim());
        return 0;
    }

    @Override
    public String getTitle() {
        return quiz.getTitle();
    }

    @Override
    public void addQuestion(Question question) {
        quiz.addQuestion(question);
    }
}
