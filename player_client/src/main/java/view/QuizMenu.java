package view;

import java.rmi.RemoteException;
import java.util.Scanner;

public interface QuizMenu {
    /**
     * Prints a List of Quizzes.
     */
    void printListOfQuizzes() throws RemoteException;

    /**
     * Gets the message for the menu.
     */
    String getQuizNumberMessage();
}
