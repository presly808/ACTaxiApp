package ua.artcode.model;

import java.io.Serializable;

public class Admin extends Person implements Serializable {


    public Admin(String login, String pass, long iD) {
        this.login = login;
        this.pass = pass;
        this.iD = iD;
        this.pass = "admin";
        this.login = "admin";
    }

    @Override
    public String toString() {
        return login +
                ";" + pass +
                ";" + iD;
    }
}
