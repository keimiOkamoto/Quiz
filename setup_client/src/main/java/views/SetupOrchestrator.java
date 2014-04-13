package views;

import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;

import java.rmi.RemoteException;

public interface SetupOrchestrator {

    String getMessageForQuizTitle() throws RemoteException;

    String getMessageForQuestion(String userInput) throws RemoteException;

    String getMessageForAnswer(String userInput) throws RemoteException, IllegalArgumentException;

    String getMessageForYesOrNo(String yesOrNo) throws RemoteException, IllegalQuestionException;

    String getMessageForSave(String userInput);

    String getMessageForCloseQuiz(String userInput);

    /**
     * Setter for the user input.
     *
     * @param userAnswer An answer for a question.
     */
    void setInput(String userAnswer);

    /**
     * Setter for an A
     *
     * @param answer
     */
    void setAnswer(String answer);

    String getAnswer();
}
