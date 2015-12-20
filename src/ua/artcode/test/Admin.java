package ua.artcode.test;


public class Admin {
    private String login;
    private String pass;
    private long ID = 0;

    public Admin(String login, String pass) {
        this.login = login;
        this.pass = pass;
        this.ID++;
    }

    public String getLogin() {
        return login;
    }

    public long getID() {
        return ID;
    }
}
