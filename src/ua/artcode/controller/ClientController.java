package ua.artcode.controller;

import ua.artcode.model.Client;
import ua.artcode.model.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public class ClientController implements IClientController{

    private Client currentClient;
    private Ticket currentTicket;
    private AppDataContainer appDataContainer;

    public ClientController(Client currentClient, AppDataContainer appDataContainer) {
        this.currentClient = currentClient;
        this.appDataContainer = appDataContainer;
    }

    @Override
    public long callTaxi(String fromLocation, String toLocation){
        currentTicket = Registration.addTicket(fromLocation, toLocation, currentClient.getId(), appDataContainer);
        appDataContainer.addTicketToData(currentTicket);
        return currentTicket.getiDTicket();
    }

    @Override
    public Ticket getCurrentTicket(){
        return currentTicket;
    }

    @Override
    public List<Ticket> getTickets() {

        ArrayList<Ticket> allClientsTickets = new ArrayList<>();

        for(Ticket tmp : appDataContainer.getListTickets()){
           if(tmp.getIdClient() == currentClient.getId()){
               allClientsTickets.add(tmp);
           }
        }

        return allClientsTickets;
    }

}
