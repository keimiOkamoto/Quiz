package view;

public interface QuizGameOrchestrator {

    /**
     * Gets the quiz and allows the player to play the quiz.
     *
     * @return the message that will lead to the next event.
     */
    String getQuiz();

    String checkForValidNumber(String userInput);

    String getValidNumberMessage();

    void setQuizNumber(int numberIndex);
}
