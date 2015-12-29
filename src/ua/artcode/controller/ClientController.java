package ua.artcode.controller;

import ua.artcode.exception.ClientHaveAlreadyHadATicket;
import ua.artcode.exception.NoTicketsException;
import ua.artcode.model.Client;
import ua.artcode.model.Ticket;
import ua.artcode.model.TicketStatus;

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
        currentTicket = setTicket();
    }

    private Ticket setTicket() {

        for(Ticket tmp : appDataContainer.getListTickets()){
            if(tmp.getIdClient() == currentClient.getId()){
                if(tmp.getStatus() == TicketStatus.NEW){
                    return tmp;
                }
            }
        }
        return null;
    }

    @Override
    public long callTaxi(String fromLocation, String toLocation) throws ClientHaveAlreadyHadATicket {

        currentTicket = setTicket();
        if(currentTicket != null){
            throw new ClientHaveAlreadyHadATicket("Client has already ordered the taxi..Maybe he wants to reject the order?");
        }

        currentTicket = Registration.addTicket(fromLocation, toLocation, currentClient.getId(), appDataContainer);
        appDataContainer.addTicketToData(currentTicket);


        return currentTicket.getiDTicket();
    }

    @Override
    public Ticket getCurrentTicket() throws NoTicketsException {

        if(currentTicket != null){
            return currentTicket;
        }
        throw new NoTicketsException("Client has not called taxi");
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

    @Override
    public void rejectTaxi() throws NoTicketsException {
        if(currentTicket == null){
            throw new NoTicketsException("You've already canceled the order");
        }
        currentTicket.setStatus(TicketStatus.REJECTED);
        currentTicket = null;
    }

}
