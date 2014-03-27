
public class QuizMakerImpl implements QuizMaker {
    private Server server;
    private Quiz quiz;

    public QuizMakerImpl(Server server) {
        this.server = server;
    }

    @Override
    public int createQuiz(String title) {
        quiz = server.createQuiz(title);
        return 0;
    }

    @Override
    public String getTitle() {
        return quiz.getTitle();
    }
}
