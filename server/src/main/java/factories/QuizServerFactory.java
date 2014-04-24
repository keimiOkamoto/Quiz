package factories;

import controllers.QuizServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuizServerFactory extends Remote{
    QuizServer getQuizServer() throws RemoteException;
}
