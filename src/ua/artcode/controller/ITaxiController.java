package ua.artcode.controller;

import ua.artcode.model.Car;
import ua.artcode.model.Client;
import ua.artcode.model.Driver;
import ua.artcode.model.Ticket;

import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public interface ITaxiController {

    Client addClient(String name, int phone, String location, String pass, long cash);

    Driver addDriver(String name, Car car);

    List<Client> getAllClients();

    List<Driver> getAllDrivers();

    List<Ticket> getTickets();

    Client getClientById(long id);

    Driver getDriverById(long id);

    Ticket getTicketById(long id);

    boolean setDriverToTicket(long clientId, long driverId);

    Ticket findTicketByClientId(long id);

    public String whoAmI();

}
