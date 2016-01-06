package ua.artcode.controller;

import ua.artcode.exception.BusyDriverException;
import ua.artcode.exception.HaveNotNewTickets;
import ua.artcode.model.Ticket;

/**
 * Created by dexter on 24.12.15.
 */
public interface IDriverController extends ITaxiController {

    Ticket getCurrentTicket();
    boolean isFree();
    void changeStatus() throws BusyDriverException;
    void dropCurrentTicket();
    Ticket takeATicket() throws HaveNotNewTickets;
    void changeLocation(String location);
    void finishTrip();
    void setLocation(String location);

}
