package ua.artcode.model;

// TODO OLEG replace id by simple type (long) in cotroller will be generation of id
public class Admin {

    private String login;
    private String pass;
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
}
