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

    private static String login = "admin";
    private static String pass = "admin";
    private static boolean isCreate;

    private AdminController(){
        isCreate = true;
    }

    public static AdminController getAdminController() throws AdminControllerHasAlreadyCreated{
        if(isCreate){
            throw new AdminControllerHasAlreadyCreated();
        }
        return  new AdminController();
    }

    public static String getLogin() {
        return login;
    }

    public static String getPass() {
        return pass;
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
