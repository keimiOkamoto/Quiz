package models;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PlayerImpl extends UnicastRemoteObject implements Player {

    private String name;
    private String country;
    private int age;
    private int id;

    public PlayerImpl(String name, String country, int age, int id) throws RemoteException {
        this.name = name;
        this.country = country;
        this.age = age;
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public int getAge() {
        return age;
    }
}
