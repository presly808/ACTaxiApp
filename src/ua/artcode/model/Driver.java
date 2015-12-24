package ua.artcode.model;


import java.io.Serializable;

public class Driver extends Person implements Serializable{

    private Car car;
    private boolean isFree = false;
    private long iDCurrentTicket;

    public Driver(String name, Car car, long iD, boolean isFree, long iDTicket) {

        this.login = name;
        this.car = car;
        this.iD = iD;
        this.isFree = isFree;
        this.iDCurrentTicket = iDTicket;

    }

    public Driver(String name, Car car, long iD, boolean isFree){

        this.login = name;
        this.car = car;
        this.iD = iD;
        this.isFree = isFree;
        this.iDCurrentTicket = 0;

    }

    public Car getCar() {
        return car;
    }

    @Override
    public String toString() {
        return login +
                ";" + car +
                ";" + iD +
                ";" + isFree +
                ";" + iDCurrentTicket;
    }

    public boolean isFree() {
        return isFree;
    }

    public boolean getStatus(){ return isFree;}

    public boolean takeTicket(long iDCurrentTicket){

        if(!isFree && iDCurrentTicket > 0){
            return false;
        }

        this.iDCurrentTicket = iDCurrentTicket;

        return isFree = false;
    }

    public void changeStatus(){
        isFree = !isFree;
    }

    public long getIdCurrentTicket(){
        return iDCurrentTicket;
    }

    public void dropCurrentIdTicket(){
        iDCurrentTicket = 0;
        isFree = true;
    }
}
