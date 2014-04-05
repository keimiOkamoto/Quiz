package controllers;

import factories.ItemsFactory;
import models.Quiz;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the quiz server.
 */
public interface QuizServer extends Remote {

    /**
     * Checks if the title already exists.
     *
     * @param title A String title.
     * @return false if the same title already exists.
     * @throws RemoteException
     */
    boolean titleIsValid(String title) throws RemoteException;

    /**
     * Checks if the ID exists.
     *
     * @param id An ID of a Quiz.
     * @return false if the ID doesn't exist.
     * @throws RemoteException
     */
    boolean iDIsValid(int id) throws RemoteException;

    /**
     * Ends quiz by given ID.
     *
     * @param id The ID ofa quiz.
     * @throws RemoteException
     */
    void endQuiz(int id) throws RemoteException;

    /**
     * Saves a quiz to the server.
     *
     * @param quiz A quiz.
     * @throws RemoteException
     */
    void save(Quiz quiz) throws RemoteException;

    /**
     * Gets a ItemsFactory object.
     *
     * @return An ItemsFactory.
     * @throws RemoteException
     */
    ItemsFactory getItemsFactory() throws RemoteException;
}
