package ua.artcode.controller;

import ua.artcode.exception.ClientHaveAlreadyHadATicket;
import ua.artcode.model.Ticket;

/**
 * Created by dexter on 20.12.15.
 */
public interface IClientController extends ITaxiController {

    long callTaxi(String fromLocation, String toLocation) throws ClientHaveAlreadyHadATicket;
    Ticket getCurrentTicket();
    void rejectTaxi();

}
