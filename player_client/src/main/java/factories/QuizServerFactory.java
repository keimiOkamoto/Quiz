package factories;

import controllers.QuizServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuizServerFactory extends Remote{
    /**
     * Gets a QuizServer
     *
     * @return A QuizServer
     * @throws RemoteException if there is a problem with the constructor.
     */
    QuizServer getQuizServer() throws RemoteException;
}
