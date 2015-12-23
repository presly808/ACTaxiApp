package ua.artcode.controller;

import ua.artcode.model.Client;
import ua.artcode.model.Driver;
import ua.artcode.model.IPerson;
import ua.artcode.model.Ticket;

import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public class ClientController implements IClientController {

    private String me = "client";
    private Client currentClient;
    private Ticket currentTicket;
    private AppDataContainer appDataContainer;

    public ClientController(Client currentClient, AppDataContainer appDataContainer) {
        this.currentClient = currentClient;
        this.appDataContainer = appDataContainer;
    }

    public long callTaxi(String fromLocation, String toLocation){
        currentTicket = Registration.addTicket(fromLocation, toLocation, currentClient.getiD(), appDataContainer);
        appDataContainer.addTicketToData(currentTicket);
        return currentTicket.getiDTicket();
    }

    public Ticket getCurrentTicket(){
        return currentTicket;
    }

    @Override
    public String whoAmI(){
        return me;
    }

}
