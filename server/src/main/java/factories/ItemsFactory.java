package factories;

import models.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Factory for models associated with a quiz.
 */

public interface ItemsFactory extends Remote {
    /**
     * Generates a quiz.
     *
     * @param title A title for the quiz
     * @return Quiz object with the given title.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    Quiz generateQuiz(String title) throws RemoteException;

    /**
     * Generates a question.
     *
     * @param question A question
     * @return Question object
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    Question generateQuestion(String question) throws RemoteException;

    /**
     * Generates an answer.
     *
     * @param answer An answer to a question
     * @param answerType A boolean value that signifies if the answer is correct of incorrect.
     * @return Answer with the given answerType.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    Answer generateAnswer(String answer, boolean answerType) throws RemoteException;

    /**
     * Created a highScore with the given parameters.
     *
     * @param quiz A quiz played.
     * @param player A player that played the quiz.
     * @return a highScore object with the quiz and the player.
     * @throws RemoteException if there is a problem with the server/connection.
     */
    HighScore generateHighScore(Quiz quiz, Player player) throws RemoteException;

    /**
     * serializes the unique number generator.
     *
     * @throws RemoteException if there is a problem with the server/connection.
     */
    void flush() throws RemoteException;
}
