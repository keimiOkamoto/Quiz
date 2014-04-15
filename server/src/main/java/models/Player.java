package models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Player extends Remote {

    /**
     * Getter for a players ID.
     *
     * @return The ID of the player.
     */
    int getId();

    /**
     * Getter for a players name.
     *
     * @return The name of the player.
     */
    String getName();

    /**
     * Getter for a players country.
     *
     * @return The country of the player.
     */
    String getCountry();

    /**
     * Getter for a players age.
     *
     * @return The age of the player.
     */
    int getAge();
}
