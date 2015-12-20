package ua.artcode.controller;

import ua.artcode.exception.AdminControllerHasAlreadyCreated;
import ua.artcode.model.Client;
import ua.artcode.model.Driver;
import ua.artcode.model.Ticket;

import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public class AdminController implements ITaxiController {

    private static boolean isCreate;

    private static final AdminController INSTANCE = new AdminController();

    private AdminController(){}

    // todo lazy initialization using singleton pattern, load data from file see trello task
    public static AdminController getAdminController() {
        return INSTANCE;
    }

    @Override
    public Client login(String login, String pass) {



        return null;
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
    public Client getClientById(int id) {
        return null;
    }

    @Override
    public Driver getDriverById(int id) {
        return null;
    }

    @Override
    public Ticket getTicketById(int id) {
        return null;
    }

    @Override
    public boolean setDriverToTicket(int clientId, int driverId) {
        return false;
    }

    @Override
    public Ticket findTicketByClientId(int id) {
        return null;
    }
}
