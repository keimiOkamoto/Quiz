package views;

import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;

import java.rmi.RemoteException;

public interface SetupOrchestrator {

    void setInput(String userAnswer);

    String getMessageForQuizTitle() throws RemoteException;

    String getMessageForQuestion(String userInput) throws RemoteException;

    String getMessageForAnswer(String userInput) throws RemoteException, IllegalArgumentException;

    String getMessageForYesOrNo(String yesOrNo) throws RemoteException, IllegalQuestionException;

    String getMessageForSave(String userInput);

    String getMessageForCloseQuiz(String userInput);

    void setAnswer(String answer);

    String getAnswer();

    void createQuizTitle(String userAnswer) throws RemoteException, IllegalQuizException, IllegalArgumentException;

    void addQuestion(String userInput) throws RemoteException, IllegalQuizException, IllegalArgumentException, IllegalQuestionException;

    boolean correct(String userInput);
}
