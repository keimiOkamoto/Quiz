package models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Answer extends Remote{

    /**
     * Getter for answer.
     *
     * @return A answer String.
     */
    String getAnswer();

    /**
     * Getter for a Answers' type.
     *
     * @return true is the answer is the correct one.
     */
    boolean getAnswerType();
}
