package models;

import java.rmi.RemoteException;

public interface Player {


    /**
     * Getter for a players ID.
     *
     * @return The ID of the player.
     */
    int getId() throws RemoteException;

    /**
     * Getter for a players name.
     *
     * @return The name of the player.
     */
    String getName() throws RemoteException;

    /**
     * Getter for a players country.
     *
     * @return The country of the player.
     */
    String getCountry() throws RemoteException;

    /**
     * Getter for a players age.
     *
     * @return The age of the player.
     */
    int getAge() throws RemoteException;

    /**
     * Getter for a Score.
     *
     * @return A Score for a player.
     */
    int getScore() throws RemoteException;

    /**
     * Increments the score if a correct
     * answer is entered.
     */
    void incrementScore() throws RemoteException;
}
