package view;

import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public interface QuizMenu {
    /**
     * Prints a List of Quizzes.
     */

    void printListOfQuizzes(List<Quiz> quizList) throws RemoteException;

    void printListOfClosedQuizzes(List<Quiz> closedQuizList) throws RemoteException;

    void print() throws RemoteException;
}
