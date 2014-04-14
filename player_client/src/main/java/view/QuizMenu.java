package view;

import java.rmi.RemoteException;
import java.util.Scanner;

public interface QuizMenu {
    /**
     * Prints a List of Quizzes.
     */
    void printListOfQuizzes() throws RemoteException;

    /**
     * Gets the users choice from the
     * menue.
     *
     * @param scanner A user input
     */
    void getUserQuizChoice(Scanner scanner);
}
