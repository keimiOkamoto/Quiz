package view;

import controllers.Server;
import exceptions.IllegalGameException;
import models.Player;

import java.rmi.RemoteException;
import java.util.Scanner;

public interface PlayQuiz {
    String getQuizMenu(Scanner scanner, Player player, Server server, String message) throws RemoteException, IllegalGameException;
}
