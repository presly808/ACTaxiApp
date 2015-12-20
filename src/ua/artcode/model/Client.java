package ua.artcode.model;

import java.io.Serializable;

/**
 * Created by dexter on 20.12.15.
 */
public class Client implements IPerson, Serializable {

    private String login;
    private String pass;
    private int phoneNumber;
    private String location;
    private long iDClient;
    private String me = "client";

    public Client(String login, int phoneNumber, String location , String pass, long iDClient) {

        this.login = login;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.pass = pass;
        this.iDClient = iDClient;

    }

    public int getPhoneNumber() {

        return phoneNumber;
    }

    public long getiD() {
        return iDClient;
    }

    public String getLocation() {
        return location;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPass() {
        return pass;
    }

    @Override
    public String whoAmI() {
        return me;
    }
}