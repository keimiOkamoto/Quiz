package models;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * An answer to the question with a true or false value to signify if it is the
 * correct or incorrect answer.
 */
public interface Answer extends Remote {

    /**
     * Getter for answer.
     *
     * @return A answer String.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    String getAnswer() throws RemoteException;

    /**
     * Getter for a Answers' type.
     * If the answer is correct or incorrect.
     *
     * @return true is the answer is the correct one.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    boolean getAnswerType() throws RemoteException;
}
