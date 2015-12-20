package ua.artcode.controller;

import ua.artcode.exception.AdminControllerHasAlreadyCreated;
import ua.artcode.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public class AppDataContainer {

    private List<Ticket> tickets = new ArrayList<>();
    private List<Driver> driver = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();

    public Admin login(String login, String name){
        return new Admin(login, name, new ID().getID());
    }



}
