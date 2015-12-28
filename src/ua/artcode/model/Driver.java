package ua.artcode.model;


import java.awt.*;
import java.io.Serializable;

public class Driver extends Person implements Serializable{

    private Car car;
    private boolean isFree = false;
    private long iDCurrentTicket;

    public Driver(String name, Car car, long iD, boolean isFree, long iDTicket, String pass) {

        this.login = name;
        this.car = car;
        this.iD = Math.abs(iD);
        this.isFree = isFree;
        this.iDCurrentTicket = iDTicket;
        this.pass = pass;

    }

    public Driver(String name, Car car, long iD, boolean isFree){

        this.login = name;
        this.car = car;
        this.iD = iD;
        this.isFree = isFree;
        this.iDCurrentTicket = 0;
        pass = "driver";

    }

    public Car getCar() {
        return car;
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


        return !(isFree = false);
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

    @Override
    public String toString() {
        return login +
                ";" + car +
                ";" + iD +
                ";" + isFree +
                ";" + iDCurrentTicket +
                ";" + pass;
    }

    @Override
    public boolean equals(Object driver){

        if(driver == null){
            return false;
        }
        if(this == driver){
            return true;
        }
        if(driver.getClass() != Driver.class){
            return false;
        }
        Driver tmp = (Driver) driver;

        return (isFree == tmp.isFree && iDCurrentTicket == tmp.iDCurrentTicket) &&
                ((iD == tmp.iD && car.equals(tmp.car)) &&
                        (login.equals(tmp.login) && pass.equals(tmp.pass)));
    }
}
