package ua.artcode.controller;

import ua.artcode.model.Admin;
import ua.artcode.model.Client;
import ua.artcode.model.Driver;
import ua.artcode.model.Ticket;

import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public interface IAppDataContainer {

    void addClientToData(Client client);
    void addDriverToData(Driver driver);
    void addTicketToData(Ticket ticket);
    void addAdminToData(Admin admin);
    List<Client> getListClients();
    List<Driver> getListDrivers();
    List<Admin> getListAdmins();
    List<Ticket> getListTickets();

}
