package ua.artcode.model;

import java.io.Serializable;

public class Admin implements Serializable, IPerson {

    private String login = "admin";
    private String pass = "admin";
    private long iDAdmin;
    private String me = "admin";


    public Admin(String login, String pass, long iD) {
        this.login = login;
        this.pass = pass;
        this.iDAdmin = iD;
    }

    @Override
    public String getLogin() {
        return login;
    }

    public long getID() {
        return iDAdmin;
    }

    @Override
    public String getPass() { return pass; }

    @Override
    public String whoAmI() {
        return me;
    }

    @Override
    public String toString() {
        return login +
                ";" + pass +
                ";" + iDAdmin +
                ";" + me;
    }
}
