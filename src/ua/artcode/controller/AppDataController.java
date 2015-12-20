package ua.artcode.controller;

import ua.artcode.exception.AdminControllerHasAlreadyCreated;
import ua.artcode.model.Client;
import ua.artcode.model.Driver;
import ua.artcode.model.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public class AppDataController {

    List<Ticket> tickets = new ArrayList<>();
    List<Driver> driver = new ArrayList<>();
    List<Client> clients = new ArrayList<>();
    AdminController adminController;

    public AppDataController(String login, String pass) throws AdminControllerHasAlreadyCreated{

        if(login.equals(AdminController.getLogin()) && pass.equals(AdminController.getPass())){
            adminController = AdminController.getAdminController();
        }

    }

}
