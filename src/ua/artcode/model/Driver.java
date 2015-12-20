package ua.artcode.model;


public class Driver {

    private long iD;
    private String name;
    private Car car;
    private boolean status = false;

    public Driver(String name, Car car, long iD) {

        this.name = name;
        this.car = car;
        this.iD = iD;

    }

    public String getName() {
        return name;
    }

    public Car getCar() {
        return car;
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
