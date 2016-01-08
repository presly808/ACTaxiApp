package ua.artcode.controller;

import ua.artcode.exception.BusyDriverException;
import ua.artcode.exception.HaveNotNewTickets;
import ua.artcode.model.Driver;
import ua.artcode.model.Ticket;
import ua.artcode.model.TicketStatus;
import ua.artcode.utils.accounts.Calculator;
import ua.artcode.utils.geolocation.Location;
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
    public void changeStatus() throws BusyDriverException {
        if(currentDriver.getIdCurrentTicket() == 0){
            currentDriver.changeStatus();
            TaxiAppSave.save(appDataContainer);
        }else {
            throw new BusyDriverException("You can't change your status, \n" +
                    "because you has already had a ticket.\n" +
                    "Drop the ticket or do your work");
        }

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

    @Override
    public Ticket takeATicket() throws HaveNotNewTickets {

        getCurrentTicket();

        if(currentTicket == null){
            throw new HaveNotNewTickets("the driver doesn't have tickets to accept");
        }

        currentTicket.setIdDriver(currentDriver.getId());

        currentTicket.setArrivalTaxiTime(Calculator.getTimeArrival(currentDriver.getDistanceToClient()));

        currentTicket.setArrivalDestinationTime(Calculator.getTimeArrival(
                Location.getDistance(currentTicket.getFromLocation(), currentTicket.getToLocation())));

        currentTicket.setStatus(TicketStatus.IN_PROGRESS);
        TaxiAppSave.save(appDataContainer);

        return currentTicket;
    }

    // after ArrivalDestinationTime
    @Override
    public void finishTrip() {
        currentTicket.setStatus(TicketStatus.DONE);
        currentTicket.setArrivalDestinationTime(new Date());
        changeLocation(currentTicket.getToLocation());
        dropCurrentTicket();
        TaxiAppSave.save(appDataContainer);
    }

    @Override
    public void changeLocation(String location){
        currentDriver.setCurrentLocation(location);
    }

    // Only street and house number
    @Override
    public void setLocation(String location){
        currentDriver.setCurrentLocation(location);
    }
}
