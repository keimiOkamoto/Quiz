package view;

import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public interface QuizMenu {

    /**
     * Prints a List of Quizzes.
     *
     * @param quizList A list of quizzes to be printed.
     * @throws RemoteException If there is a problem with the server/connection.
     */
    void printListOfQuizzes(List<Quiz> quizList) throws RemoteException;

    /**
     * Prints a list of closed quizzes.
     *
     * @param closedQuizList A list of closed quizzes.
     * @throws RemoteException If there is a problem with the server/connection.
     */
    void printListOfClosedQuizzes(List<Quiz> closedQuizList) throws RemoteException;

    /**
     * Prints out index for the list of quizzes.
     *
     * @throws RemoteException If there is a problem with the server/connection.
     */
    void print() throws RemoteException;
}
