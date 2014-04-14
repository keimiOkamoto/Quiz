package views;

import exceptions.IllegalQuestionException;

import java.rmi.RemoteException;

/**
 * This class orchestrates the setup of a quiz.
 * <p/>
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
     * <p/>
     * If the input is valid the message is updated
     * and the can proceed.
     *
     * @param userInput An question made by client.
     * @return A 'message'
     * @throws RemoteException If there is a problem with the server.
     */
    String getMessageForQuestion(String userInput) throws RemoteException;

    /**
     * Method that determines the next state of the 'message' variable.
     * <p/>
     * If null is entered, message remains in current state
     * and requests an answer to be entered.
     * <p/>
     * If 'DONE' is entered, 'save or add more questions?'
     * is returned.
     * <p/>
     * If anything ele is entered the answer is set and
     * the message 'correct or incorrect answer?' is returned.
     *
     * @param userInput
     * @return A message depending on the state.
     * @throws RemoteException          If there is a problem with the server.
     * @throws IllegalArgumentException If the answer already exists.
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
     * Setter for an Answer.
     *
     * @param answer An answer.
     */
    void setAnswer(String answer);

    /**
     * Getter for answer.
     *
     * @return The answer set.
     */
    String getAnswer();
}
