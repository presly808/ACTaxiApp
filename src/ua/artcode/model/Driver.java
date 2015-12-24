package ua.artcode.model;


import java.io.Serializable;

public class Driver implements Serializable{

    private long iD;
    private String name;
    private Car car;
    private boolean isFree = false;
    private long iDCurrentTicket;

    public Driver(String name, Car car, long iD, boolean isFree, long iDTicket) {

        this.name = name;
        this.car = car;
        this.iD = iD;
        this.isFree = isFree;
        this.iDCurrentTicket = iDTicket;

    }

    public String getName() {
        return name;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public String toString() {
        return name +
                ";" + car +
                ";" + iD +
                ";" + isFree +
                ";" + iDCurrentTicket;
    }

    public boolean isFree() {
        return isFree;
    }

    public long getiDDriver() {
        return iD;
    }

    public boolean getStatus(){ return isFree;}

    public boolean takeTicket(){
        return this.isFree = true;
    }

    public void changeStatus(){
        isFree = !isFree;
    }

    public long getIdCurrentTicket(){
        return iDCurrentTicket;
    }

    public void dropCurrentIdTicket(){
        iDCurrentTicket = 0;
    }
}
