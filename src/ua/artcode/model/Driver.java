package ua.artcode.model;

/**
 * Created by ivan on 20.12.15.
 */
public class Driver {

    private ID iDDriver = new ID();
    private String name;
    private Car car;
    private boolean status = false;

    public Driver(String name, Car car) {

        this.name = name;
        this.car = car;

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
        return iDDriver.getID();
    }

    public boolean takeTicket(){
        return this.status = true;
    }
}
