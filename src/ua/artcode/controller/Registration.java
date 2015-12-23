package ua.artcode.controller;

import ua.artcode.model.*;
import ua.artcode.utils.serialization.TaxiAppSave;

import java.util.Date;
import java.util.List;

/**
 * Created by dexter on 23.12.15.
 */
public class Registration {

    public static boolean addClient(String name, int phone, String location, String pass, AppDataContainer appDataContainer){

        IPerson person = appDataContainer.getClient(name);
        if(person != null){
            return false;
        }

        Client client = new Client(name, phone, location, pass, ID.genId());
        appDataContainer.addClientToData(client);
        TaxiAppSave.save("clients.json", appDataContainer.getListClients());

        return true;
    }

    public static Client addClientO(String name, int phone, String location, String pass, AppDataContainer appDataContainer){

        IPerson person = appDataContainer.getClient(name);
        if(person != null){
            return null;
        }

        Client client = new Client(name, phone, location, pass, ID.genId());
        appDataContainer.addClientToData(client);
        TaxiAppSave.save("clients.json", appDataContainer.getListClients());

        return client;
    }

    public static Driver addDriverO(String name, Car car, AppDataContainer appDataContainer){

        Driver driver = new Driver(name, car, ID.genId(), new Boolean("false"));
        appDataContainer.addDriverToData(driver);
        TaxiAppSave.save("drivers.json", appDataContainer.getListDrivers());

        return driver;
    }

    public static Ticket addTicket(String fromLocation, String toLocation, long idClient){
        return new Ticket(0, idClient, fromLocation, toLocation, 0, "NEW",
                null, new Date(), null, ID.genId());
    }
}
