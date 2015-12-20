package ua.artcode.controller;

import ua.artcode.exception.AdminControllerHasAlreadyCreated;
import ua.artcode.model.*;

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

    public AppDataContainer(){}

    public AppDataContainer(AppDataContainer appDataContainer){

        this.tickets = appDataContainer.tickets;
        this.driver = appDataContainer.driver;
        this.clients = appDataContainer.clients;
        this.admins = appDataContainer.admins;

    }

    public void addClientToData(Client client){

        this.clients.add(client);

    }

    public void addDriverToData(Driver driver){

        this.driver.add(driver);

    }

    public List getListClients(){
        return clients;
    }

    @Override
    public List getListDrivers() {
        return driver;
    }


}
