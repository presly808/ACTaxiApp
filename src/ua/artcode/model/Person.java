package ua.artcode.model;

/**
 * Created by dexter on 20.12.15.
 */
public abstract class Person {


    protected String login;
    protected String pass;
    protected long iD;

    public String getLogin(){
        return login;
    }
    public String getPass(){
        return pass;
    }
    public long getId(){
        return iD;
    }
    public void setLogin(String login) {
        this.login = login;
    }
}
