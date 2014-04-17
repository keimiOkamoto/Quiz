package view;

import java.rmi.RemoteException;
import java.util.Scanner;

public interface QuizMenu {
    /**
     * Prints a List of Quizzes.
     */
    void printListOfQuizzes() throws RemoteException;

}
