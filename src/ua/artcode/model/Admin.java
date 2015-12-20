package ua.artcode.model;

import ua.artcode.controller.IPerson;

import java.io.Serializable;

// TODO OLEG replace id by simple type (long) in cotroller will be generation of id
public class Admin implements IPerson, Serializable {

    private String login = "admin";
    private String pass = "admin";
    private long iDAdmin;

    public Admin(String login, String pass, long iD) {
        this.login = login;
        this.pass = pass;
        this.iDAdmin = iD;
    }

    public String getLogin() {
        return login;
    }

    public long getID() {
        return iDAdmin;
    }

    public String getPass() { return pass; }
}
