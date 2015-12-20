package ua.artcode.model;

/**
 * Created by ivan on 20.12.15.
 */
public class Driver {

    private long iDDriver;
    private String name;
    private Car car;
    private boolean status = false;

    public Driver(String name, Car car, long iDDriver) {

        this.name = name;
        this.car = car;
        this.iDDriver = iDDriver;

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
        return iDDriver;
    }

    public boolean takeTicket(){
        return this.status = true;
    }
}
