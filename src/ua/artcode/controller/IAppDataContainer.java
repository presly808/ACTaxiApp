package ua.artcode.controller;

import ua.artcode.model.*;

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
    IPerson searchIPerson(String login, String pass);
    IPerson getClient(String login);
    IPerson getAdmin(String login);
}
