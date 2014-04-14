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
}
