package views;

import exceptions.IllegalQuestionException;

import java.rmi.RemoteException;

/**
 * This class orchestrates the setup of a quiz.
 *
 * Through out the comments when 'messages' are seen,
 * this means the messages that allow the event to
 * proceed.
 */

public interface SetupOrchestrator {

    /**
     * The start to the even loop.
     * <p/>
     * 'Messages' are replaced according to condition.
     * It determines which even to jump to and depending
     * on the state of the 'message' string it will
     * create a quiz with a valid title.
     * <p/>
     * IllegalQuizException and IllegalArgumentException
     * thrown by the quizOrchestrator.createQuiz() is also
     * caught here.
     *
     * @return 'message' for the next state.
     * @throws RemoteException If there is an issue with the server.
     */
    String getMessageForQuizTitle() throws RemoteException;

    /**
     * If addQuestion(userInput) throws exceptions.
     * i.e If input is invalid.
     * current 'message' is unaltered.
     *
     * If the input is valid the message is updated
     * and the can proceed.
     *
     * @param userInput An question made by client.
     * @return A 'message'
     * @throws RemoteException If there is a problem with the server.
     */
    String getMessageForQuestion(String userInput) throws RemoteException;

    /**
     *
     * @param userInput
     * @return
     * @throws RemoteException
     * @throws IllegalArgumentException
     */
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
     * Setter for an Answer
     *
     * @param answer An anwer
     */
    void setAnswer(String answer);

    String getAnswer();
}
