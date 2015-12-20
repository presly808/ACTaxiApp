package ua.artcode.model;


public class Admin {
    private String login;
    private String pass;
    private ID iDAdmin = new ID();

    public Admin(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public long getID() {
        return iDAdmin.getID();
    }
}
