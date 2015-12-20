package ua.artcode.model;

public class Client {

    private String login;
    private String pass;
    private int phoneNumber;
    private String location;
    private long cash;
    private ID iDClient = new ID();

    public Client(String login, int phoneNumber, String location, long cash, String pass) {

        this.login = login;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.cash = cash;
        this.pass = pass;

    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public int getPhoneNumber() {

        return phoneNumber;
    }

    public long getiD() {
        return iDClient.getID();
    }

    public String getLocation() {
        return location;
    }

    public long getCash() {
        return cash;
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

    public void setCash(long cash) {
        this.cash = cash;
    }
}