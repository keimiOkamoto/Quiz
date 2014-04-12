package views;

import exceptions.IllegalQuestionException;
import exceptions.IllegalQuizException;

import java.rmi.RemoteException;
import java.util.Scanner;

public interface SetupOrchestrator {

    /**
     * prints a welcome message.
     */
    String printStartMessage();

    String printAddQuestionMessage();

    String printAddAnswerMessage();

    String getMessageForQuestion(String userInput) throws RemoteException, IllegalQuizException;

    String getMessageForAnswer(String userInput) throws RemoteException;

    String printQuizTitleMessage() throws IllegalQuizException, IllegalQuestionException, IllegalOptionException;

    void setInput(String userAnswer);

    String printCorrectQuestionMessage();

    String getMessageForQuizTitle() throws RemoteException;

    String getAnswer();

    void createQuizTitle(String userAnswer) throws RemoteException, IllegalQuizException;

    void addQuestion(String userAnswer) throws RemoteException, IllegalQuizException, IllegalQuestionException;

    boolean correct(String userInput);

    String getMessageForYesOrNo(String yesOrNo) throws RemoteException, IllegalQuestionException;

    void setAnswer(String userInput);
}
