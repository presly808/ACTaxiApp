package ua.artcode.controller;

import ua.artcode.model.Ticket;

import java.util.List;

/**
 * Created by dexter on 20.12.15.
 */
public interface ITaxiController {
    List<Ticket> getTickets();
}
