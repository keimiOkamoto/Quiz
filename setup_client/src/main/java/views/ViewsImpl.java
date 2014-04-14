package views;

public class ViewsImpl implements Views {

    @Override
    public QuizCloser getQuizCloser(SetupOrchestrator setupOrchestrator) {
        return new QuizCloserImpl(setupOrchestrator);
    }

    @Override
    public UserAnswerView getUserAnswerView() {
        return new UserAnswerViewImpl();
    }

    @Override
    public SaveQuizView getSaveQuizView() {
        return new SaveQuizViewImpl();
    }

    @Override
    public QuestionView getQuestionView() {
        return new QuestionViewImpl();
    }

    @Override
    public GameSetupView getGameSetupView() {
        return new GameSetupViewImpl();
    }
}
