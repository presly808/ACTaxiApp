package ua.artcode.controller;

import ua.artcode.model.*;
import ua.artcode.utils.serialization.TaxiAppLoader;
import ua.artcode.utils.serialization.TaxiAppSave;

import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public class AdminController implements ITaxiController {

    private static final AdminController INSTANCE = new AdminController(TaxiAppLoader.load("nameFile"));
    AppDataContainer appDataContainer;

    private AdminController(AppDataContainer appDataContainer){
        this.appDataContainer = appDataContainer;
    }

    public static AdminController getAdminController() {
        return INSTANCE;
    }

    public void addClient(){

        Client client = new Client("name", 123, "location", 123, "pass", new ID().getID());
        appDataContainer.addClientToData(client);
        TaxiAppSave.save("file", appDataContainer);
    }

    public void addDriver(){

        Driver driver = new Driver("name", new Car(), new ID().getID());
        appDataContainer.addDriverToData(driver);
        TaxiAppSave.save("file", appDataContainer);
    }

    @Override
    public Admin login(String login, String pass) {
        return new Admin(login,pass,new ID().getID());
    }

    @Override
    public List<Client> getAllClients() {
        return null;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return null;
    }

    @Override
    public List<Ticket> getTickets() {
        return null;
    }

    @Override
    public Client getClientById(long id) {
        return null;
    }

    @Override
    public Driver getDriverById(long id) {
        return null;
    }

    @Override
    public Ticket getTicketById(long id) {
        return null;
    }

    @Override
    public boolean setDriverToTicket(long clientId, long driverId) {
        return false;
    }

    @Override
    public Ticket findTicketByClientId(long id) {
        return null;
    }
}
