package ua.artcode.controller;

import ua.artcode.exception.BusyDriverExeption;
import ua.artcode.exception.NotFindInDataBaseException;
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

    Driver addDriver(String name, Car car, String pass);

    Driver removeDriver(long id) throws NotFindInDataBaseException;

    List<Client> getAllClients();

    List<Driver> getAllDrivers();

    Client getClientById(long id) throws NotFindInDataBaseException;

    Driver getDriverById(long id) throws NotFindInDataBaseException;

    String[] getFreeDrivers() throws NotFindInDataBaseException;

    Ticket getTicketById(long id) throws NotFindInDataBaseException;

    void setDriverToTicket(long clientId, long driverId) throws NotFindInDataBaseException, BusyDriverExeption;

    Ticket findTicketByClientId(long id) throws NotFindInDataBaseException;

}
