package ua.artcode.controller;

import ua.artcode.model.*;
import ua.artcode.utils.database_gen.DataBaseListsGen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public class AppDataContainer implements Serializable, IAppDataContainer {

    private List<Ticket> tickets = new ArrayList<>();
    private List<Driver> driver = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();

    public AppDataContainer(){

        tickets = DataBaseListsGen.ticketsListGen(100);
        driver = DataBaseListsGen.driversListGen(100);
        clients = DataBaseListsGen.clientsListGen(100);
        admins = DataBaseListsGen.adminsListGen(100);

    }

    public AppDataContainer(AppDataContainer appDataContainer){

        this.tickets = appDataContainer.tickets;
        this.driver = appDataContainer.driver;
        this.clients = appDataContainer.clients;
        this.admins = appDataContainer.admins;

    }

    @Override
    public void addClientToData(Client client){

        this.clients.add(client);

    }

    @Override
    public void addDriverToData(Driver driver){

        this.driver.add(driver);

    }

    @Override
    public void addTicketToData(Ticket ticket){

        this.tickets.add(ticket);

    }

    @Override
    public void addAdminToData(Admin admin){

        this.admins.add(admin);

    }

    @Override
    public List<Client> getListClients(){
        return clients;
    }

    @Override
    public List<Driver> getListDrivers() {
        return driver;
    }

    @Override
    public List<Admin> getListAdmins() {
        return admins;
    }

    @Override
    public List<Ticket> getListTickets(){
        return tickets;
    }


}
