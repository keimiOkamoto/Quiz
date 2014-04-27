package view;

import controllers.Server;
import exceptions.IllegalGameException;
import models.Player;

import java.rmi.RemoteException;
import java.util.Scanner;

public interface PlayQuiz {

    /**
     * Getter for the quiz menu.
     *
     * @param scanner A scanner to read the users input.
     * @param player A player object.
     * @param server A server object.
     * @param message A message to start the loop.
     * @return A message string.
     * @throws RemoteException if there is a problem with the server/connection.
     * @throws IllegalGameException if quiz doesn't exist.
     */
    String getQuizMenu(Scanner scanner, Player player, Server server, String message) throws RemoteException, IllegalGameException;
}
