package factories;

import models.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerFactory extends Remote {
    /**
     * Generates a player.
     *
     * @param name The name of the player.
     * @param country The country of the player.
     * @param age The age of the player.
     * @return A player with the given parameters.
     * @throws java.rmi.RemoteException if there is a problem with the server/connection.
     */
    Player generatePlayer(String name, String country, int age) throws RemoteException;
}
