package ua.artcode.controller;

import ua.artcode.model.Driver;
import ua.artcode.model.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexter on 24.12.15.
 */
public class DriverController implements IDriverController{

    private Driver currentDriver;
    private Ticket currentTicket;
    private String me = "driver";
    private AppDataContainer appDataContainer;

    public DriverController(Driver currentDriver, AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
        this.currentDriver = currentDriver;
    }

    @Override
    public Ticket getCurrentTicket() {

        for(Ticket tmp : appDataContainer.getListTickets()){

            if(tmp.getiDTicket() == currentDriver.getIdCurrentTicket()){
                currentTicket = tmp;
            }

        }

        return currentTicket;
    }

    @Override
    public boolean isFree() {
        return currentDriver.isFree();
    }

    @Override
    public void changeStatus() {
        currentDriver.changeStatus();
    }

    @Override
    public void dropCurrentTicket() {
        currentDriver.dropCurrentIdTicket();
    }

    @Override
    public List<Ticket> getTickets() {

        ArrayList<Ticket> allDriversTickets = new ArrayList<>();

        for(Ticket tmp : appDataContainer.getListTickets()){
            if(tmp.getIdDriver() == currentDriver.getId()){
                allDriversTickets.add(tmp);
            }
        }

        return allDriversTickets;
    }
}
