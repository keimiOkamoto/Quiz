package views;

import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;

public interface SetupOrchestrator {

    /**
     * prints a welcome message.
     */
    String startMessage();

    void selectOption() throws IllegalQuizException, IllegalQuestionException, IllegalOptionException;

    String printQuizTitleMessage() throws IllegalQuizException, IllegalQuestionException, IllegalOptionException;

    void setInput(String userAnswer);

    String getMessage();
}
