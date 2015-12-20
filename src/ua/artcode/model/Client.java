package ua.artcode.model;

/**
 * Created by dexter on 20.12.15.
 */
public class Client {

    private String login;
    private int phoneNumber;
    private static int iD = 1;
    private String location;
    private long cash;

    public Client(String login, int phoneNumber, String location, long cash) {

        this.login = login;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.cash = cash;
        iD++;

    }

    public String getLogin() {
        return login;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public static int getiD() {
        return iD;
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

    public static void setiD(int iD) {
        Client.iD = iD;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }
}