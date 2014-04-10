package views;

import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;

public interface SetupOrchestrator {

    /**
     * prints a welcome message.
     */
    String startMessage();

    void selectOption() throws IllegalQuizException, IllegalQuestionException, IllegalOptionException;

    void createQuizTitle() throws IllegalQuizException, IllegalQuestionException, IllegalOptionException;

    void getInput(String userAnswer);

    String getMessage();
}
