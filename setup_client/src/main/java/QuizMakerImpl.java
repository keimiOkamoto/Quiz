/**
 * Controls the organization of the quiz.
 */
public class QuizMakerImpl implements QuizMaker {
    private Server server;
    private Quiz quiz;
    private Question question;


    public QuizMakerImpl(Server server) {
        this.server = server;
    }

    @Override
    public int createQuiz(String title) throws IllegalArgumentException {
        if (title == null || title.trim().equals("")) throw new IllegalArgumentException("Title is empty. Please enter a title with at least one character.");
        quiz = server.createQuiz(title.trim());
        return 0;
    }

    @Override
    public String getTitle() {
        return quiz.getTitle();
    }

    @Override
    public void addQuestion(String questionString) throws IllegalQuizException, IllegalArgumentException {
        if (quiz == null) throw new IllegalQuizException();
        if (questionString == null || questionString.trim().equals("")) throw new IllegalArgumentException("Question entered is empty. Please try again.");

        question = server.createQuestion(questionString);
        quiz.addQuestion(question);
    }

    @Override
    public void addAnswer(String answer) throws IllegalQuestionException, IllegalArgumentException {
        if (question == null) throw new IllegalQuestionException();
        if (answer == null || answer.trim().equals("")) throw new IllegalArgumentException("Answer entered is empty. Please enter a valid answer.");

        Answer answer1 = server.createAnswer(answer);
        question.addAnswer(answer1);
    }
}
