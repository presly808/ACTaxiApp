package ua.artcode.controller;

import ua.artcode.model.*;
import ua.artcode.utils.serialization.TaxiAppSave;

import java.util.Date;

/**
 * Created by dexter on 23.12.15.
 */
public class Registration {

    public static boolean addClient(String name, int phone, String location, String pass, AppDataContainer appDataContainer){

        Person person = appDataContainer.getClient(name);
        if(person != null){
            return false;
        }

        Client client = new Client(name, phone, location, pass, ID.genId());
        appDataContainer.addClientToData(client);
        TaxiAppSave.save("clients.json", appDataContainer.getListClients());

        return true;
    }

    public static Client addClientO(String name, int phone, String location, String pass, AppDataContainer appDataContainer){

        Person person = appDataContainer.getClient(name);
        if(person != null){
            return null;
        }

        Client client = new Client(name, phone, location, pass, ID.genId());
        appDataContainer.addClientToData(client);
        TaxiAppSave.save("clients.json", appDataContainer.getListClients());

        return client;
    }

    public static Driver addDriverO(String name, Car car, AppDataContainer appDataContainer, String pass){

        Driver driver = new Driver(name, car, ID.genId(), new Boolean("false"), 0, pass);
        appDataContainer.addDriverToData(driver);
        TaxiAppSave.save("drivers.json", appDataContainer.getListDrivers());

        return driver;
    }

    public static Ticket addTicket(String fromLocation, String toLocation, long idClient, AppDataContainer appDataContainer){

        Ticket ticket = new Ticket(0, idClient, fromLocation, toLocation, 0, "NEW",
                new Date(), new Date(), new Date(), ID.genId());
        appDataContainer.addTicketToData(ticket);
        TaxiAppSave.save("tickets.json", appDataContainer.getListTickets());


        return ticket;
    }

}
