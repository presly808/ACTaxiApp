package ua.artcode.controller;

import ua.artcode.model.Car;
import ua.artcode.model.Client;
import ua.artcode.model.Driver;
import ua.artcode.model.Ticket;

import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public interface IAdminController extends ITaxiController{

    Client addClient(String name, int phone, String location, String pass );

    Driver addDriver(String name, Car car);

    List<Client> getAllClients();

    List<Driver> getAllDrivers();

    Client getClientById(long id);

    Driver getDriverById(long id);

    long getFreeDriver();

    Ticket getTicketById(long id);

    boolean setDriverToTicket(long clientId, long driverId);

    Ticket findTicketByClientId(long id);

}
