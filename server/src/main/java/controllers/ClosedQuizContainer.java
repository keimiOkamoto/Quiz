package controllers;

import models.Player;
import models.Quiz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * A container that holds quizzes closed by the setup client.
 */
public interface ClosedQuizContainer {
    /**
     * Adds a quiz that has been closed.
     *
     * @param closedQuiz A Quiz that has been closed.
     */
    void add(Quiz closedQuiz);

    List<Quiz> getClosedQuizList();

    /**
     * Getter for a quiz that has been closed.
     *
     * @param id ID of a quiz that has been closed.
     * @return A previously closed quiz.
     */
    Quiz getQuiz(int id);
}
