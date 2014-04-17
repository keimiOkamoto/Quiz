package factories;

import models.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Factory for models associated with a quiz.
 */

public interface ItemsFactory extends Remote {
    /**
     * Generated a quiz.
     *
     * @param title A title of a quiz
     * @return Quiz object
     */
    Quiz generateQuiz(String title) throws RemoteException;

    /**
     * Generates a question.
     *
     * @param question A question
     * @return Question object
     */
    Question generateQuestion(String question) throws RemoteException;

    /**
     * Generates an answer.
     *
     * @param answer An answer to a question
     * @return Answer object
     */
    Answer generateAnswer(String answer, boolean answerType) throws RemoteException;

    void flush() throws RemoteException;

    HighScore getHighScore(Quiz quiz, Player player) throws RemoteException;
}
