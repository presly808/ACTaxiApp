package ua.artcode.model;

import java.io.Serializable;

/**
 * Created by dexter on 20.12.15.
 */
public class Client extends Person implements Serializable {

    private int phoneNumber;
    private String location;

    public Client(String login, int phoneNumber, String location , String pass, long iDClient) {

        this.login = login;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.pass = pass;
        this.iD = iDClient;

    }

    public int getPhoneNumber() {

        return phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return login +
                ";" + phoneNumber +
                ";" + location +
                ";" + pass +
                ";" + iD;
    }

    @Override
    public boolean equals(Object client){

        if(client == null){
            return false;
        }
        if(this == client){
            return true;
        }
        if(client.getClass() != Client.class){
            return false;
        }
        Client tmp = (Client) client;

        return iD == tmp.iD && (login.equals(tmp.login) && pass.equals(tmp.pass))
                && (phoneNumber == tmp.phoneNumber && location.equals(tmp.location));
    }
}