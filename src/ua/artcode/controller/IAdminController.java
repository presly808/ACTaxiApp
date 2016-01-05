package ua.artcode.controller;

import ua.artcode.exception.BusyDriverException;
import ua.artcode.exception.LoginHasAlreadyUsed;
import ua.artcode.exception.NotFindInDataBaseException;
import ua.artcode.model.*;

import java.util.List;
import java.util.Vector;

/**
 * Created by dexter on 20.12.15.
 */
public interface IAdminController extends ITaxiController{

    Client addClient(String name, int phone, String location, String pass ) throws LoginHasAlreadyUsed;

    Driver addDriver(String name, Car car, String pass);

    Admin addAdmin(String name, String pass) throws LoginHasAlreadyUsed;

    Driver removeDriver(long id) throws NotFindInDataBaseException;

    List<Client> getAllClients();

    List<Driver> getAllDrivers();

    Client getClientById(long id) throws NotFindInDataBaseException;

    Driver getDriverById(long id) throws NotFindInDataBaseException;

    Vector<Driver> getFreeDrivers() throws NotFindInDataBaseException;

    Ticket getTicketById(long id) throws NotFindInDataBaseException;

    void setDriverToTicket(long clientId, long driverId) throws NotFindInDataBaseException, BusyDriverException;

    Ticket findTicketByClientId(long id) throws NotFindInDataBaseException;

}
