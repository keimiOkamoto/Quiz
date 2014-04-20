package models;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A player of the quiz.
 */
public interface Player extends Remote {

    /**
     * Getter for a players ID.
     *
     * @return The ID of the player.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    int getId() throws RemoteException;

    /**
     * Getter for a players name.
     *
     * @return The name of the player.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    String getName() throws RemoteException;

    /**
     * Getter for a players country.
     *
     * @return The country of the player.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    String getCountry() throws RemoteException;

    /**
     * Getter for a players age.
     *
     * @return The age of the player.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    int getAge() throws RemoteException;

    /**
     * Getter for a Score.
     *
     * @return A Score for a player.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    int getScore() throws RemoteException;

    /**
     * Increments the score if a correct
     * answer is entered.
     *
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    void incrementScore() throws RemoteException;

    /**
     * Resets the player score.
     *
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    void resetScore() throws RemoteException;
}
