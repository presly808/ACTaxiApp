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

    Client getClientById(int id);

    Driver getDriverById(int id);

    Ticket getTicketById(int id);

    boolean setDriverToTicket(int clientId, int driverId);

    Ticket findTicketByClientId(int id);

}
