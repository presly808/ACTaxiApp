package ua.artcode.controller;

import ua.artcode.model.*;
import ua.artcode.utils.serialization.TaxiAppLoader;
import ua.artcode.utils.serialization.TaxiAppSave;

import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public class AdminController implements ITaxiController {

    AppDataContainer appDataContainer;
    String me = "admin";

    private AdminController(AppDataContainer appDataContainer){
        this.appDataContainer = appDataContainer;
    }

    public static AdminController getAdminController(AppDataContainer appDataContainer) {
        return new AdminController(appDataContainer);
    }

    public Client addClient(String name, int phone, String location, String pass, long cash){

        Client client = new Client(name, phone, location, cash, pass, ID.genId());
        appDataContainer.addClientToData(client);
        TaxiAppSave.save("file", appDataContainer);

        return client;
    }

    public Driver addDriver(String name, Car car){

        Driver driver = new Driver(name, car, ID.genId());
        appDataContainer.addDriverToData(driver);
        TaxiAppSave.save("file", appDataContainer);

        return driver;
    }

    @Override
    public List<Client> getAllClients() {
        return appDataContainer.getListClients();
    }

    @Override
    public List<Driver> getAllDrivers() {
        return appDataContainer.getListDrivers();
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

    public String whoAmI(){
        return me;
    }
}
