package ua.artcode.model;

/**
 * Created by ivan on 20.12.15.
 */
public class Driver {
    private int id = 0;
    private String name;
    private Car car;
    private boolean status = false;

    public Driver(String name, Car car) {
        this.id = id++;
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

    public boolean takeTicket(){
        return this.status = true;
    }
}
