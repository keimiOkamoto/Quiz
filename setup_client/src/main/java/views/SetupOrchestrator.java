package views;

import exceptions.IllegalQuestionException;

import java.rmi.RemoteException;

public interface SetupOrchestrator {

    /**
     * The start to the even loop.
     * <p/>
     * Messages are replaced according to condition.
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
     * Setter for an Answer
     *
     * @param answer An anwer
     */
    void setAnswer(String answer);

    String getAnswer();
}
