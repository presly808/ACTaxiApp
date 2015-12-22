package ua.artcode.model;


import java.io.Serializable;

public class Driver implements Serializable{

    private long iD;
    private String name;
    private Car car;
    private boolean status = false;

    public Driver(String name, Car car, long iD, boolean status) {

        this.name = name;
        this.car = car;
        this.iD = iD;
        this.status = status;

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
                ";" + status;
    }

    public boolean isStatus() {
        return status;
    }

    public long getiDDriver() {
        return iD;
    }

    public boolean takeTicket(){
        return this.status = true;
    }
}
