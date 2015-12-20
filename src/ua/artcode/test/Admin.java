package ua.artcode.test;


public class Admin {
    private String login;
    private String pass;
    private final long ID;

    public Admin(String login, String pass, long ID) {
        this.login = login;
        this.pass = pass;
        this.ID = ID;
    }
}
