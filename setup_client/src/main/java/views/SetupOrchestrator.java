package views;

import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;

import java.rmi.RemoteException;

public interface SetupOrchestrator {

    /**
     * prints a welcome message.
     */
    String startMessage();

    void selectOption() throws IllegalQuizException, IllegalQuestionException, IllegalOptionException;

    String printAddQuestionMessage();

    String printAddAnswerMessage();

    String getMessageForQuestion(String userInput) throws RemoteException, IllegalQuizException;

    String getMessageForAnswer(String userInput, boolean value) throws RemoteException;

    String printQuizTitleMessage() throws IllegalQuizException, IllegalQuestionException, IllegalOptionException;

    void setInput(String userAnswer);

    String getMessageForQuizTitle() throws RemoteException;

    void createQuizTitle(String userAnswer) throws RemoteException, IllegalQuizException;

    void addQuestion(String userAnswer) throws RemoteException, IllegalQuizException, IllegalQuestionException;
}
