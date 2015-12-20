package ua.artcode.controller;

import ua.artcode.model.Client;
import ua.artcode.model.Driver;
import ua.artcode.model.Ticket;

import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public interface ITaxiController {

    Client login(String login, String pass);
    List<Client> getAllClients();
    List<Driver> getAllDrivers();
    List<Ticket> getTickets();
    Client getClient();
    Driver getDriver();
    Ticket getTicket();
    boolean SetDriverToTicket(Ticket ticket, Driver driver);
    Ticket FindTicketbyID(int iDClient);

}
