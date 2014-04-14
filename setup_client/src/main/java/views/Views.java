package views;

/**
 * In charge of creating view objects,
 * (Views Factory)
 *
 */
public interface Views {

    /**
     * Gets instance of a QuizCloser
     *
     * @param setupOrchestrator A setupOrchestrator.
     * @return A QuizCloser object.
     */
    QuizCloser getQuizCloser(SetupOrchestrator setupOrchestrator);

    /**
     * Gets instance of a UserAnswerView
     *
     * @return A UserAnswerView object.
     */
    UserAnswerView getUserAnswerView();

    /**
     * Gets instance of a getSaveQuizView.
     *
     * @return A SaveQuizView.
     */
    SaveQuizView getSaveQuizView();
}
