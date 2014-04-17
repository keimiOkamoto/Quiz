package models;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PlayerImpl extends UnicastRemoteObject implements Player {

    private String name;
    private String country;
    private int age;
    private int id;
    private int score;

    public PlayerImpl(String name, String country, int age, int id) throws RemoteException {
        this.name = name;
        this.country = country;
        this.age = age;
        this.id = id;
        this.score = 0;
    }

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getCountry() throws RemoteException {
        return country;
    }

    @Override
    public int getAge() throws RemoteException {
        return age;
    }


    @Override
    public int getScore() throws RemoteException {
        return score;
    }

    @Override
    public void incrementScore() throws RemoteException {
        score++;
    }
}
