package ua.artcode.controller;

import ua.artcode.model.Driver;
import ua.artcode.model.Ticket;
import ua.artcode.model.TicketStatus;
import ua.artcode.utils.serialization.TaxiAppSave;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dexter on 24.12.15.
 */
public class DriverController implements IDriverController{

    private Driver currentDriver;
    private Ticket currentTicket;
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
            if(tmp.getiDTicket() == currentDriver.getIdCurrentTicket()){
                allDriversTickets.add(tmp);
            }
        }

        return allDriversTickets;
    }

    @Override
    public Ticket takeATicket(){

        getCurrentTicket();

        currentTicket.setIdDriver(currentDriver.getId());

        // we need method to calculate time when taxi will arrive to client
        currentTicket.setArrivalTaxiTime(new Date());

        // we need method to calculate time when taxi will arrive to destination place
        currentTicket.setArrivalDestinationTime(new Date());

        // we need method to calculate price
        currentTicket.setPrice(100);

        currentTicket.setStatus(TicketStatus.IN_PROGRESS);

        TaxiAppSave.save(appDataContainer);

        // after ArrivalDestinationTime
        currentTicket.setStatus(TicketStatus.DONE);
        TaxiAppSave.save(appDataContainer);
        dropCurrentTicket();

        return currentTicket;
    }
}
