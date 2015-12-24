package ua.artcode.model;


import java.io.Serializable;

public class Driver implements Serializable{

    private long iD;
    private String name;
    private Car car;
    private boolean isFree = false;

    public Driver(String name, Car car, long iD, boolean isFree) {

        this.name = name;
        this.car = car;
        this.iD = iD;
        this.isFree = isFree;

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
                ";" + isFree;
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
}
