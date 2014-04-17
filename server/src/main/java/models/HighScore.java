package models;

import java.rmi.RemoteException;

public interface HighScore {
    int getHighScore() throws RemoteException;

    Player getPlayer();
}
