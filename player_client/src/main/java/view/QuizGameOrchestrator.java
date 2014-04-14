package view;

public interface QuizGameOrchestrator {

    /**
     * Gets the quiz and allows the player to play the quiz.
     *
     * @return the message that will lead to the next event.
     */
    String getQuiz();
}
