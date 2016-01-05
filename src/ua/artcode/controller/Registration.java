package ua.artcode.controller;

import ua.artcode.exception.LoginHasAlreadyUsed;
import ua.artcode.model.*;
import ua.artcode.utils.accounts.Calculator;
import ua.artcode.utils.geolocation.GoogleMapsAPIImpl;
import ua.artcode.utils.geolocation.Location;
import ua.artcode.utils.serialization.TaxiAppSave;

import java.util.Date;

/**
 * Created by dexter on 23.12.15.
 */
public class Registration {

    public static boolean addClient(String name, int phone, String location,
                                    String pass, AppDataContainer appDataContainer)  {

        Person person = appDataContainer.getPerson(name, appDataContainer.getListClients());
        if(person != null){
            return false;
        }

        Client client = new Client(name, phone, location, pass, ID.genId(name.hashCode()));
        appDataContainer.addClientToData(client);
        TaxiAppSave.save(appDataContainer);

        return true;
    }

    public static Client addClientO(String name, int phone, String location,
                                    String pass, AppDataContainer appDataContainer) throws LoginHasAlreadyUsed {

        Person person = appDataContainer.getPerson(name, appDataContainer.getListClients());
        if(person != null){
            throw new LoginHasAlreadyUsed("This Client Login has already used");
        }

        Client client = new Client(name, phone, location, pass, ID.genId(name.hashCode()));
        appDataContainer.addClientToData(client);
        TaxiAppSave.save(appDataContainer);

        return client;
    }

    public static Driver addDriverO(String name, Car car, AppDataContainer appDataContainer,
                                    String pass) {

        Driver driver = new Driver(name, car, ID.genId(name.hashCode()), new Boolean("true"), 0, pass);
        appDataContainer.addDriverToData(driver);
        TaxiAppSave.save(appDataContainer);

        return driver;
    }

    public static Ticket addTicket(String fromLocation, String toLocation, long idClient, AppDataContainer appDataContainer){

        double distance = Location.getDistance(fromLocation, toLocation);

        Ticket ticket = new Ticket(0, idClient, fromLocation, toLocation, Calculator.getCost(distance),
                TicketStatus.NEW, new Date(), new Date(), new Date(),
                ID.genId(fromLocation.hashCode()));

        appDataContainer.addTicketToData(ticket);
        TaxiAppSave.save(appDataContainer);

        return ticket;
    }

    public static Admin addAdmin(String name, String pass, AppDataContainer appDataContainer) throws LoginHasAlreadyUsed {

        Person person = appDataContainer.getPerson(name, appDataContainer.getListClients());
        if(person != null){
            throw new LoginHasAlreadyUsed("This Admins Login has already used");
        }

        Admin admin = new Admin(name, pass, ID.genId(name.hashCode()));
        appDataContainer.addAdminToData(admin);
        TaxiAppSave.save(appDataContainer);
        return admin;
    }
}
