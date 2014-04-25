package models;

import java.rmi.RemoteException;

/**
 * A highScore holds the quiz and the player of the quiz.
 */
public interface HighScore {

    /**
     * Get player country.
     *
     * @return Player's country.
     */
    String getPlayerCountry();

    /**
     * Gets the player's age.
     *
     * @return Players age.
     */
    int getPlayerAge();

    /**
     * Gets the players score.
     *
     * @return the players score.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    int getHighScore() throws RemoteException;

    /**
     * Gets the player.
     *
     * @return a player.
     */
    String getPlayerName();
}
